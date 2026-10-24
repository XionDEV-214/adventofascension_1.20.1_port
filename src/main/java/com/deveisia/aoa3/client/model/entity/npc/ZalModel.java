package com.deveisia.aoa3.client.model.entity.npc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ZalModel extends LegacyEntityModel<Mob> {
	private LegacyModelPart head;
	private LegacyModelPart rightArm;
	private LegacyModelPart leftArm;
	private LegacyModelPart rightLeg;
	private LegacyModelPart leftLeg;
	private LegacyModelPart body1;
	private LegacyModelPart body2;
	private LegacyModelPart body3;

	public ZalModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, -8.0f, -4.0f, 4, 8, 8);
		head.setPos(0.0f, 17.0f, -0.4666667f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 108, 15)).addBox(-2.0f, -2.0f, -2.0f, 2, 12, 2);
		rightArm.setPos(-3.0f, 9.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 108, 15)).addBox(0.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm.setPos(3.0f, 9.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 88, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 26, 4);
		rightLeg.setPos(-8.0f, -2.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 88, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 26, 4);
		leftLeg.setPos(8.0f, -2.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
		body1.setPos(0.0f, -2.0f, 0.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 34, 0)).addBox(-1.0f, 0.0f, -2.0f, 10, 4, 5);
		body2.setPos(-4.0f, 3.0f, -0.5f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 34, 10)).addBox(0.0f, 0.0f, -2.0f, 20, 4, 5);
		body3.setPos(-10.0f, -6.0f, -0.5f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
