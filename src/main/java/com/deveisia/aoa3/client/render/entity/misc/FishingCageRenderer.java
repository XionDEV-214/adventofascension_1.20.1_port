package com.deveisia.aoa3.client.render.entity.misc;

import com.deveisia.aoa3.client.model.entity.misc.FishingCageModel;
import com.deveisia.aoa3.content.entity.misc.FishingCageEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class FishingCageRenderer extends EntityRenderer<FishingCageEntity> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/misc/fishing_cage.png");
	private final FishingCageModel model = new FishingCageModel();

	public FishingCageRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public void render(FishingCageEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.translate(0, 1.5d, 0);
		matrix.mulPose(Axis.XP.rotationDegrees(180));
		matrix.mulPose(Axis.YP.rotationDegrees(entityYaw));
		matrix.scale(-1, 1, -1);

		VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.translate(0, 1.4d, 0.1d);
		matrix.mulPose(Axis.XP.rotationDegrees(90));

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		for (ItemStack stack : entity.getLoot()) {
			itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, matrix, buffer, entity.level(), entity.getId());
			matrix.translate(0, -0.15d, 0);
		}

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(FishingCageEntity entity) {
		return texture;
	}
}
