package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.mob.MechFallEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MechFallRenderer extends ParticleProjectileRenderer<MechFallEntity> {
	public MechFallRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MechFallEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
