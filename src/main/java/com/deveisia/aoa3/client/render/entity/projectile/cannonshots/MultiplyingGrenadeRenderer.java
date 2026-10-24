package com.deveisia.aoa3.client.render.entity.projectile.cannonshots;

import com.deveisia.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.cannon.MultiplyingGrenadeEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MultiplyingGrenadeRenderer extends TexturedProjectileRenderer<MultiplyingGrenadeEntity> {
	public MultiplyingGrenadeRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(MultiplyingGrenadeEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}
