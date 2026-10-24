package com.deveisia.aoa3.client.render.entity.projectile.bullets;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.content.entity.projectile.gun.SniperSlugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SniperSlugRenderer extends ParticleProjectileRenderer<SniperSlugEntity> {
	public SniperSlugRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SniperSlugEntity entity, float partialTicks) {}
}
