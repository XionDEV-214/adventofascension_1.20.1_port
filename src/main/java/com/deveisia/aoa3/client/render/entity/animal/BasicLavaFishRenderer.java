package com.deveisia.aoa3.client.render.entity.animal;

import com.deveisia.aoa3.client.model.entity.EntityGeoModel;
import com.deveisia.aoa3.client.render.entity.AnimatedMobRenderer;
import com.deveisia.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class BasicLavaFishRenderer extends AnimatedMobRenderer<BasicLavaFishEntity> {
	public BasicLavaFishRenderer(EntityRendererProvider.Context renderManager, String assetSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(assetSubpath), shadow);
	}

	public BasicLavaFishRenderer(EntityRendererProvider.Context renderManager, String modelSubpath, String textureSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(modelSubpath, textureSubpath), shadow);
	}

	public BasicLavaFishRenderer(EntityRendererProvider.Context renderManager, String modelSubpath, String textureSubpath, String animationSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(modelSubpath, textureSubpath, animationSubpath), shadow);
	}

	@Override
	protected void applyRotations(BasicLavaFishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

		matrixStackIn.mulPose(Axis.YP.rotationDegrees(4.3f * Mth.sin(0.6f * ageInTicks)));

		if (!entityLiving.isInLava()) {
			matrixStackIn.translate(0.2f, 0.1f, 0);
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90));
		}
	}
}
