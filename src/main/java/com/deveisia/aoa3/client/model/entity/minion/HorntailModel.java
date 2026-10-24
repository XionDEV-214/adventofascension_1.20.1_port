package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class HorntailModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart head2;

	public HorntailModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 32)).addBox(-7.0f, -10.0f, -4.0f, 14, 5, 8);
		head.setPos(0.0f, 4.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 40, 47)).addBox(-1.0f, 0.0f, -2.0f, 2, 7, 2);
		body.setPos(3.0f, 22.0f, 4.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 39, 0)).addBox(-1.0f, -1.0f, -2.0f, 2, 8, 4);
		rightArm.setPos(-5.0f, 5.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 39, 0)).addBox(-1.0f, -1.0f, -2.0f, 2, 8, 4);
		leftArm.setPos(5.0f, 5.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 15)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-6.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 15)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(6.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 32, 12)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 7);
		body2.setPos(0.0f, 4.0f, -1.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 16, 46)).addBox(-4.0f, 0.0f, -2.0f, 6, 6, 5);
		body3.setPos(1.0f, 15.0f, 0.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.3839724f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 46, 37)).addBox(-4.0f, 0.0f, -2.0f, 4, 5, 3);
		body4.setPos(2.0f, 19.0f, 2.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.8726646f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 40, 47)).addBox(-7.0f, 0.0f, -2.0f, 2, 7, 2);
		body5.setPos(3.0f, 22.0f, 4.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 8);
		head2.setPos(0.0f, 4.0f, 0.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		head2.yRot = netHeadYaw / (float)(180f / Math.PI);
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightArm.yRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm.yRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg.yRot = 0.0f;
	}
}
