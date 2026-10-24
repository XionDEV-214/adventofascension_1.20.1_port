package com.deveisia.aoa3.client.render.entity.animal;

import com.deveisia.aoa3.client.model.entity.animal.MeganeuropsisModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.animal.MeganeuropsisEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class MeganeuropsisRenderer extends MobRenderer<MeganeuropsisEntity, EntityModel<MeganeuropsisEntity>> {
	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/passive/meganeuropsis.png");
	public MeganeuropsisRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new MeganeuropsisModel(), AoAEntities.Animals.MEGANEUROPSIS.get().getWidth() / 3f);
	}

	@Override
	protected void scale(MeganeuropsisEntity entity, PoseStack matrix, float partialTicks) {
		if (entity.isLanded() && entity.isPassenger())
			matrix.translate(0, -entity.getBbHeight() * 0.75f, 0);

		if (entity.getStartLandingTicks() > 0 && entity.getLandingDirection() != Direction.UP) {
			float landPercentage = (float)Mth.clamp(entity.tickCount - entity.getStartLandingTicks(), 0, 10) / 10;
			float rotAmount = 90 * landPercentage;

			switch (entity.getLandingDirection()) {
				case NORTH:
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case SOUTH:
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));

					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case EAST:
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));

					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case WEST:
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					matrix.mulPose(Axis.ZP.rotationDegrees(-rotAmount));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public ResourceLocation getTextureLocation(MeganeuropsisEntity entity) {
		return texture;
	}
}
