package com.deveisia.aoa3.client.render.entity.mob;

import net.minecraft.client.model.geom.ModelLayers;
import com.deveisia.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class DoppelgangerRenderer extends LivingEntityRenderer<DoppelgangerEntity, PlayerModel<DoppelgangerEntity>> {
	public DoppelgangerRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new PlayerModel<DoppelgangerEntity>(renderManager.bakeLayer(ModelLayers.PLAYER), false), 0.5f);

		this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(renderManager.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(renderManager.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), renderManager.getModelManager()));
		this.addLayer(new ItemInHandLayer<>(this, renderManager.getItemInHandRenderer()));
		this.addLayer(new ArrowLayer<>(renderManager, this));
	}

	@Override
	public ResourceLocation getTextureLocation(DoppelgangerEntity entity) {
		return Minecraft.getInstance().player.getSkinTextureLocation();
	}

	@Override
	public Vec3 getRenderOffset(DoppelgangerEntity doppelganger, float partialTicks) {
		return doppelganger.isCrouching() ? new Vec3(0, -0.125d, 0) : super.getRenderOffset(doppelganger, partialTicks);
	}

	@Override
	public void render(DoppelgangerEntity doppelganger, float rotYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		setModelStates(doppelganger);
		super.render(doppelganger, rotYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	@Override
	protected void scale(DoppelgangerEntity doppelganger, PoseStack matrixStack, float partialTicks) {
		matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
	}

	@Override
	protected boolean shouldShowName(DoppelgangerEntity pEntity) {
		return false;
	}

	@Override
	protected void renderNameTag(DoppelgangerEntity entity, Component displayName, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		matrixStack.pushPose();
		matrixStack.translate(0, 0.25875f, 0);
		super.renderNameTag(entity, Minecraft.getInstance().player.getDisplayName(), matrixStack, buffer, packedLight);
		matrixStack.popPose();
	}

	private void setModelStates(DoppelgangerEntity doppelganger) {
		PlayerModel<DoppelgangerEntity> model = this.getModel();

		model.setAllVisible(true);

		model.hat.visible = false;
		model.jacket.visible = false;
		model.leftPants.visible = false;
		model.rightPants.visible = false;
		model.leftSleeve.visible = false;
		model.rightSleeve.visible = false;
		model.crouching = doppelganger.isCrouching();
		HumanoidModel.ArmPose mainHandArmPose = getArmPose(doppelganger, InteractionHand.MAIN_HAND);
		HumanoidModel.ArmPose offhandArmPose = getArmPose(doppelganger, InteractionHand.OFF_HAND);

		if (mainHandArmPose.isTwoHanded())
			offhandArmPose = doppelganger.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;

		if (doppelganger.getMainArm() == HumanoidArm.RIGHT) {
			model.rightArmPose = mainHandArmPose;
			model.leftArmPose = offhandArmPose;
		}
		else {
			model.rightArmPose = offhandArmPose;
			model.leftArmPose = mainHandArmPose;
		}
	}

	private static HumanoidModel.ArmPose getArmPose(DoppelgangerEntity doppelganger, InteractionHand hand) {
		ItemStack stack = doppelganger.getItemInHand(hand);

		if (stack.isEmpty())
			return HumanoidModel.ArmPose.EMPTY;

		if (doppelganger.getUsedItemHand() == hand && doppelganger.getUseItemRemainingTicks() > 0) {
			UseAnim useaction = stack.getUseAnimation();

			if (useaction == UseAnim.BLOCK)
				return HumanoidModel.ArmPose.BLOCK;

			if (useaction == UseAnim.BOW)
				return HumanoidModel.ArmPose.BOW_AND_ARROW;

			if (useaction == UseAnim.SPEAR)
				return HumanoidModel.ArmPose.THROW_SPEAR;

			if (useaction == UseAnim.CROSSBOW && hand == doppelganger.getUsedItemHand())
				return HumanoidModel.ArmPose.CROSSBOW_CHARGE;

		}
		else if (!doppelganger.swinging && stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack)) {
			return HumanoidModel.ArmPose.CROSSBOW_HOLD;
		}

		return HumanoidModel.ArmPose.ITEM;
	}

	public void renderRightHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).rightArm, (this.model).rightSleeve);
	}

	public void renderLeftHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).leftArm, (this.model).leftSleeve);
	}

	private void renderHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger, ModelPart armPiece, ModelPart armCovering) {
		PlayerModel<DoppelgangerEntity> model = this.getModel();

		this.setModelStates(doppelganger);

		model.attackTime = 0;
		model.crouching = false;
		model.swimAmount = 0;

		model.setupAnim(doppelganger, 0, 0, 0, 0, 0);

		armPiece.xRot = 0;

		armPiece.render(matrixStack, buffer.getBuffer(RenderType.entitySolid(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);

		armCovering.xRot = 0;

		armCovering.render(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);
	}

	@Override
	protected void setupRotations(DoppelgangerEntity doppelganger, PoseStack matrixStack, float age, float yawRot, float partialTicks) {
		if (doppelganger.isFallFlying()) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			float lerpFallTicks = (float)doppelganger.getFallFlyingTicks() + partialTicks;

			if (!doppelganger.isAutoSpinAttack())
				matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.clamp(lerpFallTicks * lerpFallTicks / 100.0F, 0.0F, 1.0F) * (-90.0F - doppelganger.getXRot())));

			Vec3 viewVec = doppelganger.getViewVector(partialTicks);
			Vec3 velocity = doppelganger.getDeltaMovement();
			double velocityScaleOffset = velocity.horizontalDistanceSqr();
			double viewScaleOffset = viewVec.horizontalDistanceSqr();

			if (velocityScaleOffset > 0 && viewScaleOffset > 0)
				matrixStack.mulPose(Axis.YP.rotation((float)(Math.signum(velocity.x * viewVec.z - velocity.z * viewVec.x) * Math.acos((velocity.x * viewVec.x + velocity.z * viewVec.z) / Math.sqrt(velocityScaleOffset * viewScaleOffset)))));
		}
		else if (doppelganger.getSwimAmount(partialTicks) > 0) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(doppelganger.getSwimAmount(partialTicks), 0, doppelganger.isInWater() ? -90 - doppelganger.getXRot() : -90)));

			if (doppelganger.isVisuallySwimming())
				matrixStack.translate(9, -1, 0.3f);
		}
		else {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);
		}
	}
}
