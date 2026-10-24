package com.deveisia.aoa3.client.render.entity.projectile.bullets;

import com.deveisia.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.gun.YellowBulletEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class YellowBulletRenderer extends TexturedProjectileRenderer<YellowBulletEntity> {
	public YellowBulletRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(YellowBulletEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
