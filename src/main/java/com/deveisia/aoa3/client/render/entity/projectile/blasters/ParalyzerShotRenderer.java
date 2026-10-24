package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.ParalyzerShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ParalyzerShotRenderer extends ParticleProjectileRenderer<ParalyzerShotEntity> {
	public ParalyzerShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ParalyzerShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, ColourUtil.CYAN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
