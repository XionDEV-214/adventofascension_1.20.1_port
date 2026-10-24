package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.mob.WhiteBallEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class WhiteBallRenderer extends ParticleProjectileRenderer<WhiteBallEntity> {
	public WhiteBallRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(WhiteBallEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
