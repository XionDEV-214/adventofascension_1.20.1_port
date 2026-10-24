package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.mob.LelyetianShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LelyetianShotRenderer extends ParticleProjectileRenderer<LelyetianShotEntity> {
	public LelyetianShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(LelyetianShotEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
