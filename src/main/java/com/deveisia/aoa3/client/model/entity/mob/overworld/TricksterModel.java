package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class TricksterModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;

	public TricksterModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 10)).addBox(2.0f, 3.0f, -1.0f, 2, 2, 2);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 22, 2);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 22, 2);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		rightLeg.setPos(-2.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leftLeg.setPos(2.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 16, 16)).addBox(-2.0f, 0.0f, -1.0f, 4, 12, 2);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 16, 10)).addBox(-4.0f, 3.0f, -1.0f, 2, 2, 2);
		body3.setPos(0.0f, 0.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
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

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
}
