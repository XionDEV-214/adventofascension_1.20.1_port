package com.deveisia.aoa3.client.model.entity.mob.shyrelands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ArcWizardModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart r1;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head7;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart rightArm4;

	public ArcWizardModel() {
		texWidth = 64;
		texHeight = 64;

		head = new LegacyModelPart(this, 46, 3);
		head.addBox(4.0F, -13.0F, 1.0F, 1, 4, 2);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.setTexSize(64, 32);
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new LegacyModelPart(this, 16, 37);
		body.addBox(-0.5F, 6.0F, -2.0F, 1, 4, 4);
		body.setPos(0.0F, -4.0F, 0.0F);
		body.setTexSize(64, 32);
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new LegacyModelPart(this, 38, 42);
		rightArm.addBox(-3.0F, -14.0F, -7.0F, 4, 5, 2);
		rightArm.setPos(-5.0F, -3.0F, 0.0F);
		rightArm.setTexSize(64, 32);
		setRotation(rightArm, 0.3490659F, 0.0F, 0.0F);
		leftArm = new LegacyModelPart(this, 40, 16);
		leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 16, 4);
		leftArm.setPos(5.0F, -3.0F, 0.0F);
		leftArm.setTexSize(64, 32);
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new LegacyModelPart(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(64, 32);
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new LegacyModelPart(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setPos(2.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(64, 32);
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		r1 = new LegacyModelPart(this, 52, 8);
		r1.addBox(-2.0F, -14.0F, -1.0F, 4, 6, 2);
		r1.setPos(0.0F, 0.0F, 0.0F);
		r1.setTexSize(64, 32);
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		head2 = new LegacyModelPart(this, 46, 3);
		head2.addBox(-5.0F, -13.0F, 1.0F, 1, 4, 2);
		head2.setPos(0.0F, 0.0F, 0.0F);
		head2.setTexSize(64, 32);
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new LegacyModelPart(this, 26, 3);
		head3.addBox(4.0F, -9.0F, -4.0F, 1, 4, 8);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head3.setTexSize(64, 32);
		setRotation(head3, 0.0F, 0.0F, 0.0F);
		head4 = new LegacyModelPart(this, 26, 3);
		head4.addBox(-5.0F, -9.0F, -4.0F, 1, 4, 8);
		head4.setPos(0.0F, 0.0F, 0.0F);
		head4.setTexSize(64, 32);
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new LegacyModelPart(this, 39, 3);
		head5.addBox(4.0F, -11.0F, -3.0F, 1, 2, 2);
		head5.setPos(0.0F, 0.0F, 0.0F);
		head5.setTexSize(64, 32);
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new LegacyModelPart(this, 39, 3);
		head6.addBox(-5.0F, -11.0F, -3.0F, 1, 2, 2);
		head6.setPos(0.0F, 0.0F, 0.0F);
		head6.setTexSize(64, 32);
		setRotation(head6, 0.0F, 0.0F, 0.0F);
		head7 = new LegacyModelPart(this, 0, 0);
		head7.addBox(-4.0F, -7.0F, -4.0F, 8, 2, 8);
		head7.setPos(0.0F, 0.0F, 0.0F);
		head7.setTexSize(64, 32);
		setRotation(head7, 0.0F, 0.0F, 0.0F);
		body2 = new LegacyModelPart(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4);
		body2.setPos(0.0F, -4.0F, 0.0F);
		body2.setTexSize(64, 32);
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		body3 = new LegacyModelPart(this, 16, 26);
		body3.addBox(-4.0F, 10.0F, -2.0F, 8, 6, 4);
		body3.setPos(0.0F, -4.0F, 0.0F);
		body3.setTexSize(64, 32);
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new LegacyModelPart(this, 16, 37);
		body4.addBox(-3.0F, 6.0F, -2.0F, 1, 4, 4);
		body4.setPos(0.0F, -4.0F, 0.0F);
		body4.setTexSize(64, 32);
		setRotation(body4, 0.0F, 0.0F, 0.0F);
		body5 = new LegacyModelPart(this, 16, 37);
		body5.addBox(2.0F, 6.0F, -2.0F, 1, 4, 4);
		body5.setPos(0.0F, -4.0F, 0.0F);
		body5.setTexSize(64, 32);
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		rightArm2 = new LegacyModelPart(this, 40, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 16, 4);
		rightArm2.setPos(-5.0F, -3.0F, 0.0F);
		rightArm2.setTexSize(64, 32);
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
		rightArm3 = new LegacyModelPart(this, 56, 20);
		rightArm3.addBox(-2.0F, -5.0F, -7.0F, 2, 28, 2);
		rightArm3.setPos(-5.0F, -3.0F, 0.0F);
		rightArm3.setTexSize(64, 32);
		setRotation(rightArm3, 0.3490659F, 0.0F, 0.0F);
		rightArm4 = new LegacyModelPart(this, 38, 37);
		rightArm4.addBox(-4.0F, -8.0F, -7.0F, 6, 2, 2);
		rightArm4.setPos(-5.0F, -3.0F, 0.0F);
		rightArm4.setTexSize(64, 32);
		setRotation(rightArm4, 0.3490659F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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

		leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		r1.yRot = (ageInTicks * 0.067F * 1.25F);

		rightArm.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F + 0.349F);
		rightArm.zRot = 0.0F;

		rightArm2.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm2.zRot = 0.0F;

		rightArm3.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F + 0.349F);
		rightArm3.zRot = 0.0F;

		rightArm4.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F + 0.349F);
		rightArm4.zRot = 0.0F;

		leftArm.xRot = (Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.zRot = 0.0F;
	}
}
