package com.deveisia.aoa3.client.model.entity.mob.crystevia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ConstructOfSpeedModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart r1;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart hindleg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart head;
	private final LegacyModelPart r2;
	private final LegacyModelPart r3;

	public ConstructOfSpeedModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 32;
		(r1 = new LegacyModelPart(this, 37, 0)).addBox(2.0f, -9.0f, -1.0f, 2, 2, 2);
		r1.setPos(0.0f, -1.0f, -2.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 25, 1)).addBox(2.0f, 9.0f, 2.0f, 2, 3, 6);
		body.setPos(0.0f, 1.0f, -1.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(hindleg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		hindleg.setPos(0.0f, 12.0f, 6.0f);
		hindleg.setTexSize(64, 32);
		hindleg.mirror = true;
		setRotation(hindleg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(4.0f, 12.0f, -2.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setPos(0.0f, 0.0f, -1.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 25, 1)).addBox(-4.0f, 9.0f, 2.0f, 2, 3, 6);
		body3.setPos(0.0f, 1.0f, -1.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-4.0f, 12.0f, -2.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 8, 6);
		head.setPos(0.0f, 2.0f, -1.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 46, 0)).addBox(-2.0f, -10.0f, -2.0f, 4, 6, 4);
		r2.setPos(0.0f, -1.0f, -2.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new LegacyModelPart(this, 37, 0)).addBox(-4.0f, -9.0f, -1.0f, 2, 2, 2);
		r3.setPos(0.0f, -1.0f, -2.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hindleg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		hindleg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		r1.yRot = ageInTicks * 0.067f * 1.25f;
		r2.yRot = ageInTicks * 0.067f * 1.25f;
		r3.yRot = ageInTicks * 0.067f * 1.25f;
	}
}
