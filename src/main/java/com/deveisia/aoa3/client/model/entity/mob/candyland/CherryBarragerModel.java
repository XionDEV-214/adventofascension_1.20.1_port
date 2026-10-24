package com.deveisia.aoa3.client.model.entity.mob.candyland;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class CherryBarragerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart body8;
	private final LegacyModelPart body9;
	private final LegacyModelPart body10;
	private final LegacyModelPart body11;
	private final LegacyModelPart body12;
	private final LegacyModelPart body13;
	private final LegacyModelPart body14;
	private final LegacyModelPart body15;
	private final LegacyModelPart body16;
	private final LegacyModelPart body17;
	private final LegacyModelPart body18;
	private final LegacyModelPart body19;

	public CherryBarragerModel() {
		texWidth = 64;
		texHeight = 64;
		(body = new LegacyModelPart(this, 32, 30)).addBox(0.0f, 0.0f, -3.0f, 4, 5, 12);
		body.setPos(12.0f, 5.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
		rightLeg.setPos(-3.0f, 20.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
		leftLeg.setPos(3.0f, 20.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 16, 15)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body2.setPos(8.0f, 10.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 6, 16)).addBox(0.0f, -12.0f, 2.0f, 4, 4, 4);
		body3.setPos(-2.0f, 3.0f, -3.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 0, 49)).addBox(-2.0f, 0.0f, 5.0f, 6, 5, 4);
		body4.setPos(8.0f, 5.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 0, 30)).addBox(-6.0f, 0.0f, -3.0f, 4, 5, 12);
		body5.setPos(8.0f, 5.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 16, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body6.setPos(8.0f, 1.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 1, 11)).addBox(-2.466667f, -13.0f, 1.0f, 1, 14, 1);
		body7.setPos(10.0f, 1.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, -0.8726646f);
		(body8 = new LegacyModelPart(this, 16, 15)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body8.setPos(-1.0f, 16.0f, -3.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new LegacyModelPart(this, 32, 30)).addBox(0.0f, 0.0f, -3.0f, 4, 5, 12);
		body9.setPos(3.0f, 11.0f, -3.0f);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new LegacyModelPart(this, 0, 30)).addBox(-6.0f, 0.0f, -3.0f, 4, 5, 12);
		body10.setPos(-1.0f, 11.0f, -3.0f);
		body10.setTexSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(body11 = new LegacyModelPart(this, 16, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body11.setPos(-1.0f, 7.0f, -3.0f);
		body11.setTexSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new LegacyModelPart(this, 0, 49)).addBox(-2.0f, 0.0f, 5.0f, 6, 5, 4);
		body12.setPos(-1.0f, 11.0f, -3.0f);
		body12.setTexSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(body13 = new LegacyModelPart(this, 16, 15)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body13.setPos(-10.0f, 10.0f, 0.0f);
		body13.setTexSize(64, 32);
		body13.mirror = true;
		setRotation(body13, 0.0f, 0.0f, 0.0f);
		(body14 = new LegacyModelPart(this, 32, 30)).addBox(0.0f, 0.0f, -3.0f, 4, 5, 12);
		body14.setPos(-6.0f, 5.0f, 0.0f);
		body14.setTexSize(64, 32);
		body14.mirror = true;
		setRotation(body14, 0.0f, 0.0f, 0.0f);
		(body15 = new LegacyModelPart(this, 0, 30)).addBox(-6.0f, 0.0f, -3.0f, 4, 5, 12);
		body15.setPos(-10.0f, 5.0f, 0.0f);
		body15.setTexSize(64, 32);
		body15.mirror = true;
		setRotation(body15, 0.0f, 0.0f, 0.0f);
		(body16 = new LegacyModelPart(this, 16, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body16.setPos(-10.0f, 1.0f, 0.0f);
		body16.setTexSize(64, 32);
		body16.mirror = true;
		setRotation(body16, 0.0f, 0.0f, 0.0f);
		(body17 = new LegacyModelPart(this, 0, 49)).addBox(-2.0f, 0.0f, 5.0f, 6, 5, 4);
		body17.setPos(-10.0f, 5.0f, 0.0f);
		body17.setTexSize(64, 32);
		body17.mirror = true;
		setRotation(body17, 0.0f, 0.0f, 0.0f);
		(body18 = new LegacyModelPart(this, 1, 11)).addBox(0.5333334f, -10.0f, 2.5f, 1, 14, 1);
		body18.setPos(-1.0f, 3.0f, -3.0f);
		body18.setTexSize(64, 32);
		body18.mirror = true;
		setRotation(body18, 0.0f, 0.0f, 0.0f);
		(body19 = new LegacyModelPart(this, 1, 11)).addBox(-2.466667f, -13.0f, 3.0f, 1, 14, 1);
		body19.setPos(-8.0f, 4.0f, -3.0f);
		body19.setTexSize(64, 32);
		body19.mirror = true;
		setRotation(body19, 0.0f, 0.0f, 0.8726646f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body17.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body18.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body19.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
