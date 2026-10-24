package com.deveisia.aoa3.client.render.entity.projectile.misc;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.content.entity.projectile.misc.TidalWaveEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;

public class TidalWaveRenderer extends ParticleProjectileRenderer<TidalWaveEntity> {
	public TidalWaveRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(TidalWaveEntity entity, float partialTicks) {
		entity.level().addAlwaysVisibleParticle(ParticleTypes.BUBBLE, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
