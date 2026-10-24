package com.deveisia.aoa3.client.render.entity.projectile.cannonshots;

import com.deveisia.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.cannon.HeavyBoneCannonballEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HeavyBoneBallRenderer extends TexturedProjectileRenderer<HeavyBoneCannonballEntity> {
	public HeavyBoneBallRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(HeavyBoneCannonballEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RGB(250, 250, 150)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
