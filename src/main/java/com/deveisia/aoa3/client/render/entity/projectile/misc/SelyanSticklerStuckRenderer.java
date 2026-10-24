package com.deveisia.aoa3.client.render.entity.projectile.misc;

import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.misc.SelyanSticklerStuckEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SelyanSticklerStuckRenderer extends EntityRenderer<SelyanSticklerStuckEntity> {
	private final ResourceLocation texture;

	public SelyanSticklerStuckRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void render(SelyanSticklerStuckEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		for (int i = 0; i < 7; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.GREEN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(SelyanSticklerStuckEntity entity) {
		return texture;
	}
}
