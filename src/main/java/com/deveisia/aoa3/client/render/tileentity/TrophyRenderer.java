package com.deveisia.aoa3.client.render.tileentity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.content.block.tileentity.TrophyTileEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

public class TrophyRenderer implements BlockEntityRenderer<TrophyTileEntity> {
	public TrophyRenderer(BlockEntityRendererProvider.Context context) {}

	@Override
	public void render(TrophyTileEntity tileEntity, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Entity entity = tileEntity.getCachedEntity();

		if (entity == null && "minecraft:player".equals(tileEntity.getEntityId()))
			entity = Minecraft.getInstance().player;

		if (entity != null) {
			try {
				matrix.pushPose();
				matrix.translate(0.5f, 0, 0.5f);

				float scale = 0.53125F;
				float maxScale = Math.max(entity.getBbWidth(), entity.getBbHeight());
				double hover = (Mth.sin((float)tileEntity.hoverStep + partialTicks) / 30d);
				tileEntity.hoverStep += hover * 0.01f;
				EntityRenderDispatcher entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher();

				if (maxScale > 1.0D)
					scale /= maxScale;

				matrix.translate(0, -0.1, 0);
				matrix.scale(scale, scale, scale);
				matrix.translate(0, (1 / scale), 0);

				if (tileEntity.isOriginal() && partialTicks > 0.95f)
					entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.005f, 10, ColourUtil.RGB(255, 200, 0)), tileEntity.getBlockPos().getX() + 0.5f, tileEntity.getBlockPos().getY() + 0.9 + ((entity.getBbHeight() / 2f) * scale), tileEntity.getBlockPos().getZ() + 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f);

				if (AoAConfig.CLIENT.rotatingTrophies.get())
					matrix.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, tileEntity.getPrevMobRotation(), tileEntity.getMobRotation()) * 30.0F));

				entityRenderer.setRenderShadow(false);
				entityRenderer.render(entity, 0, 0, 0, 0, 0, matrix, buffer, combinedLight);
				entityRenderer.setRenderShadow(true);
				matrix.popPose();
			}
			catch (Exception ex) {}
		}
	}
}
