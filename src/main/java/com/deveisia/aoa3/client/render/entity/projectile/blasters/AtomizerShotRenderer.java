package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.AtomizerShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class AtomizerShotRenderer extends ParticleProjectileRenderer<AtomizerShotEntity> {
	public AtomizerShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(AtomizerShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.BLUE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
