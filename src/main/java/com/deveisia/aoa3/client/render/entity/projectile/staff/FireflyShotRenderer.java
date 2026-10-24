package com.deveisia.aoa3.client.render.entity.projectile.staff;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.staff.FireflyShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class FireflyShotRenderer extends ParticleProjectileRenderer<FireflyShotEntity> {
	public FireflyShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(FireflyShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
