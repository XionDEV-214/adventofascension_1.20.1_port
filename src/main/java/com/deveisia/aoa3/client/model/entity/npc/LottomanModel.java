package com.deveisia.aoa3.client.model.entity.npc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class LottomanModel extends LegacyEntityModel<Mob> {
	private LegacyModelPart tophat2;
	private LegacyModelPart body;
	private LegacyModelPart rightArm;
	private LegacyModelPart leftArm;
	private LegacyModelPart rightLeg;
	private LegacyModelPart leftLeg;
	private LegacyModelPart tophat;
	private LegacyModelPart head;

	public LottomanModel() {
		texWidth = 128;
		texHeight = 32;
		(tophat2 = new LegacyModelPart(this, 34, 0)).addBox(-6.0f, -8.0f, -6.0f, 12, 2, 12);
		tophat2.setPos(0.0f, 0.0f, 0.0f);
		tophat2.setTexSize(128, 32);
		tophat2.mirror = true;
		setRotation(tophat2, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-2.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(2.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(tophat = new LegacyModelPart(this, 88, 0)).addBox(-4.0f, -14.0f, -4.0f, 8, 6, 8);
		tophat.setPos(0.0f, 0.0f, 0.0f);
		tophat.setTexSize(128, 32);
		tophat.mirror = true;
		setRotation(tophat, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		tophat2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		tophat.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		tophat.yRot = netHeadYaw / 57.295776f;
		tophat.xRot = headPitch / 54.11268f;
		tophat2.yRot = netHeadYaw / 57.295776f;
		tophat2.xRot = headPitch / 54.11268f;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
