package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class EverbeastModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body1;
	private final LegacyModelPart rightArm1;
	private final LegacyModelPart leftArm1;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart leftArm5;
	private final LegacyModelPart leftArm6;
	private final LegacyModelPart head2;

	public EverbeastModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 23, 0)).addBox(0.0f, -6.5f, -3.5f, 3, 7, 7);
		head.setPos(0.0f, 2.0f, -2.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 43, 0)).addBox(-4.0f, 0.0f, -2.0f, 12, 6, 4);
		body1.setPos(-2.0f, 0.0f, 0.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new LegacyModelPart(this, 58, 16)).addBox(-2.0f, 10.0f, -1.0f, 2, 8, 2);
		rightArm1.setPos(-7.0f, 3.0f, 0.0f);
		rightArm1.setTexSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new LegacyModelPart(this, 75, 0)).addBox(3.0f, 12.0f, -1.0f, 2, 8, 2);
		leftArm1.setPos(7.0f, 2.0f, 0.0f);
		leftArm1.setTexSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 16, 22)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
		body2.setPos(0.0f, 6.0f, 0.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 72, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setPos(-7.0f, 3.0f, 0.0f);
		rightArm2.setTexSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 93, 0)).addBox(-1.0f, -5.0f, -4.0f, 6, 17, 8);
		leftArm2.setPos(7.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 84, 0)).addBox(-1.0f, 12.0f, 1.0f, 2, 4, 2);
		leftArm3.setPos(7.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new LegacyModelPart(this, 75, 0)).addBox(3.0f, 12.0f, 2.0f, 2, 8, 2);
		leftArm4.setPos(7.0f, 2.0f, 0.0f);
		leftArm4.setTexSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new LegacyModelPart(this, 75, 0)).addBox(3.0f, 12.0f, -4.0f, 2, 8, 2);
		leftArm5.setPos(7.0f, 2.0f, 0.0f);
		leftArm5.setTexSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new LegacyModelPart(this, 84, 0)).addBox(-1.0f, 12.0f, -3.0f, 2, 4, 2);
		leftArm6.setPos(7.0f, 2.0f, 0.0f);
		leftArm6.setTexSize(128, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 5, 6, 6);
		head2.setPos(0.0f, 2.0f, -2.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		rightArm1.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm1.zRot = 0.0f;
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		leftArm1.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm1.zRot = 0.0f;
		leftArm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		leftArm3.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
		leftArm4.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm4.zRot = 0.0f;
		leftArm5.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm5.zRot = 0.0f;
		leftArm6.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm6.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
