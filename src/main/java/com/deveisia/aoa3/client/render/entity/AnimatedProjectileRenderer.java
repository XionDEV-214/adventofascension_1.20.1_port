package com.deveisia.aoa3.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnimatedProjectileRenderer<T extends Entity & GeoAnimatable> extends GeoEntityRenderer<T> {
	public AnimatedProjectileRenderer(EntityRendererProvider.Context context, GeoModel<T> model, float shadowSize) {
		super(context, model);

		this.shadowRadius = shadowSize;
	}

	@Override
	protected void applyRotations(T animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, animatable.yRotO, animatable.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, animatable.xRotO, animatable.getXRot())));
	}
}
