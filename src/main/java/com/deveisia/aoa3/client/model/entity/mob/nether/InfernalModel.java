package com.deveisia.aoa3.client.model.entity.mob.nether;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class InfernalModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart leftArm;

	public InfernalModel() {
		texWidth = 128;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, -10.0f, -4.0f, 10, 10, 8);
		head.setPos(0.0f, 4.0f, -7.533333f);
		head.setTexSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 34, 19)).addBox(-7.0f, 0.0f, -5.0f, 14, 5, 10);
		body.setPos(0.0f, -2.0f, -6.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.6981317f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 100, 0)).addBox(-6.0f, -2.0f, -3.0f, 6, 23, 6);
		rightArm.setPos(-9.0f, 3.0f, -4.0f);
		rightArm.setTexSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightLeg.setPos(-5.0f, 14.0f, 0.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftLeg.setPos(5.0f, 14.0f, 0.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 34, 39)).addBox(-9.0f, 0.0f, -6.0f, 18, 12, 12);
		body2.setPos(0.0f, 0.0f, -4.0f);
		body2.setTexSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.3490659f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 39, 0)).addBox(-8.0f, 0.0f, -5.0f, 16, 5, 10);
		body3.setPos(0.0f, 9.0f, 0.0f);
		body3.setTexSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 100, 0)).addBox(-1.0f, -2.0f, -3.0f, 6, 23, 6);
		leftArm.setPos(10.0f, 3.0f, -4.0f);
		leftArm.setTexSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
