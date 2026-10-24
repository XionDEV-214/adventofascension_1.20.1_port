package com.deveisia.aoa3.client.model.entity.mob.gardencia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SquasherModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm2;

	public SquasherModel() {
		texWidth = 128;
		texHeight = 32;
		(body = new LegacyModelPart(this, 107, 1)).addBox(-5.0f, -1.0f, -4.0f, 4, 1, 4);
		body.setPos(3.0f, -9.0f, 3.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 18, 20)).addBox(-3.0f, 3.0f, 1.0f, 4, 6, 4);
		rightArm.setPos(-7.0f, 5.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 18, 20)).addBox(-1.0f, 3.0f, 1.0f, 4, 6, 4);
		leftArm.setPos(7.0f, 5.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-6.0f, 12.0f, 1.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(6.0f, 12.0f, 1.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 78, 11)).addBox(-6.0f, 0.0f, -5.0f, 12, 9, 12);
		body2.setPos(0.0f, 3.0f, 0.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, 0.0f, -5.0f, 10, 2, 10);
		body3.setPos(1.0f, 12.0f, 1.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 35, 20)).addBox(-6.0f, 0.0f, -5.0f, 10, 2, 10);
		body4.setPos(1.0f, 1.0f, 1.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 41, 0)).addBox(-6.0f, 0.0f, -5.0f, 8, 9, 8);
		body5.setPos(2.0f, -8.0f, 2.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 78, 1)).addBox(-5.0f, -1.0f, -4.0f, 6, 1, 6);
		body6.setPos(2.0f, -8.0f, 2.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 18, 20)).addBox(-1.0f, 3.0f, -3.0f, 4, 6, 4);
		leftArm2.setPos(7.0f, 5.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 18, 20)).addBox(-3.0f, 3.0f, -3.0f, 4, 6, 4);
		rightArm2.setPos(-7.0f, 5.0f, 0.0f);
		rightArm2.setTexSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}
}
