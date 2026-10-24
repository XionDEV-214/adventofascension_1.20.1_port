package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.boss.RockRiderEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class RockRiderModel extends LegacyEntityModel<RockRiderEntity> {
	private final LegacyModelPart headrider;
	private final LegacyModelPart bodyrider;
	private final LegacyModelPart rightArmrider;
	private final LegacyModelPart leftArmrider;
	private final LegacyModelPart rightLegrider;
	private final LegacyModelPart leftLegrider;
	private final LegacyModelPart body1;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart head;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;

	public RockRiderModel() {
		texWidth = 128;
		texHeight = 32;
		(headrider = new LegacyModelPart(this, 96, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		headrider.setPos(0.0f, -4.0f, 6.0f);
		headrider.setTexSize(128, 32);
		headrider.mirror = true;
		setRotation(headrider, 0.0f, 0.0f, 0.0f);
		(bodyrider = new LegacyModelPart(this, 70, 20)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		bodyrider.setPos(0.0f, -4.0f, 5.0f);
		bodyrider.setTexSize(128, 32);
		bodyrider.mirror = true;
		setRotation(bodyrider, 0.3490659f, 0.0f, 0.0f);
		(rightArmrider = new LegacyModelPart(this, 112, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArmrider.setPos(-5.0f, -2.0f, 6.0f);
		rightArmrider.setTexSize(128, 32);
		rightArmrider.mirror = true;
		setRotation(rightArmrider, -1.396263f, 0.0f, 0.0f);
		(leftArmrider = new LegacyModelPart(this, 112, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArmrider.setPos(5.0f, -2.0f, 6.0f);
		leftArmrider.setTexSize(128, 32);
		leftArmrider.mirror = true;
		setRotation(leftArmrider, -1.396263f, 0.0f, 0.0f);
		(rightLegrider = new LegacyModelPart(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		rightLegrider.setPos(-3.0f, 3.0f, 8.0f);
		rightLegrider.setTexSize(128, 32);
		rightLegrider.mirror = true;
		setRotation(rightLegrider, 0.148353f, 0.0f, 0.0f);
		(leftLegrider = new LegacyModelPart(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		leftLegrider.setPos(3.0f, 3.0f, 8.0f);
		leftLegrider.setTexSize(128, 32);
		leftLegrider.mirror = true;
		setRotation(leftLegrider, 0.148353f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 0, 16)).addBox(-7.0f, 0.0f, -5.0f, 14, 6, 10);
		body1.setPos(0.0f, 7.0f, -5.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 52, 2)).addBox(-3.0f, -2.0f, -2.0f, 4, 22, 4);
		rightArm.setPos(-8.0f, 4.0f, -2.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 52, 2)).addBox(-1.0f, -2.0f, -2.0f, 4, 22, 4);
		leftArm.setPos(8.0f, 4.0f, -2.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head.setPos(0.0f, 7.0f, -5.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg.setPos(-6.0f, 16.0f, 6.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg.setPos(6.0f, 16.0f, 6.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 8, 22)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
		body2.setPos(0.0f, 13.0f, 6.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 4, 20)).addBox(-5.0f, 0.0f, -3.0f, 10, 6, 6);
		body3.setPos(0.0f, 10.0f, 3.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.5235988f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 1, 18)).addBox(-6.0f, 0.0f, -4.0f, 12, 6, 8);
		body4.setPos(0.0f, 7.0f, -1.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.047198f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		headrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		bodyrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArmrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArmrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLegrider.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(RockRiderEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		headrider.yRot = netHeadYaw / 57.295776f;
		headrider.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
