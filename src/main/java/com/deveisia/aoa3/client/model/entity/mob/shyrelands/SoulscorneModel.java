package com.deveisia.aoa3.client.model.entity.mob.shyrelands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SoulscorneModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftLeg3;
	private final LegacyModelPart rightLeg3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;

	public SoulscorneModel() {
		texWidth = 64;
		texHeight = 32;

		head = new LegacyModelPart(this, 33, 0);
		head.addBox(1.0F, -5.0F, -4.0F, 2, 1, 8);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new LegacyModelPart(this, 58, 11);
		body.addBox(2.0F, 3.0F, -1.0F, 1, 6, 1);
		body.setPos(0.0F, 0.0F, 0.0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new LegacyModelPart(this, 47, 26);
		rightArm.addBox(-3.0F, 8.0F, -2.0F, 4, 1, 4);
		rightArm.setPos(-5.0F, 2.0F, 0.0F);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new LegacyModelPart(this, 47, 26);
		leftArm.addBox(-1.0F, 8.0F, -2.0F, 4, 1, 4);
		leftArm.setPos(5.0F, 2.0F, 0.0F);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new LegacyModelPart(this, 0, 24);
		rightLeg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		rightLeg.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new LegacyModelPart(this, 0, 24);
		leftLeg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		leftLeg.setPos(3.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		leftLeg2 = new LegacyModelPart(this, 0, 16);
		leftLeg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		leftLeg2.setPos(3.0F, 12.0F, 0.0F);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0F, 0.0F, 0.0F);
		rightLeg2 = new LegacyModelPart(this, 0, 16);
		rightLeg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		rightLeg2.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0F, 0.0F, 0.0F);
		leftLeg3 = new LegacyModelPart(this, 0, 16);
		leftLeg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		leftLeg3.setPos(3.0F, 12.0F, 0.0F);
		leftLeg3.setTexSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0F, 0.0F, 0.0F);
		rightLeg3 = new LegacyModelPart(this, 0, 16);
		rightLeg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		rightLeg3.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg3.setTexSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0F, 0.0F, 0.0F);
		body2 = new LegacyModelPart(this, 17, 16);
		body2.addBox(-4.0F, 0.0F, 0.0F, 8, 12, 2);
		body2.setPos(0.0F, 0.0F, 0.0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		body3 = new LegacyModelPart(this, 36, 13);
		body3.addBox(-3.0F, 2.0F, -1.0F, 6, 1, 1);
		body3.setPos(0.0F, 0.0F, 0.0F);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new LegacyModelPart(this, 36, 13);
		body4.addBox(-3.0F, 9.0F, -1.0F, 6, 1, 1);
		body4.setPos(0.0F, 0.0F, 0.0F);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0F, 0.0F, 0.0F);
		body5 = new LegacyModelPart(this, 58, 11);
		body5.addBox(-3.0F, 3.0F, -1.0F, 1, 6, 1);
		body5.setPos(0.0F, 0.0F, 0.0F);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		leftArm2 = new LegacyModelPart(this, 43, 16);
		leftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 5, 4);
		leftArm2.setPos(5.0F, 2.0F, 0.0F);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new LegacyModelPart(this, 43, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 5, 4);
		rightArm2.setPos(-5.0F, 2.0F, 0.0F);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
		leftArm3 = new LegacyModelPart(this, 38, 25);
		leftArm3.addBox(0.0F, 3.0F, -1.0F, 2, 5, 2);
		leftArm3.setPos(5.0F, 2.0F, 0.0F);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		rightArm3 = new LegacyModelPart(this, 38, 25);
		rightArm3.addBox(-2.0F, 3.0F, -1.0F, 2, 5, 2);
		rightArm3.setPos(-5.0F, 2.0F, 0.0F);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		head2 = new LegacyModelPart(this, 0, 0);
		head2.addBox(-4.0F, -4.0F, -4.0F, 8, 4, 8);
		head2.setPos(0.0F, 0.0F, 0.0F);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new LegacyModelPart(this, 33, 0);
		head3.addBox(-3.0F, -5.0F, -4.0F, 2, 1, 8);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		rightLeg2.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg2.yRot = 0.0F;

		rightLeg3.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg3.yRot = 0.0F;

		leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		leftLeg2.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		leftLeg3.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		rightArm.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm.zRot = 0.0F;

		rightArm2.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm2.zRot = 0.0F;

		rightArm3.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm3.zRot = 0.0F;

		leftArm.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.zRot = 0.0F;

		leftArm2.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm2.zRot = 0.0F;

		leftArm3.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm3.zRot = 0.0F;
	}
}
