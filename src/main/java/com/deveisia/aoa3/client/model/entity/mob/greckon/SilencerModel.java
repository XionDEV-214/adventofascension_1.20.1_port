package com.deveisia.aoa3.client.model.entity.mob.greckon;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SilencerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart rightArm4;
	private final LegacyModelPart leftArm5;
	private final LegacyModelPart rightArm5;
	private final LegacyModelPart leftArm6;
	private final LegacyModelPart rightArm6;
	private final LegacyModelPart leftArm7;
	private final LegacyModelPart rightArm7;

	public SilencerModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 100, 22)).addBox(-8.0f, -12.0f, -1.0f, 2, 3, 2);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 100, 22)).addBox(6.0f, -12.0f, -1.0f, 2, 3, 2);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-2.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(2.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 43, 11)).addBox(-5.0f, -2.0f, -2.0f, 6, 4, 4);
		rightArm2.setPos(-5.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 43, 11)).addBox(-1.0f, -2.0f, -2.0f, 6, 4, 4);
		leftArm2.setPos(5.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 63, 22)).addBox(5.0f, -4.0f, -2.0f, 4, 6, 4);
		leftArm3.setPos(5.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 63, 22)).addBox(-9.0f, -4.0f, -2.0f, 4, 6, 4);
		rightArm3.setPos(-5.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new LegacyModelPart(this, 36, 0)).addBox(5.5f, -8.0f, -2.0f, 3, 3, 1);
		leftArm4.setPos(5.0f, 2.0f, 0.0f);
		leftArm4.setTexSize(64, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new LegacyModelPart(this, 36, 0)).addBox(-8.5f, -8.0f, -2.0f, 3, 3, 1);
		rightArm4.setPos(-5.0f, 2.0f, 0.0f);
		rightArm4.setTexSize(64, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new LegacyModelPart(this, 115, 22)).addBox(4.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm5.setPos(5.0f, 2.0f, 0.0f);
		leftArm5.setTexSize(64, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new LegacyModelPart(this, 115, 22)).addBox(-10.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm5.setPos(-5.0f, 2.0f, 0.0f);
		rightArm5.setTexSize(64, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new LegacyModelPart(this, 115, 22)).addBox(9.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm6.setPos(5.0f, 2.0f, 0.0f);
		leftArm6.setTexSize(64, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(rightArm6 = new LegacyModelPart(this, 115, 22)).addBox(-5.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm6.setPos(-5.0f, 2.0f, 0.0f);
		rightArm6.setTexSize(64, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new LegacyModelPart(this, 81, 22)).addBox(4.0f, -9.0f, -1.0f, 6, 5, 2);
		leftArm7.setPos(5.0f, 2.0f, 0.0f);
		leftArm7.setTexSize(64, 32);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(rightArm7 = new LegacyModelPart(this, 81, 22)).addBox(-10.0f, -9.0f, -1.0f, 6, 5, 2);
		rightArm7.setPos(-5.0f, 2.0f, 0.0f);
		rightArm7.setTexSize(64, 32);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
	}
}
