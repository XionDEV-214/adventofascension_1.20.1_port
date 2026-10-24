package com.deveisia.aoa3.client.model.entity.mob.celeve;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class JumboModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart head4;

	public JumboModel() {
		texWidth = 128;
		texHeight = 64;
		(head = new LegacyModelPart(this, 43, 17)).addBox(-5.0f, -14.0f, -5.0f, 10, 4, 10);
		head.setPos(0.0f, -4.0f, 0.0f);
		head.setTexSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 40, 35)).addBox(-1.0f, 4.0f, -4.0f, 3, 2, 1);
		body.setPos(0.0f, -4.0f, 0.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 67, 47)).addBox(-5.0f, 8.0f, -17.0f, 8, 8, 8);
		rightArm.setPos(-8.0f, -2.0f, 0.0f);
		rightArm.setTexSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 40)).addBox(-2.0f, -2.0f, -3.0f, 6, 16, 6);
		leftArm.setPos(8.0f, -2.0f, 0.0f);
		leftArm.setTexSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 33, 0)).addBox(-1.5f, -6.0f, -7.0f, 3, 3, 2);
		head2.setPos(0.0f, -4.0f, 0.0f);
		head2.setTexSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 42, 0)).addBox(-6.0f, -10.0f, -6.0f, 12, 2, 12);
		head3.setPos(0.0f, -4.0f, 0.0f);
		head3.setTexSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 2, 41)).addBox(-6.0f, 0.0f, -3.0f, 12, 16, 6);
		body2.setPos(0.0f, -4.0f, 0.0f);
		body2.setTexSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 26, 34)).addBox(-3.0f, 4.0f, -5.0f, 2, 2, 2);
		body3.setPos(0.0f, -4.0f, 0.0f);
		body3.setTexSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 34, 39)).addBox(-3.0f, 1.0f, -4.0f, 2, 3, 1);
		body4.setPos(0.0f, -4.0f, 0.0f);
		body4.setTexSize(128, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 34, 39)).addBox(-3.0f, 6.0f, -4.0f, 2, 3, 1);
		body5.setPos(0.0f, -4.0f, 0.0f);
		body5.setTexSize(128, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 40, 35)).addBox(-6.0f, 4.0f, -4.0f, 3, 2, 1);
		body6.setPos(0.0f, -4.0f, 0.0f);
		body6.setTexSize(128, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 40)).addBox(-4.0f, -2.0f, -3.0f, 6, 16, 6);
		rightArm2.setPos(-8.0f, -2.0f, 0.0f);
		rightArm2.setTexSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 67, 34)).addBox(-2.0f, 11.0f, -9.0f, 2, 2, 6);
		rightArm3.setPos(-8.0f, -2.0f, 0.0f);
		rightArm3.setTexSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, -8.0f, -5.0f, 10, 8, 10);
		head4.setPos(0.0f, -4.0f, 0.0f);
		head4.setTexSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 54.11268f;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
