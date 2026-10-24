package com.deveisia.aoa3.client.render.entity.npc.ambient;

import com.deveisia.aoa3.client.model.entity.npc.DryadSpriteModel;
import com.deveisia.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DryadSpriteRenderer extends GeoEntityRenderer<DryadSpriteEntity> {
	public DryadSpriteRenderer(EntityRendererProvider.Context context, float shadowSize) {
		super(context, new DryadSpriteModel());

		this.shadowRadius = shadowSize;
	}

	@Override
	public void render(DryadSpriteEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		poseStack.pushPose();
		poseStack.translate(0, 0.01f, 0);
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}

	@Override
	public Color getRenderColor(DryadSpriteEntity animatable, float partialTick, int packedLight) {
		if (Minecraft.getInstance().player != null && animatable.isInvisibleTo(Minecraft.getInstance().player))
			return Color.ofRGBA(1f, 1f, 1f, 0f);

		return Color.WHITE;
	}

	@Override
	public RenderType getRenderType(DryadSpriteEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(texture);
	}

	@Override
	protected float getDeathMaxRotation(DryadSpriteEntity entity) {
		return 0;
	}
}
