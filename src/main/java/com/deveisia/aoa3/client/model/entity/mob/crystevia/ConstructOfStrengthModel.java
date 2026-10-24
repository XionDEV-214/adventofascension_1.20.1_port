package com.deveisia.aoa3.client.model.entity.mob.crystevia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ConstructOfStrengthModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart r1;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart head;
	private final LegacyModelPart r2;
	private final LegacyModelPart r3;
	private final LegacyModelPart r4;

	public ConstructOfStrengthModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(r1 = new LegacyModelPart(this, 0, 36)).addBox(6.0f, -7.0f, -4.0f, 2, 2, 8);
		r1.setPos(0.0f, -6.0f, 0.0f);
		r1.setTexSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 38)).addBox(-4.0f, 0.0f, -2.0f, 16, 18, 8);
		body.setPos(-4.0f, -6.0f, -2.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 25, 24)).addBox(2.0f, -1.0f, -3.0f, 1, 5, 6);
		rightArm.setPos(-11.0f, -3.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 25, 24)).addBox(-3.0f, -1.0f, -3.0f, 1, 5, 6);
		leftArm.setPos(11.0f, -3.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 18)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightLeg.setPos(-4.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 18)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftLeg.setPos(4.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 2)).addBox(-4.0f, -2.0f, -3.0f, 6, 23, 6);
		rightArm2.setPos(-11.0f, -3.0f, 0.0f);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 40, 2)).addBox(-2.0f, -2.0f, -3.0f, 6, 23, 6);
		leftArm2.setPos(11.0f, -3.0f, 0.0f);
		leftArm2.setTexSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -6.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 42, 33)).addBox(-4.0f, -7.0f, -8.0f, 8, 2, 2);
		r2.setPos(0.0f, -6.0f, 0.0f);
		r2.setTexSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new LegacyModelPart(this, 42, 33)).addBox(-4.0f, -7.0f, 6.0f, 8, 2, 2);
		r3.setPos(0.0f, -6.0f, 0.0f);
		r3.setTexSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new LegacyModelPart(this, 0, 36)).addBox(-8.0f, -7.0f, -4.0f, 2, 2, 8);
		r4.setPos(0.0f, -6.0f, 0.0f);
		r4.setTexSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		r1.yRot = ageInTicks * 0.067f * 1.25f;
		r2.yRot = ageInTicks * 0.067f * 1.25f;
		r3.yRot = ageInTicks * 0.067f * 1.25f;
		r4.yRot = ageInTicks * 0.067f * 1.25f;
	}
}
