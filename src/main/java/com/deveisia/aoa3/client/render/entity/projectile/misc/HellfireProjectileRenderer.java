package com.deveisia.aoa3.client.render.entity.projectile.misc;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HellfireProjectileRenderer extends ParticleProjectileRenderer<HellfireProjectileEntity> {
	public HellfireProjectileRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(HellfireProjectileEntity entity, float partialTicks) {
		for (int i = 0; i < 5; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RED), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
