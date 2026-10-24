package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.mob.MechShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MechShotRenderer extends ParticleProjectileRenderer<MechShotEntity> {
	public MechShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MechShotEntity entity, float partialTicks) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
