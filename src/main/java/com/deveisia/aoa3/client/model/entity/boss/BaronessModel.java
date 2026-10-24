package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class BaronessModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head4;
	private final LegacyModelPart body;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart head6;
	private final LegacyModelPart head5;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart rightArm4;
	private final LegacyModelPart rightArm5;
	private final LegacyModelPart rightArm6;
	private final LegacyModelPart head2;
	private final LegacyModelPart r2;
	private final LegacyModelPart head3;
	private final LegacyModelPart r1;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm7;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart rightArm8;
	private final LegacyModelPart leftArm5;
	private final LegacyModelPart rightArm9;

	public BaronessModel(float delta) {
		texWidth = 128;
		texHeight = 64;

		head4 = new LegacyModelPart(this, 104, 3);
		head4.addBox(-3F, -18F, -3F, 6, 5, 6, delta);
		head4.setPos(0F, -2F, 0F);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0F, 0F, 0F);
		body = new LegacyModelPart(this, 7, 41);
		body.addBox(-5F, 15F, -3F, 10, 5, 6, delta);
		body.setPos(0F, -2F, 0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightArm = new LegacyModelPart(this, 40, 49);
		rightArm.addBox(-7F, -8F, 1F, 3, 2, 1, delta);
		rightArm.setPos(-6F, 0F, 0F);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
		leftArm = new LegacyModelPart(this, 40, 49);
		leftArm.addBox(4F, -8F, 1F, 3, 2, 1, delta);
		leftArm.setPos(6F, 0F, 0F);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, 0F);
		rightArm2 = new LegacyModelPart(this, 79, 25);
		rightArm2.addBox(-2F, -9F, -5F, 2, 3, 2, delta);
		rightArm2.setPos(-6F, 0F, 0F);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.4363323F, 0F, 0F);
		leftArm2 = new LegacyModelPart(this, 40, 16);
		leftArm2.addBox(-1F, 0F, -2F, 4, 10, 4, delta);
		leftArm2.setPos(6F, 0F, 0F);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0F, 0F, 0F);
		body2 = new LegacyModelPart(this, 16, 16);
		body2.addBox(-4F, 0F, -2F, 8, 10, 4, delta);
		body2.setPos(0F, -2F, 0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new LegacyModelPart(this, 3, 52);
		body3.addBox(-5.5F, 20F, -3.5F, 11, 5, 7, delta);
		body3.setPos(0F, -2F, 0F);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body4 = new LegacyModelPart(this, 11, 31);
		body4.addBox(-4.5F, 10F, -2.5F, 9, 5, 5, delta);
		body4.setPos(0F, -2F, 0F);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		head6 = new LegacyModelPart(this, 32, 2);
		head6.addBox(-5F, -8F, -5F, 10, 2, 10, delta);
		head6.setPos(0F, -2F, 0F);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0F, 0F, 0F);
		head5 = new LegacyModelPart(this, 72, 1);
		head5.addBox(-4F, -13F, -4F, 8, 5, 8, delta);
		head5.setPos(0F, -2F, 0F);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0F, 0F, 0F);
		rightArm3 = new LegacyModelPart(this, 40, 16);
		rightArm3.addBox(-3F, 0F, -2F, 4, 10, 4, delta);
		rightArm3.setPos(-6F, 0F, 0F);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0F, 0F, 0F);
		rightArm4 = new LegacyModelPart(this, 69, 16);
		rightArm4.addBox(-2F, -4F, -7F, 2, 26, 2, delta);
		rightArm4.setPos(-6F, 0F, 0F);
		rightArm4.setTexSize(64, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.4363323F, 0F, 0F);
		rightArm5 = new LegacyModelPart(this, 79, 16);
		rightArm5.addBox(-2F, -6F, -9F, 2, 2, 6, delta);
		rightArm5.setPos(-6F, 0F, 0F);
		rightArm5.setTexSize(64, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.4363323F, 0F, 0F);
		rightArm6 = new LegacyModelPart(this, 79, 16);
		rightArm6.addBox(-2F, -11F, -9F, 2, 2, 6, delta);
		rightArm6.setPos(-6F, 0F, 0F);
		rightArm6.setTexSize(64, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.4363323F, 0F, 0F);
		head2 = new LegacyModelPart(this, 40, 40);
		head2.addBox(-4F, 0F, -4F, 8, 7, 0, delta);
		head2.setPos(0F, -2F, 0F);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		r2 = new LegacyModelPart(this, 97, 16);
		r2.addBox(-3F, -3F, 10F, 6, 6, 6, delta);
		r2.setPos(0F, -21F, 0F);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0F, 0F, 0F);
		head3 = new LegacyModelPart(this, 0, 0);
		head3.addBox(-4F, -6F, -4F, 8, 6, 8, delta);
		head3.setPos(0F, -2F, 0F);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, 0F);
		r1 = new LegacyModelPart(this, 97, 16);
		r1.addBox(-3F, -3F, -16F, 6, 6, 6, delta);
		r1.setPos(0F, -21F, 0F);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0F, 0F, 0F);
		leftArm3 = new LegacyModelPart(this, 40, 30);
		leftArm3.addBox(-2F, -3F, -4F, 6, 3, 6, delta);
		leftArm3.setPos(6F, 0F, 0F);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0F, 0F, 0F);
		rightArm7 = new LegacyModelPart(this, 40, 30);
		rightArm7.addBox(-4F, -3F, -4F, 6, 3, 6, delta);
		rightArm7.setPos(-6F, 0F, 0F);
		rightArm7.setTexSize(64, 32);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0F, 0F, 0F);
		leftArm4 = new LegacyModelPart(this, 47, 54);
		leftArm4.addBox(-1F, -6F, 1F, 2, 3, 1, delta);
		leftArm4.setPos(6F, 0F, 0F);
		leftArm4.setTexSize(64, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0F, 0F, 0F);
		rightArm8 = new LegacyModelPart(this, 47, 54);
		rightArm8.addBox(-1F, -6F, 1F, 2, 3, 1, delta);
		rightArm8.setPos(-6F, 0F, 0F);
		rightArm8.setTexSize(64, 32);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0F, 0F, 0F);
		leftArm5 = new LegacyModelPart(this, 40, 54);
		leftArm5.addBox(2F, -8F, 1F, 2, 5, 1, delta);
		leftArm5.setPos(6F, 0F, 0F);
		leftArm5.setTexSize(64, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0F, 0F, 0F);
		rightArm9 = new LegacyModelPart(this, 40, 54);
		rightArm9.addBox(-4F, -8F, 1F, 2, 5, 1, delta);
		rightArm9.setPos(-6F, 0F, 0F);
		rightArm9.setTexSize(64, 32);
		rightArm9.mirror = true;
		setRotation(rightArm9, 0F, 0F, 0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.r1.yRot = (ageInTicks * 0.067F) * 1.25F;
		this.r2.yRot = (ageInTicks * 0.067F) * 1.25F;

		this.head2.yRot = netHeadYaw / (180F / (float)Math.PI);
		this.head2.xRot = headPitch / (170F / (float)Math.PI);

		this.head3.yRot = netHeadYaw / (180F / (float)Math.PI);
		this.head3.xRot = headPitch / (170F / (float)Math.PI);

		this.head4.yRot = netHeadYaw / (180F / (float)Math.PI);
		this.head4.xRot = headPitch / (170F / (float)Math.PI);

		this.head5.yRot = netHeadYaw / (180F / (float)Math.PI);
		this.head5.xRot = headPitch / (170F / (float)Math.PI);

		this.head6.yRot = netHeadYaw / (180F / (float)Math.PI);
		this.head6.xRot = headPitch / (170F / (float)Math.PI);

		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm.zRot = 0.0F;

		this.rightArm2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F + 0.436F;
		this.rightArm2.zRot = 0.0F;

		this.rightArm3.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm3.zRot = 0.0F;

		this.rightArm4.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F + 0.436F;
		this.rightArm4.zRot = 0.0F;

		this.rightArm5.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F + 0.436F;
		this.rightArm5.zRot = 0.0F;

		this.rightArm6.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F + 0.436F;
		this.rightArm6.zRot = 0.0F;

		this.rightArm7.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm7.zRot = 0.0F;

		this.rightArm8.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm8.zRot = 0.0F;

		this.rightArm9.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.rightArm9.zRot = 0.0F;

		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.leftArm.zRot = 0.0F;

		this.leftArm2.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.leftArm2.zRot = 0.0F;

	}

}
