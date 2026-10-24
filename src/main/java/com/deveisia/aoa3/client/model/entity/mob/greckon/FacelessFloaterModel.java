package com.deveisia.aoa3.client.model.entity.mob.greckon;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class FacelessFloaterModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart head2;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;

	public FacelessFloaterModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 30, 0)).addBox(-4.0f, -8.0f, 2.0f, 8, 5, 6);
		head.setPos(0.0f, 0.0f, -1.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 0, 28)).addBox(2.0f, 17.0f, -2.0f, 4, 4, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-8.0f, 4.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, -1.570796f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(8.0f, 4.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, -1.570796f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 29, 47)).addBox(-4.0f, -3.0f, 0.0f, 8, 2, 2);
		head2.setPos(0.0f, 0.0f, -1.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 21, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 23, 4);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 0, 28)).addBox(-6.0f, 17.0f, -2.0f, 4, 4, 4);
		body3.setPos(0.0f, 0.0f, 0.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 0, 16)).addBox(2.0f, 2.0f, -2.0f, 5, 4, 4);
		body4.setPos(0.0f, 0.0f, 0.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 0, 16)).addBox(-7.0f, 2.0f, -2.0f, 5, 4, 4);
		body5.setPos(0.0f, 0.0f, 0.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 0, 28)).addBox(2.0f, 9.0f, -2.0f, 4, 4, 4);
		body6.setPos(0.0f, 0.0f, 0.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 0, 28)).addBox(-6.0f, 9.0f, -2.0f, 4, 4, 4);
		body7.setPos(0.0f, 0.0f, 0.0f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 5, 6);
		head3.setPos(0.0f, 0.0f, -1.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 47)).addBox(-4.0f, -1.0f, -4.0f, 8, 1, 6);
		head4.setPos(0.0f, 0.0f, -1.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}
}
