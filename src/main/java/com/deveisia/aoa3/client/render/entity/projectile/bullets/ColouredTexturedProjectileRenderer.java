package com.deveisia.aoa3.client.render.entity.projectile.bullets;

import com.deveisia.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrowableProjectile;

public class ColouredTexturedProjectileRenderer extends TexturedProjectileRenderer<ThrowableProjectile> {
	private final int colour;

	public ColouredTexturedProjectileRenderer(final EntityRendererProvider.Context manager, final int colour, final ResourceLocation textureResource) {
		super(manager, textureResource);

		this.colour = colour;
	}

	@Override
	public void render(ThrowableProjectile entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, colour), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
