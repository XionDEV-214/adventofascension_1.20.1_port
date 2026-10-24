package com.deveisia.aoa3.client.model.entity.mob.celeve;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class TipsyModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head7;
	private final LegacyModelPart head8;
	private final LegacyModelPart head9;
	private final LegacyModelPart head10;

	public TipsyModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 38, 0)).addBox(-5.0f, -9.0f, 2.0f, 10, 3, 2);
		head.setPos(0.0f, 3.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-2.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(2.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 40, 6)).addBox(-2.0f, -3.0f, -3.0f, 4, 2, 1);
		head2.setPos(0.0f, 3.0f, 0.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 55, 6)).addBox(-1.0f, -8.0f, -7.0f, 2, 2, 2);
		head3.setPos(0.0f, 3.0f, 0.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 26, 7)).addBox(-5.0f, -9.0f, -3.0f, 2, 3, 5);
		head4.setPos(0.0f, 3.0f, 0.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 26, 7)).addBox(3.0f, -9.0f, -3.0f, 2, 3, 5);
		head5.setPos(0.0f, 3.0f, 0.0f);
		head5.setTexSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 51, 10)).addBox(2.0f, -3.0f, -5.0f, 0, 2, 2);
		head6.setPos(0.0f, 3.0f, 0.0f);
		head6.setTexSize(64, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new LegacyModelPart(this, 42, 10)).addBox(-2.0f, -3.0f, -5.0f, 4, 2, 0);
		head7.setPos(0.0f, 3.0f, 0.0f);
		head7.setTexSize(64, 64);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new LegacyModelPart(this, 51, 10)).addBox(-2.0f, -3.0f, -5.0f, 0, 2, 2);
		head8.setPos(0.0f, 3.0f, 0.0f);
		head8.setTexSize(64, 64);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -11.0f, -5.0f, 6, 8, 7);
		head9.setPos(0.0f, 3.0f, 0.0f);
		head9.setTexSize(64, 64);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new LegacyModelPart(this, 23, 0)).addBox(-2.0f, -1.0f, -5.0f, 4, 2, 3);
		head10.setPos(0.0f, 3.0f, 0.0f);
		head10.setTexSize(64, 64);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 54.11268f;
		head5.yRot = netHeadYaw / 57.295776f;
		head5.xRot = headPitch / 54.11268f;
		head6.yRot = netHeadYaw / 57.295776f;
		head6.xRot = headPitch / 54.11268f;
		head7.yRot = netHeadYaw / 57.295776f;
		head7.xRot = headPitch / 54.11268f;
		head8.yRot = netHeadYaw / 57.295776f;
		head8.xRot = headPitch / 54.11268f;
		head9.yRot = netHeadYaw / 57.295776f;
		head9.xRot = headPitch / 54.11268f;
		head10.yRot = netHeadYaw / 57.295776f;
		head10.xRot = headPitch / 54.11268f;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
