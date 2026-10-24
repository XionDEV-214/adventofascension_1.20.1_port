package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.LightSparkEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LightSparkRenderer extends ParticleProjectileRenderer<LightSparkEntity> {
	public LightSparkRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(LightSparkEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.GREEN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
