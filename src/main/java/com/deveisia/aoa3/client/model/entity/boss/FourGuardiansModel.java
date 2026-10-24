package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class FourGuardiansModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart rightArm1;
	private final LegacyModelPart leftArm1;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm4;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart rightArm5;
	private final LegacyModelPart leftArm5;

	public FourGuardiansModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, -12.0f, -2.0f, 12, 12, 4);
		head.setPos(0.0f, 15.0f, 0.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new LegacyModelPart(this, 89, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		rightArm1.setPos(-10.0f, 7.0f, 0.0f);
		rightArm1.setTexSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new LegacyModelPart(this, 113, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		leftArm1.setPos(10.0f, 7.0f, 0.0f);
		leftArm1.setTexSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new LegacyModelPart(this, 40, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		rightArm2.setPos(-10.0f, 7.0f, 0.0f);
		rightArm2.setTexSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new LegacyModelPart(this, 64, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		leftArm2.setPos(10.0f, 7.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new LegacyModelPart(this, 89, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		rightArm3.setPos(-10.0f, 7.0f, 0.0f);
		rightArm3.setTexSize(128, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new LegacyModelPart(this, 113, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		leftArm3.setPos(10.0f, 7.0f, 0.0f);
		leftArm3.setTexSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new LegacyModelPart(this, 89, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		rightArm4.setPos(-10.0f, 7.0f, 0.0f);
		rightArm4.setTexSize(128, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new LegacyModelPart(this, 113, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		leftArm4.setPos(10.0f, 7.0f, 0.0f);
		leftArm4.setTexSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new LegacyModelPart(this, 89, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		rightArm5.setPos(-10.0f, 7.0f, 0.0f);
		rightArm5.setTexSize(128, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new LegacyModelPart(this, 113, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		leftArm5.setPos(10.0f, 7.0f, 0.0f);
		leftArm5.setTexSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm1.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm1.zRot = 0.0f;
		rightArm2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		rightArm4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm4.zRot = 0.0f;
		rightArm5.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm5.zRot = 0.0f;
		leftArm1.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm1.zRot = 0.0f;
		leftArm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		leftArm3.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
		leftArm4.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm4.zRot = 0.0f;
		leftArm5.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm5.zRot = 0.0f;
	}
}
