package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.content.entity.projectile.mob.NethengeicWitherShotEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class NethengeicWitherShotRenderer extends EntityRenderer<NethengeicWitherShotEntity> {
	private final ResourceLocation texture;
	private final SkullModel headModel;

	public NethengeicWitherShotRenderer(EntityRendererProvider.Context renderManager, ResourceLocation textureResource) {
		super(renderManager);

		this.texture = textureResource;
		this.headModel = new SkullModel(renderManager.bakeLayer(ModelLayers.WITHER_SKULL));
	}

	@Override
	public void render(NethengeicWitherShotEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.scale(-1.0F, -1.0F, 1.0F);

		float rotY = Mth.rotLerp(entity.yRotO, entity.getYRot(), partialTicks);
		float rotX = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
		VertexConsumer ivertexbuilder = buffer.getBuffer(this.headModel.renderType(this.getTextureLocation(entity)));

		headModel.setupAnim(0.0F, rotY, rotX);
		headModel.renderToBuffer(matrix, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(NethengeicWitherShotEntity entity) {
		return texture;
	}
}
