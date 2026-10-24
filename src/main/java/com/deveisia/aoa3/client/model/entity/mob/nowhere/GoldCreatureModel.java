package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class GoldCreatureModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftLeg2;

	public GoldCreatureModel() {
		texWidth = 64;
		texHeight = 64;
		(body = new LegacyModelPart(this, 0, 31)).addBox(-4.0f, 0.0f, -3.0f, 10, 12, 6);
		body.setPos(-1.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 1)).addBox(-4.0f, 11.0f, -3.0f, 6, 6, 6);
		rightArm.setPos(-7.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 1)).addBox(-2.0f, 11.0f, -3.0f, 6, 6, 6);
		leftArm.setPos(7.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 8.0f, -3.0f, 6, 4, 6);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 8.0f, -3.0f, 6, 4, 6);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, 1.0f, -2.0f, 4, 10, 4);
		rightArm2.setPos(-7.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, 1.0f, -2.0f, 4, 10, 4);
		leftArm2.setPos(7.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 40, 1)).addBox(-2.0f, -5.0f, -3.0f, 6, 6, 6);
		leftArm3.setPos(7.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 40, 1)).addBox(-4.0f, -5.0f, -3.0f, 6, 6, 6);
		rightArm3.setPos(-7.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 0, 2)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg2.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 0, 2)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg2.setPos(3.0f, 12.0f, 0.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
