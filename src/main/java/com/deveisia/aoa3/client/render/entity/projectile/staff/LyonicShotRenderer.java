package com.deveisia.aoa3.client.render.entity.projectile.staff;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.staff.LyonicShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LyonicShotRenderer extends ParticleProjectileRenderer<LyonicShotEntity> {
	public LyonicShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(LyonicShotEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, RandomUtil.getRandomSelection(ColourUtil.RGB(30, 29, 22), ColourUtil.RGB(204, 172, 0))), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
