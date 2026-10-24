package com.deveisia.aoa3.client.model.entity.mob.iromine;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class MechachronModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body8;
	private final LegacyModelPart body9;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;

	public MechachronModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 16, 21)).addBox(5.0f, -8.0f, 2.0f, 2, 4, 8);
		head.setPos(0.0f, 17.0f, -6.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.5235988f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 29, 17)).addBox(12.0f, -9.0f, -3.0f, 1, 5, 4);
		body.setPos(-4.0f, 12.0f, 5.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.745329f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 11)).addBox(-4.0f, -3.0f, -3.0f, 6, 16, 6);
		rightArm.setPos(-11.0f, 11.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 11)).addBox(-2.0f, -3.0f, -3.0f, 6, 16, 6);
		leftArm.setPos(11.0f, 10.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-5.0f, 12.0f, 14.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(5.0f, 12.0f, 14.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body8 = new LegacyModelPart(this, 43, 34)).addBox(-1.0f, 8.0f, -4.0f, 2, 8, 2);
		body8.setPos(0.0f, 12.0f, 5.0f);
		body8.setTexSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 2.530727f, 0.0f, 0.0f);
		(body9 = new LegacyModelPart(this, 29, 17)).addBox(-5.0f, -9.0f, -3.0f, 1, 5, 4);
		body9.setPos(-4.0f, 12.0f, 5.0f);
		body9.setTexSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 1.745329f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, -8.0f, -4.0f, 10, 8, 8);
		head2.setPos(0.0f, 17.0f, -6.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 16, 21)).addBox(-7.0f, -8.0f, 2.0f, 2, 4, 8);
		head3.setPos(0.0f, 17.0f, -6.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.5235988f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 0, 34)).addBox(-4.0f, -11.0f, -3.0f, 16, 22, 4);
		body2.setPos(-4.0f, 12.0f, 5.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.745329f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 43, 34)).addBox(-1.0f, 2.0f, 2.0f, 2, 8, 2);
		body3.setPos(-4.0f, 12.0f, 5.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 2.530727f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 43, 34)).addBox(-1.0f, 2.0f, 2.0f, 2, 8, 2);
		body4.setPos(4.0f, 12.0f, 5.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 2.530727f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 43, 46)).addBox(-2.0f, 2.0f, -5.0f, 4, 8, 4);
		body5.setPos(0.0f, 12.0f, 5.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 2.530727f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 43, 46)).addBox(-2.0f, -4.0f, 1.0f, 4, 8, 4);
		body6.setPos(-4.0f, 12.0f, 5.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 2.530727f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 43, 46)).addBox(-2.0f, -4.0f, 1.0f, 4, 8, 4);
		body7.setPos(4.0f, 12.0f, 5.0f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 2.530727f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm.yRot = 0.0f;
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
