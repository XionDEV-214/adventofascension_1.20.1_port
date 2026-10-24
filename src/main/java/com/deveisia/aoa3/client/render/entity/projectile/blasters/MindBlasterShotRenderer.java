package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.MindBlasterShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MindBlasterShotRenderer extends ParticleProjectileRenderer<MindBlasterShotEntity> {
	public MindBlasterShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MindBlasterShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			if (entity.toggle1) {
				entity.yOffset1 += 0.12;

				if (entity.yOffset1 >= 3.0f)
					entity.toggle1 = !entity.toggle1;
			}
			if (!entity.toggle1) {
				entity.yOffset1 -= 0.12;

				if (entity.yOffset1 <= -3.0f)
					entity.toggle1 = !entity.toggle1;
			}

			if (entity.toggle2) {
				entity.yOffset2 += 0.12;

				if (entity.yOffset2 >= 3.0f)
					entity.toggle2 = !entity.toggle2;
			}
			if (!entity.toggle2) {
				entity.yOffset2 -= 0.12;

				if (entity.yOffset2 <= -3.0f)
					entity.toggle2 = !entity.toggle2;
			}

			for (int j = 0; j < 3; j++) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.BLUE), entity.getX(), entity.getY() + entity.yOffset1, entity.getZ(), 0, 0, 0);
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + entity.yOffset2, entity.getZ(), 0, 0, 0);
			}
		}
	}
}
