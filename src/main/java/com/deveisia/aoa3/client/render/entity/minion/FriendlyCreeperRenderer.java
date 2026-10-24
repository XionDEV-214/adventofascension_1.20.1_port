package com.deveisia.aoa3.client.render.entity.minion;

import net.minecraft.client.model.geom.ModelLayers;
import com.deveisia.aoa3.client.render.entity.layer.FriendlyCreeperChargeRenderLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class FriendlyCreeperRenderer extends LivingEntityRenderer<Mob, EntityModel<Mob>> {
	private static final ResourceLocation textures = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/creeper/creeper.png");

	public FriendlyCreeperRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CreeperModel<Mob>(renderManager.bakeLayer(ModelLayers.CREEPER)), 1 / 3f);

		addLayer(new FriendlyCreeperChargeRenderLayer(this, new CreeperModel<Mob>(renderManager.bakeLayer(ModelLayers.CREEPER_ARMOR))));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		float flashIntensity = 0;
		float scaleRotationMod = 1.0F + Mth.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(Mth.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		matrix.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}

	@Override
	protected float getWhiteOverlayProgress(Mob entity, float partialTicks) {
		float flashIntensity = 0;

		return (int)(flashIntensity * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(flashIntensity, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return textures;
	}
}
