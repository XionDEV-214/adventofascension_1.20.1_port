package com.deveisia.aoa3.client.model.entity.mob.lborean;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class MermageModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart rightArm4;
	private final LegacyModelPart rightArm5;
	private final LegacyModelPart rightArm6;
	private final LegacyModelPart rightArm7;

	public MermageModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -2.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 3, 52)).addBox(-9.0f, 6.0f, -1.0f, 16, 8, 0);
		body.setPos(1.0f, 12.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.6981317f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 4)).addBox(-4.0f, -5.0f, -3.0f, 6, 3, 6);
		rightArm.setPos(-6.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 4)).addBox(-2.0f, -5.0f, -3.0f, 6, 3, 6);
		leftArm.setPos(6.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 13, 17)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setPos(0.0f, -2.0f, -1.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 16, 34)).addBox(-4.0f, 0.0f, -2.0f, 7, 7, 3);
		body3.setPos(0.5f, 8.0f, -1.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.2617994f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 16, 45)).addBox(-4.0f, 0.0f, -2.0f, 6, 5, 2);
		body4.setPos(1.0f, 12.0f, 0.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.6981317f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 40, 16)).addBox(-6.0f, 5.0f, -2.0f, 10, 2, 2);
		body5.setPos(1.0f, 12.0f, 0.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.6981317f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 35)).addBox(-2.0f, -21.0f, -9.0f, 2, 8, 2);
		rightArm2.setPos(-6.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.7853982f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 38, 47)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setPos(6.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 38, 47)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm3.setPos(-6.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new LegacyModelPart(this, 56, 28)).addBox(-2.0f, -11.0f, -9.0f, 2, 22, 2);
		rightArm4.setPos(-6.0f, 2.0f, 0.0f);
		rightArm4.setTexSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.7853982f, 0.0f, 0.0f);
		(rightArm5 = new LegacyModelPart(this, 40, 30)).addBox(-4.0f, -13.0f, -9.0f, 6, 2, 2);
		rightArm5.setPos(-6.0f, 2.0f, 0.0f);
		rightArm5.setTexSize(64, 64);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.7853982f, 0.0f, 0.0f);
		(rightArm6 = new LegacyModelPart(this, 40, 35)).addBox(-6.0f, -18.0f, -9.0f, 2, 7, 2);
		rightArm6.setPos(-6.0f, 2.0f, 0.0f);
		rightArm6.setTexSize(64, 64);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.7853982f, 0.0f, 0.0f);
		(rightArm7 = new LegacyModelPart(this, 40, 35)).addBox(2.0f, -18.0f, -9.0f, 2, 7, 2);
		rightArm7.setPos(-6.0f, 2.0f, 0.0f);
		rightArm7.setTexSize(64, 64);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.7853982f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.785f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		rightArm4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.785f;
		rightArm4.zRot = 0.0f;
		rightArm5.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.785f;
		rightArm5.zRot = 0.0f;
		rightArm6.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.785f;
		rightArm6.zRot = 0.0f;
		rightArm7.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.785f;
		rightArm7.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
	}
}
