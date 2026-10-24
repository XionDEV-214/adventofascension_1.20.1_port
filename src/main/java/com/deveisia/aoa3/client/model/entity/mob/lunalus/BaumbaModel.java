package com.deveisia.aoa3.client.model.entity.mob.lunalus;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class BaumbaModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart body8;

	public BaumbaModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -3.0f, 8, 8, 7);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 23)).addBox(-4.0f, 20.0f, -7.0f, 8, 4, 5);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 0, 20)).addBox(-3.0f, -2.0f, -2.0f, 4, 8, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.5235988f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 0, 20)).addBox(-1.0f, -2.0f, -2.0f, 4, 8, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.5235988f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 11.0f, -4.0f, 8, 6, 2);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 40, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 24, 3);
		body3.setPos(0.0f, 0.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 17.0f, 1.0f, 8, 3, 2);
		body4.setPos(0.0f, 0.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 5.0f, -4.0f, 8, 6, 2);
		body5.setPos(0.0f, 0.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 0.0f, 1.0f, 8, 6, 2);
		body6.setPos(0.0f, 0.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 6.0f, 1.0f, 8, 3, 2);
		body7.setPos(0.0f, 0.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new LegacyModelPart(this, 20, 15)).addBox(-4.0f, 20.0f, 1.0f, 8, 4, 2);
		body8.setPos(0.0f, 0.0f, 0.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
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
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
	}
}
