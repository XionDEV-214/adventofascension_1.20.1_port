package com.deveisia.aoa3.client.render.entity.animal;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.entity.animal.ShinySquidModel;
import com.deveisia.aoa3.content.entity.animal.ShinySquidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

public class ShinySquidRenderer extends MobRenderer<ShinySquidEntity, ShinySquidModel> {
	private static final ResourceLocation TEXTURE = AdventOfAscension.id("textures/entity/animal/shiny_squid.png");

	public ShinySquidRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ShinySquidModel(), 0.7f);
	}

	@Override
	protected void setupRotations(ShinySquidEntity squid, PoseStack matrixStack, float pAgeInTicks, float pRotationYaw, float partialTicks) {
		matrixStack.translate(0, 0.5d, 0);
		matrixStack.mulPose(Axis.YP.rotationDegrees(180 - pRotationYaw));
		matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, squid.xBodyRotO, squid.xBodyRot)));
		matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, squid.zBodyRotO, squid.zBodyRot)));
		matrixStack.translate(0, -1.2f, 0);
	}

	@Override
	protected float getBob(ShinySquidEntity squid, float partialTicks) {
		return Mth.lerp(partialTicks, squid.oldTentacleAngle, squid.tentacleAngle);
	}

	@Override
	public ResourceLocation getTextureLocation(ShinySquidEntity pEntity) {
		return TEXTURE;
	}
}
