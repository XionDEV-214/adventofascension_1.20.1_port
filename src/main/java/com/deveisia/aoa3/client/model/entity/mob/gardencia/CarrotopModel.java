package com.deveisia.aoa3.client.model.entity.mob.gardencia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class CarrotopModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart body8;
	private final LegacyModelPart body9;
	private final LegacyModelPart body10;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart body11;
	private final LegacyModelPart body12;
	private final LegacyModelPart leftLeg3;
	private final LegacyModelPart rightLeg3;

	public CarrotopModel() {
		texWidth = 64;
		texHeight = 32;
		(rightLeg = new LegacyModelPart(this, 19, 16)).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
		rightLeg.setPos(-5.0f, 10.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 19, 16)).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
		leftLeg.setPos(5.0f, 10.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-1.5f, -15.0f, -1.5f, 3, 3, 3);
		body.setPos(0.0f, 4.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 0, 6)).addBox(1.0f, 10.0f, -1.0f, 2, 2, 2);
		body2.setPos(0.0f, 4.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 10, 16)).addBox(-3.5f, -3.0f, -3.5f, 7, 3, 7);
		body3.setPos(0.0f, 4.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 10, 16)).addBox(-3.0f, -6.0f, -3.0f, 6, 3, 6);
		body4.setPos(0.0f, 4.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 16, 16)).addBox(-1.0f, -18.0f, -1.0f, 2, 3, 2);
		body5.setPos(0.0f, 4.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 16, 16)).addBox(-2.5f, -9.0f, -2.5f, 5, 3, 5);
		body6.setPos(0.0f, 4.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 16, 16)).addBox(-2.0f, -12.0f, -2.0f, 4, 3, 4);
		body7.setPos(0.0f, 4.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new LegacyModelPart(this, 8, 27)).addBox(-4.0f, 4.0f, 1.0f, 8, 2, 3);
		body8.setPos(0.0f, 4.0f, 0.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new LegacyModelPart(this, 0, 6)).addBox(-1.0f, 8.0f, -1.0f, 2, 6, 2);
		body9.setPos(0.0f, 4.0f, 0.0f);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new LegacyModelPart(this, 0, 6)).addBox(-3.0f, 10.0f, -1.0f, 2, 2, 2);
		body10.setPos(0.0f, 4.0f, 0.0f);
		body10.setTexSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 48, 11)).addBox(-2.0f, 10.13333f, -2.0f, 4, 4, 4);
		leftLeg2.setPos(5.0f, 10.0f, 0.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 48, 11)).addBox(-2.0f, 10.13333f, -2.0f, 4, 4, 4);
		rightLeg2.setPos(-5.0f, 10.0f, 0.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(body11 = new LegacyModelPart(this, 16, 0)).addBox(-4.0f, 0.0f, -4.0f, 8, 4, 8);
		body11.setPos(0.0f, 4.0f, 0.0f);
		body11.setTexSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new LegacyModelPart(this, 32, 22)).addBox(-4.0f, 6.0f, -4.0f, 8, 2, 8);
		body12.setPos(0.0f, 4.0f, 0.0f);
		body12.setTexSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 14, 2);
		leftLeg3.setPos(5.0f, 10.0f, 0.0f);
		leftLeg3.setTexSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 14, 2);
		rightLeg3.setPos(-5.0f, 10.0f, 0.0f);
		rightLeg3.setTexSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		rightLeg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg3.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
