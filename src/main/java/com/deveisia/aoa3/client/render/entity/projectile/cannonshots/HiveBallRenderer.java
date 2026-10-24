package com.deveisia.aoa3.client.render.entity.projectile.cannonshots;

import com.deveisia.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.cannon.HiveBallEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HiveBallRenderer extends TexturedProjectileRenderer<HiveBallEntity> {
	private int counter = 12;
	private boolean toggle;

	public HiveBallRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(HiveBallEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			counter--;

			if (counter == 0) {
				toggle = !toggle;
				counter = 12;
			}

			if (toggle) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
			else {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
	}
}
