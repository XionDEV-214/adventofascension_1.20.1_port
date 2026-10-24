package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class XxeusModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart rightArm;
	private final LegacyModelPart leftArm;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightArm2;
	private final LegacyModelPart leftArm2;
	private final LegacyModelPart leftArm3;
	private final LegacyModelPart rightArm3;
	private final LegacyModelPart leftArm4;
	private final LegacyModelPart rightArm4;
	private final LegacyModelPart leftArm5;
	private final LegacyModelPart rightArm5;
	private final LegacyModelPart rightArm6;
	private final LegacyModelPart leftArm6;
	private final LegacyModelPart rightArm7;
	private final LegacyModelPart leftArm7;
	private final LegacyModelPart rightArm8;
	private final LegacyModelPart leftArm8;
	private final LegacyModelPart rightArm9;
	private final LegacyModelPart leftArm9;
	private final LegacyModelPart rightArm10;
	private final LegacyModelPart leftArm10;
	private final LegacyModelPart rightArm11;
	private final LegacyModelPart leftArm11;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head7;
	private final LegacyModelPart body;
	private final LegacyModelPart leftArm12;
	private final LegacyModelPart rightArm12;
	private final LegacyModelPart leftArm13;
	private final LegacyModelPart rightArm13;
	private final LegacyModelPart leftArm14;
	private final LegacyModelPart rightArm14;
	private final LegacyModelPart leftArm15;
	private final LegacyModelPart rightArm15;
	private final LegacyModelPart leftArm16;
	private final LegacyModelPart rightArm16;

	public XxeusModel() {
		texWidth = 256;
		texHeight = 128;

		head = new LegacyModelPart(this, 66, 7);
		head.addBox(-2.0F, -14.0F, -5.0F, 4, 2, 1);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.setTexSize(256, 128);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		rightArm = new LegacyModelPart(this, 57, 27);
		rightArm.addBox(-4.0F, -7.0F, 1.0F, 1, 3, 2);
		rightArm.setPos(-6.0F, 3.0F, 0.0F);
		rightArm.setTexSize(256, 128);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.2617994F);
		leftArm = new LegacyModelPart(this, 57, 27);
		leftArm.addBox(3.0F, -7.0F, 1.0F, 1, 3, 2);
		leftArm.setPos(6.0F, 3.0F, 0.0F);
		leftArm.setTexSize(256, 128);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, -0.2617994F);
		rightLeg = new LegacyModelPart(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(256, 128);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new LegacyModelPart(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setPos(3.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(256, 128);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		rightArm2 = new LegacyModelPart(this, 90, 7);
		rightArm2.addBox(-1.5F, 14.0F, -19.0F, 1, 1, 5);
		rightArm2.setPos(-6.0F, 3.0F, 0.0F);
		rightArm2.setTexSize(256, 128);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.2617994F);
		leftArm2 = new LegacyModelPart(this, 90, 7);
		leftArm2.addBox(0.5F, 14.0F, -19.0F, 1, 1, 5);
		leftArm2.setPos(6.0F, 3.0F, 0.0F);
		leftArm2.setTexSize(256, 128);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, -0.2617994F);
		leftArm3 = new LegacyModelPart(this, 57, 17);
		leftArm3.addBox(-2.0F, -4.0F, -3.0F, 6, 2, 6);
		leftArm3.setPos(6.0F, 3.0F, 0.0F);
		leftArm3.setTexSize(256, 128);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, -0.2617994F);
		rightArm3 = new LegacyModelPart(this, 57, 17);
		rightArm3.addBox(-4.0F, -4.0F, -3.0F, 6, 2, 6);
		rightArm3.setPos(-6.0F, 3.0F, 0.0F);
		rightArm3.setTexSize(256, 128);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.2617994F);
		leftArm4 = new LegacyModelPart(this, 57, 27);
		leftArm4.addBox(3.0F, -7.0F, -3.0F, 1, 3, 2);
		leftArm4.setPos(6.0F, 3.0F, 0.0F);
		leftArm4.setTexSize(256, 128);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0F, 0.0F, -0.2617994F);
		rightArm4 = new LegacyModelPart(this, 57, 27);
		rightArm4.addBox(-4.0F, -7.0F, -3.0F, 1, 3, 2);
		rightArm4.setPos(-6.0F, 3.0F, 0.0F);
		rightArm4.setTexSize(256, 128);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0F, 0.0F, 0.2617994F);
		leftArm5 = new LegacyModelPart(this, 40, 16);
		leftArm5.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		leftArm5.setPos(6.0F, 3.0F, 0.0F);
		leftArm5.setTexSize(256, 128);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0F, 0.0F, -0.2617994F);
		rightArm5 = new LegacyModelPart(this, 40, 16);
		rightArm5.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		rightArm5.setPos(-6.0F, 3.0F, 0.0F);
		rightArm5.setTexSize(256, 128);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0F, 0.0F, 0.2617994F);
		rightArm6 = new LegacyModelPart(this, 73, 29);
		rightArm6.addBox(-2.0F, 7.0F, -7.0F, 2, 2, 10);
		rightArm6.setPos(-6.0F, 3.0F, 0.0F);
		rightArm6.setTexSize(256, 128);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0F, 0.0F, 0.2617994F);
		leftArm6 = new LegacyModelPart(this, 73, 29);
		leftArm6.addBox(0.0F, 7.0F, -7.0F, 2, 2, 10);
		leftArm6.setPos(6.0F, 3.0F, 0.0F);
		leftArm6.setTexSize(256, 128);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0F, 0.0F, -0.2617994F);
		rightArm7 = new LegacyModelPart(this, 72, 92);
		rightArm7.addBox(-1.5F, 4.0F, -27.0F, 1, 2, 2);
		rightArm7.setPos(-6.0F, 3.0F, 0.0F);
		rightArm7.setTexSize(256, 128);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.0F, 0.0F, 0.2617994F);
		leftArm7 = new LegacyModelPart(this, 72, 92);
		leftArm7.addBox(0.5F, 4.0F, -27.0F, 1, 2, 2);
		leftArm7.setPos(6.0F, 3.0F, 0.0F);
		leftArm7.setTexSize(256, 128);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0F, 0.0F, -0.2617994F);
		rightArm8 = new LegacyModelPart(this, 111, 1);
		rightArm8.addBox(-1.5F, 5.0F, -24.0F, 1, 6, 2);
		rightArm8.setPos(-6.0F, 3.0F, 0.0F);
		rightArm8.setTexSize(256, 128);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0.0F, 0.0F, 0.2617994F);
		leftArm8 = new LegacyModelPart(this, 111, 1);
		leftArm8.addBox(0.5F, 5.0F, -24.0F, 1, 6, 2);
		leftArm8.setPos(6.0F, 3.0F, 0.0F);
		leftArm8.setTexSize(256, 128);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0F, 0.0F, -0.2617994F);
		rightArm9 = new LegacyModelPart(this, 99, 14);
		rightArm9.addBox(-1.5F, 12.0F, -19.0F, 1, 2, 2);
		rightArm9.setPos(-6.0F, 3.0F, 0.0F);
		rightArm9.setTexSize(256, 128);
		rightArm9.mirror = true;
		setRotation(rightArm9, 0.0F, 0.0F, 0.2617994F);
		leftArm9 = new LegacyModelPart(this, 99, 14);
		leftArm9.addBox(0.5F, 12.0F, -19.0F, 1, 2, 2);
		leftArm9.setPos(6.0F, 3.0F, 0.0F);
		leftArm9.setTexSize(256, 128);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0F, 0.0F, -0.2617994F);
		rightArm10 = new LegacyModelPart(this, 90, 7);
		rightArm10.addBox(-1.5F, 1.0F, -19.0F, 1, 1, 5);
		rightArm10.setPos(-6.0F, 3.0F, 0.0F);
		rightArm10.setTexSize(256, 128);
		rightArm10.mirror = true;
		setRotation(rightArm10, 0.0F, 0.0F, 0.2617994F);
		leftArm10 = new LegacyModelPart(this, 90, 7);
		leftArm10.addBox(0.5F, 1.0F, -19.0F, 1, 1, 5);
		leftArm10.setPos(6.0F, 3.0F, 0.0F);
		leftArm10.setTexSize(256, 128);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0F, 0.0F, -0.2617994F);
		rightArm11 = new LegacyModelPart(this, 90, 1);
		rightArm11.addBox(-1.5F, 2.0F, -19.0F, 1, 2, 2);
		rightArm11.setPos(-6.0F, 3.0F, 0.0F);
		rightArm11.setTexSize(256, 128);
		rightArm11.mirror = true;
		setRotation(rightArm11, 0.0F, 0.0F, 0.2617994F);
		leftArm11 = new LegacyModelPart(this, 90, 1);
		leftArm11.addBox(0.5F, 2.0F, -19.0F, 1, 2, 2);
		leftArm11.setPos(6.0F, 3.0F, 0.0F);
		leftArm11.setTexSize(256, 128);
		leftArm11.mirror = true;
		setRotation(leftArm11, 0.0F, 0.0F, -0.2617994F);
		head2 = new LegacyModelPart(this, 0, 0);
		head2.addBox(-4.0F, -5.0F, -4.0F, 8, 5, 8);
		head2.setPos(0.0F, 0.0F, 0.0F);
		head2.setTexSize(256, 128);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new LegacyModelPart(this, 103, 28);
		head3.addBox(5.0F, -9.0F, 2.0F, 1, 4, 11);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head3.setTexSize(256, 128);
		head3.mirror = true;
		setRotation(head3, 0.5235988F, 0.0F, 0.0F);
		head4 = new LegacyModelPart(this, 66, 0);
		head4.addBox(-4.0F, -10.0F, -5.0F, 8, 1, 1);
		head4.setPos(0.0F, 0.0F, 0.0F);
		head4.setTexSize(256, 128);
		head4.mirror = true;
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new LegacyModelPart(this, 66, 3);
		head5.addBox(-3.0F, -12.0F, -5.0F, 6, 2, 1);
		head5.setPos(0.0F, 0.0F, 0.0F);
		head5.setTexSize(256, 128);
		head5.mirror = true;
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new LegacyModelPart(this, 33, 0);
		head6.addBox(-5.0F, -9.0F, -5.0F, 10, 4, 11);
		head6.setPos(0.0F, 0.0F, 0.0F);
		head6.setTexSize(256, 128);
		head6.mirror = true;
		setRotation(head6, 0.0F, 0.0F, 0.0F);
		head7 = new LegacyModelPart(this, 103, 28);
		head7.addBox(-6.0F, -9.0F, 2.0F, 1, 4, 11);
		head7.setPos(0.0F, 0.0F, 0.0F);
		head7.setTexSize(256, 128);
		head7.mirror = true;
		setRotation(head7, 0.5235988F, 0.0F, 0.0F);
		body = new LegacyModelPart(this, 16, 16);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
		body.setPos(0.0F, 0.0F, 0.0F);
		body.setTexSize(256, 128);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		leftArm12 = new LegacyModelPart(this, 89, 1);
		leftArm12.addBox(0.5F, 4.0F, -22.0F, 1, 8, 18);
		leftArm12.setPos(6.0F, 3.0F, 0.0F);
		leftArm12.setTexSize(256, 128);
		leftArm12.mirror = true;
		setRotation(leftArm12, 0.0F, 0.0F, -0.2617994F);
		rightArm12 = new LegacyModelPart(this, 89, 1);
		rightArm12.addBox(-1.5F, 4.0F, -22.0F, 1, 8, 18);
		rightArm12.setPos(-6.0F, 3.0F, 0.0F);
		rightArm12.setTexSize(256, 128);
		rightArm12.mirror = true;
		setRotation(rightArm12, 0.0F, 0.0F, 0.2617994F);
		leftArm13 = new LegacyModelPart(this, 72, 70);
		leftArm13.addBox(0.5F, 6.0F, -39.0F, 1, 2, 15);
		leftArm13.setPos(6.0F, 3.0F, 0.0F);
		leftArm13.setTexSize(256, 128);
		leftArm13.mirror = true;
		setRotation(leftArm13, 0.0F, 0.0F, -0.2617994F);
		rightArm13 = new LegacyModelPart(this, 72, 70);
		rightArm13.addBox(-1.5F, 6.0F, -39.0F, 1, 2, 15);
		rightArm13.setPos(-6.0F, 3.0F, 0.0F);
		rightArm13.setTexSize(256, 128);
		rightArm13.mirror = true;
		setRotation(rightArm13, 0.0F, 0.0F, 0.2617994F);
		leftArm14 = new LegacyModelPart(this, 72, 92);
		leftArm14.addBox(0.5F, 4.0F, -39.0F, 1, 2, 2);
		leftArm14.setPos(6.0F, 3.0F, 0.0F);
		leftArm14.setTexSize(256, 128);
		leftArm14.mirror = true;
		setRotation(leftArm14, 0.0F, 0.0F, -0.2617994F);
		rightArm14 = new LegacyModelPart(this, 72, 92);
		rightArm14.addBox(-1.5F, 4.0F, -39.0F, 1, 2, 2);
		rightArm14.setPos(-6.0F, 3.0F, 0.0F);
		rightArm14.setTexSize(256, 128);
		rightArm14.mirror = true;
		setRotation(rightArm14, 0.0F, 0.0F, 0.2617994F);
		leftArm15 = new LegacyModelPart(this, 72, 92);
		leftArm15.addBox(0.5F, 4.0F, -35.0F, 1, 2, 2);
		leftArm15.setPos(6.0F, 3.0F, 0.0F);
		leftArm15.setTexSize(256, 128);
		leftArm15.mirror = true;
		setRotation(leftArm15, 0.0F, 0.0F, -0.2617994F);
		rightArm15 = new LegacyModelPart(this, 72, 92);
		rightArm15.addBox(-1.5F, 4.0F, -35.0F, 1, 2, 2);
		rightArm15.setPos(-6.0F, 3.0F, 0.0F);
		rightArm15.setTexSize(256, 128);
		rightArm15.mirror = true;
		setRotation(rightArm15, 0.0F, 0.0F, 0.2617994F);
		leftArm16 = new LegacyModelPart(this, 72, 92);
		leftArm16.addBox(0.5F, 4.0F, -31.0F, 1, 2, 2);
		leftArm16.setPos(6.0F, 3.0F, 0.0F);
		leftArm16.setTexSize(256, 128);
		leftArm16.mirror = true;
		setRotation(leftArm16, 0.0F, 0.0F, -0.2617994F);
		rightArm16 = new LegacyModelPart(this, 72, 92);
		rightArm16.addBox(-1.5F, 4.0F, -31.0F, 1, 2, 2);
		rightArm16.setPos(-6.0F, 3.0F, 0.0F);
		rightArm16.setTexSize(256, 128);
		rightArm16.mirror = true;
		setRotation(rightArm16, 0.0F, 0.0F, 0.2617994F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm.zRot = 0.0F;

		rightArm2.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm2.zRot = 0.0F;

		rightArm3.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm3.zRot = 0.0F;

		rightArm4.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm4.zRot = 0.0F;

		rightArm5.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm5.zRot = 0.0F;

		rightArm6.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm6.zRot = 0.0F;

		rightArm7.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm7.zRot = 0.0F;

		rightArm8.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm8.zRot = 0.0F;

		rightArm9.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm9.zRot = 0.0F;

		rightArm10.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm10.zRot = 0.0F;

		rightArm11.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm11.zRot = 0.0F;

		rightArm12.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm12.zRot = 0.0F;

		rightArm13.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm13.zRot = 0.0F;

		rightArm14.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm14.zRot = 0.0F;

		rightArm15.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm15.zRot = 0.0F;

		rightArm16.xRot = (Mth.cos(limbSwing * 0.3331F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm16.zRot = 0.0F;

		leftArm.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.zRot = 0.0F;

		leftArm2.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm2.zRot = 0.0F;

		leftArm3.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm3.zRot = 0.0F;

		leftArm4.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm4.zRot = 0.0F;

		leftArm5.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm5.zRot = 0.0F;

		leftArm6.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm6.zRot = 0.0F;

		leftArm7.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm7.zRot = 0.0F;

		leftArm8.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm8.zRot = 0.0F;

		leftArm9.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm9.zRot = 0.0F;

		leftArm10.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm10.zRot = 0.0F;

		leftArm11.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm11.zRot = 0.0F;

		leftArm12.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm12.zRot = 0.0F;

		leftArm13.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm13.zRot = 0.0F;

		leftArm14.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm14.zRot = 0.0F;

		leftArm15.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm15.zRot = 0.0F;

		leftArm16.xRot = (Mth.cos(limbSwing * 0.3331F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm16.zRot = 0.0F;

		rightLeg.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}
}
