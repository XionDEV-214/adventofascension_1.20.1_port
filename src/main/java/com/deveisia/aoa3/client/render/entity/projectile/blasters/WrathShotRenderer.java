package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.WrathShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class WrathShotRenderer extends ParticleProjectileRenderer<WrathShotEntity> {
	public WrathShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(WrathShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.RED), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
