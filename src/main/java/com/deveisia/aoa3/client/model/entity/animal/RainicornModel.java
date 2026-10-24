package com.deveisia.aoa3.client.model.entity.animal;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class RainicornModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart horn3;
	private final LegacyModelPart horn1;
	private final LegacyModelPart part3;
	private final LegacyModelPart part4;
	private final LegacyModelPart leftSpikes;
	private final LegacyModelPart part6;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart part7;
	private final LegacyModelPart part8;
	private final LegacyModelPart part9;
	private final LegacyModelPart head;
	private final LegacyModelPart horn2;
	private final LegacyModelPart part10;
	private final LegacyModelPart rightSpikes;

	public RainicornModel() {
		texWidth = 128;
		texHeight = 64;
		(horn3 = new LegacyModelPart(this, 35, 16)).addBox(0.0f, -0.5f, 0.0f, 1, 3, 1);
		horn3.setPos(-2.0f, -9.0f, -17.06667f);
		horn3.setTexSize(128, 64);
		setRotation(horn3, 0.3490659f, 0.0f, 0.0f);
		(horn1 = new LegacyModelPart(this, 42, 18)).addBox(-2.5f, -6.5f, -4.0f, 2, 9, 2);
		horn1.setPos(2.0f, -9.0f, -17.0f);
		horn1.setTexSize(128, 64);
		setRotation(horn1, 0.3490659f, 0.0f, 0.0f);
		(part3 = new LegacyModelPart(this, 0, 0)).addBox(0.5f, 0.0f, 0.0f, 4, 13, 6);
		part3.setPos(-2.0f, -1.0f, -19.0f);
		part3.setTexSize(128, 64);
		setRotation(part3, 0.6444293f, 0.0f, 0.0f);
		(part4 = new LegacyModelPart(this, 0, 44)).addBox(0.0f, 0.0f, 0.0f, 9, 9, 11);
		part4.setPos(-4.0f, 3.0f, -12.0f);
		part4.setTexSize(128, 64);
		setRotation(part4, 0.0f, 0.0f, 0.0f);
		(leftSpikes = new LegacyModelPart(this, 91, 25)).addBox(1.0f, -7.0f, -4.0f, 0, 7, 14);
		leftSpikes.setPos(3.0f, 3.0f, -2.0f);
		leftSpikes.setTexSize(128, 64);
		setRotation(leftSpikes, 0.0f, 0.0f, 0.0f);
		(part6 = new LegacyModelPart(this, 55, 17)).addBox(0.0f, 0.0f, 0.0f, 7, 5, 3);
		part6.setPos(-3.0f, 9.0f, -5.0f);
		part6.setTexSize(128, 64);
		setRotation(part6, 0.9397927f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg1.setPos(-3.0f, 4.0f, -9.0f);
		leg1.setTexSize(128, 64);
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg2.setPos(4.0f, 4.0f, -9.0f);
		leg2.setTexSize(128, 64);
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 93, 12)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg3.setPos(4.0f, 4.0f, 10.0f);
		leg3.setTexSize(128, 64);
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 93, 12)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg4.setPos(-3.0f, 4.0f, 10.0f);
		leg4.setTexSize(128, 64);
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(part7 = new LegacyModelPart(this, 51, 25)).addBox(2.0f, 0.0f, 0.0f, 9, 1, 7);
		part7.setPos(-6.0f, 2.0f, 5.0f);
		part7.setTexSize(128, 64);
		setRotation(part7, 0.0f, 0.0f, 0.0f);
		(part8 = new LegacyModelPart(this, 74, 43)).addBox(0.0f, 0.0f, 0.0f, 3, 19, 2);
		part8.setPos(-1.0f, 3.0f, 10.0f);
		part8.setTexSize(128, 64);
		setRotation(part8, 0.4833219f, 0.0f, 0.0f);
		(part9 = new LegacyModelPart(this, 45, 46)).addBox(1.0f, 0.0f, -6.0f, 1, 15, 3);
		part9.setPos(-1.0f, -10.0f, -12.0f);
		part9.setTexSize(128, 64);
		setRotation(part9, 0.669215f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 22, 0)).addBox(-1.0f, 0.0f, 0.0f, 5, 5, 11);
		head.setPos(-1.0f, -3.466667f, -25.0f);
		head.setTexSize(128, 64);
		setRotation(head, 0.3490659f, 0.0f, 0.0f);
		(horn2 = new LegacyModelPart(this, 35, 16)).addBox(0.0f, -0.5f, 0.0f, 1, 3, 1);
		horn2.setPos(2.0f, -9.0f, -17.0f);
		horn2.setTexSize(128, 64);
		setRotation(horn2, 0.3490659f, 0.0f, 0.0f);
		(part10 = new LegacyModelPart(this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 9, 7, 12);
		part10.setPos(-4.0f, 3.0f, -1.0f);
		part10.setTexSize(128, 64);
		setRotation(part10, 0.0f, 0.0f, 0.0f);
		(rightSpikes = new LegacyModelPart(this, 91, 25)).addBox(1.0f, -7.0f, -4.0f, 0, 7, 14);
		rightSpikes.setPos(-4.0f, 3.0f, -2.0f);
		rightSpikes.setTexSize(128, 64);
		setRotation(rightSpikes, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		horn3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftSpikes.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightSpikes.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
