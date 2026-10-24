package com.deveisia.aoa3.client.model.entity.mob.iromine;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class EnforcerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm3;

	public EnforcerModel() {
		texWidth = 256;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 12, 8);
		head.setPos(0.0f, -4.0f, 0.0f);
		head.setTexSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 85, 0)).addBox(-6.0f, -2.0f, -8.0f, 10, 2, 10);
		body.setPos(1.0f, 2.0f, 3.0f);
		body.setTexSize(256, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 2, 22)).addBox(-4.0f, -3.0f, -3.0f, 6, 4, 6);
		rightArm.setPos(-7.0f, 2.0f, 0.0f);
		rightArm.setTexSize(256, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 2, 22)).addBox(-2.0f, -3.0f, -3.0f, 6, 4, 6);
		leftArm.setPos(7.0f, 2.0f, 0.0f);
		leftArm.setTexSize(256, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 34, 0)).addBox(-6.0f, 0.0f, -6.0f, 12, 8, 12);
		body2.setPos(0.0f, 13.0f, 0.0f);
		body2.setTexSize(256, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 121, 13)).addBox(-6.0f, 0.0f, -6.0f, 10, 4, 10);
		body3.setPos(1.0f, 9.0f, 1.0f);
		body3.setTexSize(256, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 164, 0)).addBox(-6.0f, 0.0f, -6.0f, 8, 7, 8);
		body4.setPos(2.0f, 2.0f, 2.0f);
		body4.setTexSize(256, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 86, 19)).addBox(-3.0f, 10.0f, -2.0f, 1, 9, 4);
		rightArm2.setPos(-7.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(256, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 86, 19)).addBox(2.0f, 10.0f, -2.0f, 1, 9, 4);
		leftArm2.setPos(7.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(256, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 101, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm3.setPos(7.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(256, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 101, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm3.setPos(-7.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(256, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		leftArm3.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
	}
}
