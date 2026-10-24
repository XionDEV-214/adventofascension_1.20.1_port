package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class UrvModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart leftArm5;
	private final LegacyModelPart leftArm6;
	private final LegacyModelPart leftArm7;
	private final LegacyModelPart leftArm8;
	private final LegacyModelPart leftArm9;
	private final LegacyModelPart leftArm10;
	private final LegacyModelPart leftArm11;

	public UrvModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 9, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 0, 24)).addBox(-8.0f, 0.0f, -4.0f, 16, 12, 8);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 32, 8)).addBox(-1.0f, -2.0f, -4.0f, 8, 8, 8);
		leftArm.setPos(9.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 40, 53)).addBox(-3.0f, 0.0f, -3.0f, 6, 4, 6);
		rightLeg.setPos(-6.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 40, 53)).addBox(-3.0f, 0.0f, -3.0f, 6, 4, 6);
		leftLeg.setPos(6.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 0, 45)).addBox(-5.0f, 4.0f, -5.0f, 10, 8, 10);
		leftLeg2.setPos(6.0f, 12.0f, 0.0f);
		leftLeg2.setTexSize(64, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 0, 45)).addBox(-5.0f, 4.0f, -5.0f, 10, 8, 10);
		rightLeg2.setPos(-6.0f, 12.0f, 0.0f);
		rightLeg2.setTexSize(64, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 12, 17)).addBox(5.5f, 14.5f, -10.0f, 1, 1, 4);
		leftArm3.setPos(9.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new LegacyModelPart(this, 31, 45)).addBox(8.0f, 5.0f, -2.0f, 2, 4, 4);
		leftArm4.setPos(9.0f, 2.0f, 0.0f);
		leftArm4.setTexSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new LegacyModelPart(this, 48, 25)).addBox(4.0f, -3.0f, -2.0f, 4, 20, 4);
		leftArm5.setPos(9.0f, 2.0f, 0.0f);
		leftArm5.setTexSize(64, 64);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new LegacyModelPart(this, 31, 45)).addBox(8.0f, -1.0f, -2.0f, 2, 4, 4);
		leftArm6.setPos(9.0f, 2.0f, 0.0f);
		leftArm6.setTexSize(64, 64);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new LegacyModelPart(this, 31, 45)).addBox(8.0f, 11.0f, -2.0f, 2, 4, 4);
		leftArm7.setPos(9.0f, 2.0f, 0.0f);
		leftArm7.setTexSize(64, 64);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(leftArm8 = new LegacyModelPart(this, 48, 25)).addBox(10.0f, -3.0f, -2.0f, 4, 20, 4);
		leftArm8.setPos(9.0f, 2.0f, 0.0f);
		leftArm8.setTexSize(64, 64);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0f, 0.0f, 0.0f);
		(leftArm9 = new LegacyModelPart(this, 0, 17)).addBox(5.0f, 14.0f, -6.0f, 2, 2, 4);
		leftArm9.setPos(9.0f, 2.0f, 0.0f);
		leftArm9.setTexSize(64, 64);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0f, 0.0f, 0.0f);
		(leftArm10 = new LegacyModelPart(this, 0, 17)).addBox(11.0f, 14.0f, -6.0f, 2, 2, 4);
		leftArm10.setPos(9.0f, 2.0f, 0.0f);
		leftArm10.setTexSize(64, 64);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0f, 0.0f, 0.0f);
		(leftArm11 = new LegacyModelPart(this, 12, 17)).addBox(11.5f, 14.5f, -10.0f, 1, 1, 4);
		leftArm11.setPos(9.0f, 2.0f, 0.0f);
		leftArm11.setTexSize(64, 64);
		leftArm11.mirror = true;
		setRotation(leftArm11, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm3.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
		leftArm4.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm4.zRot = 0.0f;
		leftArm5.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm5.zRot = 0.0f;
		leftArm6.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm6.zRot = 0.0f;
		leftArm7.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm7.zRot = 0.0f;
		leftArm8.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm8.zRot = 0.0f;
		leftArm9.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm9.zRot = 0.0f;
		leftArm10.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm10.zRot = 0.0f;
		leftArm11.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm11.zRot = 0.0f;
	}
}
