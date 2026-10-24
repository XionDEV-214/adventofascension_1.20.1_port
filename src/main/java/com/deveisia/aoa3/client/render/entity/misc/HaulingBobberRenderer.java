package com.deveisia.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class HaulingBobberRenderer extends FishingHookRenderer {
	private final ResourceLocation TEXTURE;
	private RenderType renderType = null;

	public HaulingBobberRenderer(EntityRendererProvider.Context rendererManager) {
		this(rendererManager, ResourceLocation.parse("textures/entity/fishing_hook.png"));
	}

	public HaulingBobberRenderer(EntityRendererProvider.Context renderManager, ResourceLocation texture) {
		super(renderManager);

		this.TEXTURE = texture;
	}

	public void render(FishingHook bobber, float yaw, float partialTicks, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int packedLight) {
		Player player = bobber.getPlayerOwner();

		if (player != null) {
			ResourceLocation texture = getTextureLocation(bobber);

			if (renderType == null || texture != TEXTURE)
				renderType = RenderType.entityCutout(getTextureLocation(bobber));

			matrixStack.pushPose();
			matrixStack.pushPose();
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
			matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));

			PoseStack.Pose lastPose = matrixStack.last();
			Matrix4f pose = lastPose.pose();
			Matrix3f normalisedPose = lastPose.normal();
			VertexConsumer vertexBuilder = renderTypeBuffer.getBuffer(renderType);

			vertex(vertexBuilder, pose, normalisedPose, packedLight, 0, 0, 0, 1);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 1, 0, 1, 1);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 1, 1, 1, 0);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 0, 1, 0, 0);

			matrixStack.popPose();

			int armSideMod = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
			ItemStack heldStack = player.getMainHandItem();

			if (!(heldStack.getItem() instanceof FishingRodItem))
				armSideMod = -armSideMod;

			double rodTipPosX;
			double rodTipPosY;
			double rodTipPosZ;
			float playerPoseAdjust;

			if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
				double fov = this.entityRenderDispatcher.options.fov().get() / 100d;
				float attackAnimRot = Mth.sin((float)Math.sqrt(player.getAttackAnim(partialTicks)) * (float)Math.PI);
				Vec3 lineSideVec = new Vec3((double)armSideMod * -0.36D * fov, -0.045D * fov, 0.4D);
				lineSideVec = lineSideVec.xRot(-Mth.lerp(partialTicks, player.xRotO, player.getXRot()) * ((float)Math.PI / 180F));
				lineSideVec = lineSideVec.yRot(-Mth.lerp(partialTicks, player.yRotO, player.getYRot()) * ((float)Math.PI / 180F));
				lineSideVec = lineSideVec.yRot(attackAnimRot * 0.5F);
				lineSideVec = lineSideVec.xRot(-attackAnimRot * 0.7F);
				rodTipPosX = Mth.lerp(partialTicks, player.xo, player.getX()) + lineSideVec.x;
				rodTipPosY = Mth.lerp(partialTicks, player.yo, player.getY()) + lineSideVec.y;
				rodTipPosZ = Mth.lerp(partialTicks, player.zo, player.getZ()) + lineSideVec.z;
				playerPoseAdjust = player.getEyeHeight();
			}
			else {
				float bodyRotPos = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
				double rodTipBodyRotXAdjust = Mth.sin(bodyRotPos);
				double rodTipBodyRotZAdjust = Mth.cos(bodyRotPos);
				double armSideAdjust = (double)armSideMod * 0.35d;
				rodTipPosX = Mth.lerp(partialTicks, player.xo, player.getX()) - rodTipBodyRotZAdjust * armSideAdjust - rodTipBodyRotXAdjust * 0.8D;
				rodTipPosY = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)partialTicks - 0.45D;
				rodTipPosZ = Mth.lerp(partialTicks, player.zo, player.getZ()) - rodTipBodyRotXAdjust * armSideAdjust + rodTipBodyRotZAdjust * 0.8D;
				playerPoseAdjust = player.isCrouching() ? -0.1875F : 0.0F;
			}

			float lineStartX = (float)(rodTipPosX - Mth.lerp(partialTicks, bobber.xo, bobber.getX()));
			float lineStartY = (float)(rodTipPosY - (Mth.lerp(partialTicks, bobber.yo, bobber.getY()) + 0.25D)) + playerPoseAdjust;
			float lineStartZ = (float)(rodTipPosZ - Mth.lerp(partialTicks, bobber.zo, bobber.getZ()));
			VertexConsumer lineVertexBuilder = renderTypeBuffer.getBuffer(RenderType.lines());
			Matrix4f prevPose = matrixStack.last().pose();

			for(int i = 0; i < 16; ++i) {
				stringVertex(lineStartX, lineStartY, lineStartZ, lineVertexBuilder, prevPose, i / 16f);
				stringVertex(lineStartX, lineStartY, lineStartZ, lineVertexBuilder, prevPose, (i + 1) / 16f);
			}

			matrixStack.popPose();

			RenderNameTagEvent renderNameplateEvent = new RenderNameTagEvent(bobber, bobber.getDisplayName(), this, matrixStack, renderTypeBuffer, packedLight, partialTicks);

			MinecraftForge.EVENT_BUS.post(renderNameplateEvent);

			if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(bobber)))
				this.renderNameTag(bobber, renderNameplateEvent.getContent(), matrixStack, renderTypeBuffer, packedLight);
		}
	}

	private static void vertex(VertexConsumer vertexBuilder, Matrix4f pose, Matrix3f normalisedPose, int packedLight, float x, int y, int u, int v) {
		vertexBuilder.vertex(pose, x - 0.5F, (float)y - 0.5F, 0.0F).color(255, 255, 255, 255).uv((float)u, (float)v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(normalisedPose, 0.0F, 1.0F, 0.0F).endVertex();
	}

	private static void stringVertex(float x, float y, float z, VertexConsumer vertexBuilder, Matrix4f pose, float lerpSection) {
		vertexBuilder.vertex(pose, x * lerpSection, y * (lerpSection * lerpSection + lerpSection) * 0.5F + 0.25F, z * lerpSection).color(0, 0, 0, 255).endVertex();
	}

	@Override
	public ResourceLocation getTextureLocation(FishingHook bobber) {
		return TEXTURE;
	}
}
