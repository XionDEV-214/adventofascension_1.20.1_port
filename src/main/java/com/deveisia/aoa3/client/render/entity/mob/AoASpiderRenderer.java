package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class AoASpiderRenderer extends AoAMobRenderer {
	public AoASpiderRenderer(EntityRendererProvider.Context renderManager, EntityModel<Mob> model, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, model, shadowSize, scale, texture);
	}

	@Override
	protected float getFlipDegrees(Mob LivingEntityIn) {
		return 180f;
	}
}
