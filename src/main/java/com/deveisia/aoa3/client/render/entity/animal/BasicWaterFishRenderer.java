package com.deveisia.aoa3.client.render.entity.animal;

import com.deveisia.aoa3.client.model.entity.EntityGeoModel;
import com.deveisia.aoa3.client.render.entity.AnimatedMobRenderer;
import com.deveisia.aoa3.content.entity.animal.fish.BasicFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class BasicWaterFishRenderer extends AnimatedMobRenderer<BasicFishEntity> {
	public BasicWaterFishRenderer(EntityRendererProvider.Context renderManager, String assetSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicFishEntity>(assetSubpath), shadow);
	}

	public BasicWaterFishRenderer(EntityRendererProvider.Context renderManager, String modelSubpath, String textureSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicFishEntity>(modelSubpath, textureSubpath), shadow);
	}

	public BasicWaterFishRenderer(EntityRendererProvider.Context renderManager, String modelSubpath, String textureSubpath, String animationSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicFishEntity>(modelSubpath, textureSubpath, animationSubpath), shadow);
	}

	@Override
	protected void applyRotations(BasicFishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

		matrixStackIn.mulPose(Axis.YP.rotationDegrees(4.3f * Mth.sin(0.6f * ageInTicks)));

		if (!entityLiving.isInWater()) {
			matrixStackIn.translate(0.2f, 0.1f, 0);
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90));
		}
	}
}
