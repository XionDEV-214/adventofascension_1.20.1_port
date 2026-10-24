package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class UrsaModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart Torso;
	private final LegacyModelPart body;
	private final LegacyModelPart Left_Back_Quad;
	private final LegacyModelPart Right_Back_Quad;
	private final LegacyModelPart Left_Back_Leg;
	private final LegacyModelPart Right_Back_Leg;
	private final LegacyModelPart Left_Front_Quad;
	private final LegacyModelPart Right_Front_Quad;
	private final LegacyModelPart Left_Front_Leg;
	private final LegacyModelPart Right_Front_Leg;
	private final LegacyModelPart tail;
	private final LegacyModelPart head;
	private final LegacyModelPart Mouth;
	private final LegacyModelPart leftEar;
	private final LegacyModelPart rightEar;

	public UrsaModel() {
		texWidth = 128;
		texHeight = 64;
		(Torso = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, -6.0f, -13.0f, 12, 12, 12);
		Torso.setPos(0.0f, 6.0f, 1.0f);
		Torso.setTexSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 49, 0)).addBox(-5.0f, -5.0f, 0.0f, 10, 10, 10);
		body.setPos(0.0f, 7.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Left_Back_Quad = new LegacyModelPart(this, 49, 20)).addBox(0.0f, -3.0f, -3.0f, 6, 6, 6);
		Left_Back_Quad.setPos(4.0f, 8.0f, 5.0f);
		Left_Back_Quad.setTexSize(64, 32);
		Left_Back_Quad.mirror = true;
		setRotation(Left_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Back_Quad = new LegacyModelPart(this, 49, 20)).addBox(-6.0f, -3.0f, -3.0f, 6, 6, 6);
		Right_Back_Quad.setPos(-4.0f, 8.0f, 5.0f);
		Right_Back_Quad.setTexSize(64, 32);
		Right_Back_Quad.mirror = true;
		setRotation(Right_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Back_Leg = new LegacyModelPart(this, 111, 17)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Back_Leg.setPos(5.0f, 7.0f, 5.0f);
		Left_Back_Leg.setTexSize(64, 32);
		Left_Back_Leg.mirror = true;
		setRotation(Left_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Back_Leg = new LegacyModelPart(this, 111, 17)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Back_Leg.setPos(-5.0f, 7.0f, 4.066667f);
		Right_Back_Leg.setTexSize(64, 32);
		Right_Back_Leg.mirror = true;
		setRotation(Right_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Left_Front_Quad = new LegacyModelPart(this, 90, 0)).addBox(0.0f, -4.0f, -4.0f, 6, 8, 8);
		Left_Front_Quad.setPos(5.0f, 7.0f, -7.0f);
		Left_Front_Quad.setTexSize(64, 32);
		Left_Front_Quad.mirror = true;
		setRotation(Left_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Front_Quad = new LegacyModelPart(this, 90, 0)).addBox(-6.0f, -4.0f, -4.0f, 6, 8, 8);
		Right_Front_Quad.setPos(-5.0f, 7.0f, -7.0f);
		Right_Front_Quad.setTexSize(64, 32);
		Right_Front_Quad.mirror = true;
		setRotation(Right_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Front_Leg = new LegacyModelPart(this, 0, 25)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Front_Leg.setPos(6.0f, 8.0f, -7.0f);
		Left_Front_Leg.setTexSize(64, 32);
		Left_Front_Leg.mirror = true;
		setRotation(Left_Front_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Front_Leg = new LegacyModelPart(this, 0, 25)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Front_Leg.setPos(-6.0f, 7.0f, -7.0f);
		Right_Front_Leg.setTexSize(64, 32);
		Right_Front_Leg.mirror = true;
		setRotation(Right_Front_Leg, 0.0f, 0.0f, 0.0f);
		(tail = new LegacyModelPart(this, 49, 33)).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 13);
		tail.setPos(0.0f, 4.0f, 8.0f);
		tail.setTexSize(64, 32);
		tail.mirror = true;
		setRotation(tail, -0.7435722f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 17, 25)).addBox(-4.0f, -4.0f, -6.0f, 8, 9, 7);
		head.setPos(0.0f, 5.0f, -13.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Mouth = new LegacyModelPart(this, 0, 44)).addBox(-3.0f, -2.0f, -5.0f, 6, 4, 4);
		Mouth.setPos(0.0f, 7.0f, -18.0f);
		Mouth.setTexSize(64, 32);
		Mouth.mirror = true;
		setRotation(Mouth, 0.0f, 0.0f, 0.0f);
		(leftEar = new LegacyModelPart(this, 21, 44)).addBox(-1.0f, -3.0f, -1.0f, 3, 3, 2);
		leftEar.setPos(3.0f, 2.0f, -14.0f);
		leftEar.setTexSize(64, 32);
		leftEar.mirror = true;
		setRotation(leftEar, 0.0f, 0.0f, 0.0f);
		(rightEar = new LegacyModelPart(this, 21, 44)).addBox(-2.0f, -3.0f, -1.0f, 3, 3, 2);
		rightEar.setPos(-3.0f, 2.0f, -14.0f);
		rightEar.setTexSize(64, 32);
		rightEar.mirror = true;
		setRotation(rightEar, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Left_Front_Leg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Front_Leg.yRot = 0.0f;
		Left_Front_Quad.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Front_Quad.yRot = 0.0f;
		Left_Back_Leg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Back_Leg.yRot = 0.0f;
		Left_Back_Quad.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Back_Quad.yRot = 0.0f;
		Right_Front_Leg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Front_Leg.yRot = 0.0f;
		Right_Front_Quad.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Front_Quad.yRot = 0.0f;
		Right_Back_Leg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Back_Leg.yRot = 0.0f;
		Right_Back_Quad.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Back_Quad.yRot = 0.0f;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Torso.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Back_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Back_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Back_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Back_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Front_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Front_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Front_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Front_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		tail.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Mouth.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightEar.render(matrix, buffer, light, overlay, red, green, blue, alpha);

	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
