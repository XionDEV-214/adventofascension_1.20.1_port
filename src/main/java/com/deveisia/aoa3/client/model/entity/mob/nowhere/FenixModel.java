package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class FenixModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart leftLeg3;
	private final LegacyModelPart rightLeg3;
	private final LegacyModelPart leftLeg4;
	private final LegacyModelPart rightLeg4;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart leftArm3;

	public FenixModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 7);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 57, 16)).addBox(1.5f, 6.0f, -7.0f, 1, 12, 1);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		rightLeg.setPos(-2.0f, 11.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		leftLeg.setPos(2.0f, 11.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		rightLeg2.setPos(-2.0f, 11.0f, 0.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, -0.4363323f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		leftLeg2.setPos(2.0f, 11.0f, 0.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, -0.4363323f, 0.0f, 0.0f);
		(leftLeg3 = new LegacyModelPart(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		leftLeg3.setPos(2.0f, 11.0f, 0.0f);
		leftLeg3.setTexSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.4363323f, 0.0f, 0.0f);
		(rightLeg3 = new LegacyModelPart(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		rightLeg3.setPos(-2.0f, 11.0f, 0.0f);
		rightLeg3.setTexSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.4363323f, 0.0f, 0.0f);
		(leftLeg4 = new LegacyModelPart(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		leftLeg4.setPos(2.0f, 11.0f, 0.0f);
		leftLeg4.setTexSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new LegacyModelPart(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		rightLeg4.setPos(-2.0f, 11.0f, 0.0f);
		rightLeg4.setTexSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setPos(-5.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, -0.6981317f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setPos(5.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, -0.6981317f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 57, 16)).addBox(-2.5f, 6.0f, -7.0f, 1, 12, 1);
		rightArm3.setPos(-5.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		leftArm3.setPos(5.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount - 0.436f;
		rightLeg2.yRot = 0.0f;
		rightLeg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount + 0.436f;
		rightLeg3.yRot = 0.0f;
		rightLeg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg4.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.436f;
		leftLeg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount + 0.436f;
		leftLeg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
