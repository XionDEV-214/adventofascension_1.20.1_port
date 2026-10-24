package com.deveisia.aoa3.client.render.entity.projectile.staff;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.staff.SunShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;

public class SunShotRenderer extends ParticleProjectileRenderer<SunShotEntity> {
	public SunShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SunShotEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 2, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 2, 3, ColourUtil.RED), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

		double posX = entity.getX() + RandomUtil.randomGaussianValue() * 0.5;
		double posY = entity.getY() + RandomUtil.randomGaussianValue() * 0.5;
		double posZ = entity.getZ() + RandomUtil.randomGaussianValue() * 0.5;

		if (!Minecraft.getInstance().isPaused())
			entity.level().addParticle(ParticleTypes.FLAME, posX, posY, posZ, 0, 0, 0);
	}
}
