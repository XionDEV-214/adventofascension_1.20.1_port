package com.deveisia.aoa3.client.render.entity.projectile.cannonshots;

import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.cannon.MoonShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MoonShotRenderer extends EntityRenderer<MoonShotEntity> {
	private final ResourceLocation texture;

	public MoonShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void render(MoonShotEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
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
			if (!entity.toggle2){
				entity.yOffset2 -= 0.12;

				if (entity.yOffset2 <= -3.0f)
					entity.toggle2 = !entity.toggle2;
			}

			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.RGB(255, 105, 180)), entity.getX(), entity.getY() + entity.yOffset1, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY() + entity.yOffset2, entity.getZ(), 0, 0, 0);
		}
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(MoonShotEntity entity) {
		return texture;
	}
}
