package com.deveisia.aoa3.client.render.entity.projectile.staff;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.staff.GhoulShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class GhoulShotRenderer extends ParticleProjectileRenderer<GhoulShotEntity> {
	public GhoulShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(GhoulShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.RGB(193, 64, 215)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.5f, 20, ColourUtil.RGB(247, 239, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
