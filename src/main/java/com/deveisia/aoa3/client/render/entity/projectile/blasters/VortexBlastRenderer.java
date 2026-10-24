package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.VortexBlastEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class VortexBlastRenderer extends ParticleProjectileRenderer<VortexBlastEntity> {
	public VortexBlastRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(VortexBlastEntity entity, float partialTicks) {
		if (RandomUtil.fiftyFifty()) {
			for (int i = 0; i < 3; i++) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
	}
}
