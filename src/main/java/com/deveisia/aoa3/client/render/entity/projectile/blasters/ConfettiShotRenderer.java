package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.ConfettiShotEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ConfettiShotRenderer extends ParticleProjectileRenderer<ConfettiShotEntity> {
	public ConfettiShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ConfettiShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), 0, -0.05, 0);
		}
	}
}
