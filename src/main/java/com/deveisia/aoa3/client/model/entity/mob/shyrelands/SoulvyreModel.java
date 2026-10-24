package com.deveisia.aoa3.client.model.entity.mob.shyrelands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SoulvyreModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm2;

	public SoulvyreModel() {
		texWidth = 64;
		texHeight = 64;

		head = new LegacyModelPart(this, 0, 0);
		head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
		head.setPos(0.0F, -4.0F, 0.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new LegacyModelPart(this, 32, 16);
		body.addBox(1.0F, 0.0F, -2.0F, 3, 15, 4);
		body.setPos(0.0F, -3.0F, 0.0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new LegacyModelPart(this, 38, 5);
		rightArm.addBox(-3.0F, -5.0F, -3.0F, 5, 3, 6);
		rightArm.setPos(-6.0F, 2.0F, 0.0F);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new LegacyModelPart(this, 38, 5);
		leftArm.addBox(-2.0F, -5.0F, -3.0F, 5, 3, 6);
		leftArm.setPos(6.0F, 2.0F, 0.0F);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new LegacyModelPart(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new LegacyModelPart(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setPos(3.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		body2 = new LegacyModelPart(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 3, 15, 4);
		body2.setPos(0.0F, -3.0F, 0.0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightArm3 = new LegacyModelPart(this, 48, 32);
		rightArm3.addBox(-3.0F, 9.0F, -0.5F, 2, 7, 1);
		rightArm3.setPos(-6.0F, 2.0F, 0.0F);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		leftArm3 = new LegacyModelPart(this, 48, 32);
		leftArm3.addBox(1.0F, 9.0F, -0.5F, 2, 7, 1);
		leftArm3.setPos(6.0F, 2.0F, 0.0F);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		leftArm2 = new LegacyModelPart(this, 48, 16);
		leftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4);
		leftArm2.setPos(6.0F, 2.0F, 0.0F);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new LegacyModelPart(this, 48, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4);
		rightArm2.setPos(-6.0F, 2.0F, 0.0F);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
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
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm.zRot = 0.0F;

		rightArm2.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm2.zRot = 0.0F;

		rightArm3.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm3.zRot = 0.0F;

		head.yRot = (netHeadYaw / 57.295776F);
		head.xRot = (headPitch / 54.11268F);

		leftArm.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.zRot = 0.0F;

		leftArm2.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm2.zRot = 0.0F;

		leftArm3.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm3.zRot = 0.0F;

		rightLeg.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}
}
