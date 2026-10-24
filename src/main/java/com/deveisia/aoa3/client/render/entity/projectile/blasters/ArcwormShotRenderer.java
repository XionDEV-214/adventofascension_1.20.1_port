package com.deveisia.aoa3.client.render.entity.projectile.blasters;

import com.deveisia.aoa3.client.model.entity.projectile.ArcwormShotModel;
import com.deveisia.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.content.entity.projectile.blaster.ArcwormShotEntity;
import com.deveisia.aoa3.util.ColourUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ArcwormShotRenderer extends ModelledProjectileRenderer<ArcwormShotEntity> {
	public ArcwormShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, new ArcwormShotModel(), textureResource);
	}

	@Override
	protected void preRenderCallback(ArcwormShotEntity entity, PoseStack matrix, float partialTicks) {
		matrix.mulPose(Axis.YP.rotationDegrees(180 + entity.yRotO + (entity.getYRot() - entity.yRotO) * partialTicks));
		matrix.mulPose(Axis.XP.rotationDegrees(entity.xRotO + (entity.getXRot() - entity.xRotO) * partialTicks));
	}

	@Override
	public void render(ArcwormShotEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 3; i++) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RED), true, entity.getX(), entity.getY() + 0.45, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RGB(223, 153, 0)), true, entity.getX(), entity.getY() + 0.3, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.YELLOW), true, entity.getX(), entity.getY() + 0.15, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.GREEN), true, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.CYAN), true, entity.getX(), entity.getY() - 0.15, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.BLUE), true, entity.getX(), entity.getY() - 0.3, entity.getZ(), 0, 0, 0);
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.75f, 20, ColourUtil.RGB(193, 64, 215)), true, entity.getX(), entity.getY() - 0.45, entity.getZ(), 0, 0, 0);
		}
	}
}
