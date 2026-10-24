package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.staff.UltimatumShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class UltimatumShotRenderer extends ParticleProjectileRenderer<UltimatumShotEntity> {
	public UltimatumShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(UltimatumShotEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, ColourUtil.CYAN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.5f, 3, ColourUtil.YELLOW), entity.getX() + RandomUtil.randomValueBetween(-0.5d, 0.5d), entity.getY() + RandomUtil.randomValueBetween(-0.5d, 0.5d), entity.getZ() + RandomUtil.randomValueBetween(-0.5d, 0.5d), 0, 0, 0);
	}
}
