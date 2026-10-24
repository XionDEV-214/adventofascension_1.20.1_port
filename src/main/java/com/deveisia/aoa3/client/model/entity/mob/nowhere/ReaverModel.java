package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ReaverModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart leftLeg3;
	private final LegacyModelPart rightLeg3;
	private final LegacyModelPart leftLeg4;
	private final LegacyModelPart rightLeg4;

	public ReaverModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -8.0f, -4.0f, 6, 13, 8);
		head.setPos(0.0f, -8.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 39, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, -8.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		rightLeg2.setPos(-6.0f, -8.0f, 0.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		leftLeg2.setPos(6.0f, -8.0f, 0.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new LegacyModelPart(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		leftLeg3.setPos(6.0f, -8.0f, 0.0f);
		leftLeg3.setTexSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new LegacyModelPart(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		rightLeg3.setPos(-6.0f, -8.0f, 0.0f);
		rightLeg3.setTexSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(leftLeg4 = new LegacyModelPart(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		leftLeg4.setPos(6.0f, -8.0f, 0.0f);
		leftLeg4.setTexSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new LegacyModelPart(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		rightLeg4.setPos(-6.0f, -8.0f, 0.0f);
		rightLeg4.setTexSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		rightLeg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg3.yRot = 0.0f;
		rightLeg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg4.yRot = 0.0f;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
