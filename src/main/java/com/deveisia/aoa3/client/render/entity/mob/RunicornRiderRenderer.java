package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.runandor.RunicornRiderModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class RunicornRiderRenderer extends AoAMobRenderer {
	public RunicornRiderRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RunicornRiderModel(), AoAEntities.Mobs.RUNICORN_RIDER.get().getWidth() / 3f, 1f, ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/runandor/runicorn_rider.png"));

	}

	@Override
	public void render(Mob entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entity.getHealth() > 0 && entity.isAlive() && (entity.getDeltaMovement().x() > 0 || entity.getDeltaMovement().z() > 0 || entity.getDeltaMovement().y() > 0)) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 1.25, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 1.05, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + 0.95, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + 0.75, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
		}
	}
}
