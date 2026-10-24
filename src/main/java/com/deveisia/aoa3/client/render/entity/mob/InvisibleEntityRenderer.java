package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.misc.InvisibleEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class InvisibleEntityRenderer extends MobRenderer<Mob, EntityModel<Mob>> {
	public InvisibleEntityRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new InvisibleEntityModel(), 0f);
	}

	@Override
	public void render(Mob entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return null;
	}
}
