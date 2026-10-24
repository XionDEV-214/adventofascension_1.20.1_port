package com.deveisia.aoa3.client.render.entity.projectile.bullets;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.content.entity.projectile.gun.DischargeSlugEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DischargeSlugRenderer extends ParticleProjectileRenderer<DischargeSlugEntity> {
	public DischargeSlugRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(DischargeSlugEntity entity, float partialTicks) {}
}
