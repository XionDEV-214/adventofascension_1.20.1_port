package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class LivingFungiModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart p1;
	private final LegacyModelPart p2;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body1;
	private final LegacyModelPart head1;

	public LivingFungiModel() {
		texWidth = 128;
		texHeight = 32;
		(p1 = new LegacyModelPart(this, 64, 0)).addBox(-3.0f, -7.0f, -3.0f, 16, 12, 16);
		p1.setPos(-5.0f, -8.0f, -5.0f);
		p1.setTexSize(128, 32);
		p1.mirror = true;
		setRotation(p1, 0.0f, 0.0f, 0.0f);
		(p2 = new LegacyModelPart(this, 17, 21)).addBox(-4.0f, 0.0f, -2.0f, 18, 6, 5);
		p2.setPos(-5.0f, 0.0f, -1.0f);
		p2.setTexSize(128, 32);
		p2.mirror = true;
		setRotation(p2, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		rightArm.setPos(-7.0f, 8.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		leftArm.setPos(7.0f, 8.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg.setPos(-3.0f, 16.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg.setPos(3.0f, 16.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 25, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 10, 4);
		body1.setPos(0.0f, 6.0f, 0.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(head1 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -7.0f, -3.0f, 6, 5, 6);
		head1.setPos(0.0f, 3.0f, 2.0f);
		head1.setTexSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		p1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
