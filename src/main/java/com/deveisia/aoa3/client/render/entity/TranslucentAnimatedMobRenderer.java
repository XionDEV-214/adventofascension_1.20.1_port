package com.deveisia.aoa3.client.render.entity;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class TranslucentAnimatedMobRenderer<T extends LivingEntity & GeoAnimatable> extends AnimatedMobRenderer<T> {
	public TranslucentAnimatedMobRenderer(EntityRendererProvider.Context context, GeoModel<T> model, float shadowSize) {
		super(context, model, shadowSize);
	}

	@Override
	public RenderType getRenderType(T animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(texture);
	}
}
