package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class GoblinModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart head;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart Staff;
	private final LegacyModelPart rightArm;

	public GoblinModel() {
		this.texWidth = 64;
		this.texHeight = 32;
		LegacyModelPart leftEar = new LegacyModelPart(this, 44, 0);
		leftEar.setPos(0.0F, 3.0F, 0.0F);
		leftEar.addBox(-8.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(leftEar, 0.0F, 0.0F, 0.8922123136195012F);
		this.head = new LegacyModelPart(this, 0, 0);
		this.head.setPos(0.0F, 3.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		LegacyModelPart nose = new LegacyModelPart(this, 33, 8);
		nose.setPos(0.0F, 3.0F, -1.0F);
		nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		this.rightLeg = new LegacyModelPart(this, 18, 18);
		this.rightLeg.setPos(-2.0F, 14.0F, 1.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightArm = new LegacyModelPart(this, 0, 18);
		this.rightArm.setPos(-4.0F, 4.0F, 1.0F);
		this.rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.leftLeg = new LegacyModelPart(this, 18, 18);
		this.leftLeg.setPos(2.0F, 14.0F, 1.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body = new LegacyModelPart(this, 36, 15);
		this.body.setPos(0.0F, 3.0F, 1.0F);
		this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.0F);
		LegacyModelPart rightEar = new LegacyModelPart(this, 33, 0);
		rightEar.setPos(0.0F, 3.0F, 0.0F);
		rightEar.addBox(4.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(rightEar, 0.0F, 0.0F, -0.8922123136195012F);
		this.leftArm = new LegacyModelPart(this, 0, 18);
		this.leftArm.setPos(4.0F, 4.0F, 1.0F);
		this.leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Staff = new LegacyModelPart(this, 59, 0);
		this.Staff.setPos(-4.0F, 4.0F, 1.0F);
		this.Staff.addBox(-2.5F, -9.0F, -5.0F, 1, 20, 1, 0.0F);
		this.setRotation(Staff, 0.43632999062538147F, 0.0F, 0.0F);
		this.head.addChild(leftEar);
		this.head.addChild(nose);
		this.head.addChild(rightEar);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Staff.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		Staff.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.43633f;
		Staff.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
