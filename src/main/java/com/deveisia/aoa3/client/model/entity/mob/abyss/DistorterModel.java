package com.deveisia.aoa3.client.model.entity.mob.abyss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class DistorterModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body1;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart body8;

	public DistorterModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -10.0f, -4.0f, 6, 10, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 31, 10)).addBox(3.0f, -4.0f, -2.0f, 4, 3, 4);
		body1.setPos(-1.0f, 15.0f, 0.0f);
		body1.setTexSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		rightLeg.setPos(-3.0f, 18.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leftLeg.setPos(3.0f, 18.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 18, 20)).addBox(-4.0f, 0.0f, -2.0f, 10, 3, 4);
		body2.setPos(-1.0f, 15.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -14.0f, -2.0f, 4, 15, 4);
		body3.setPos(-1.0f, 14.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 31, 10)).addBox(3.0f, -14.0f, -2.0f, 4, 3, 4);
		body4.setPos(-1.0f, 15.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 31, 10)).addBox(3.0f, -9.0f, -2.0f, 4, 3, 4);
		body5.setPos(-1.0f, 15.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 48, 20)).addBox(-5.0f, -14.0f, -2.0f, 4, 3, 4);
		body6.setPos(-1.0f, 15.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 48, 20)).addBox(-5.0f, -9.0f, -2.0f, 4, 3, 4);
		body7.setPos(-1.0f, 15.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new LegacyModelPart(this, 48, 20)).addBox(-5.0f, -4.0f, -2.0f, 4, 3, 4);
		body8.setPos(-1.0f, 15.0f, 0.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
