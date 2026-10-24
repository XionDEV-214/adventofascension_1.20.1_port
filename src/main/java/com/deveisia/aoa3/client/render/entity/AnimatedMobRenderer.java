package com.deveisia.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnimatedMobRenderer<T extends LivingEntity & GeoAnimatable> extends GeoEntityRenderer<T> {
	public AnimatedMobRenderer(EntityRendererProvider.Context context, GeoModel<T> model, float shadowSize) {
		super(context, model);

		this.shadowRadius = shadowSize;
	}
}
