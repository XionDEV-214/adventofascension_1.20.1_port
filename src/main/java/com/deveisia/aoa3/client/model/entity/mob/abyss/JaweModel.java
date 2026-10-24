package com.deveisia.aoa3.client.model.entity.mob.abyss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class JaweModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart body2;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head8;
	private final LegacyModelPart head9;
	private final LegacyModelPart head10;
	private final LegacyModelPart head11;
	private final LegacyModelPart head12;
	private final LegacyModelPart head13;
	private final LegacyModelPart leg4p2;
	private final LegacyModelPart leg3p2;

	public JaweModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 11)).addBox(-3.0f, -7.0f, -4.0f, 6, 4, 6);
		head.setPos(0.0f, 16.0f, -8.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 22, 13)).addBox(-6.0f, -5.0f, -7.0f, 12, 10, 9);
		body.setPos(0.0f, 16.0f, 7.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 18, 8)).addBox(-0.5f, 6.0f, 2.0f, 0, 2, 6);
		leg3.setPos(-5.0f, 16.0f, -4.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 18, 8)).addBox(1.5f, 6.0f, 2.0f, 0, 2, 6);
		leg4.setPos(4.0f, 16.0f, -4.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 28, 1)).addBox(-6.0f, -5.0f, -7.0f, 12, 5, 6);
		body2.setPos(0.0f, 16.0f, 1.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(3.0f, -3.0f, 0.0f, 1, 2, 1);
		head2.setPos(0.0f, 16.0f, -8.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -2.0f, -6.0f, 6, 2, 8);
		head3.setPos(0.0f, 16.0f, -8.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 3, 5)).addBox(-2.0f, -3.0f, -6.0f, 1, 1, 1);
		head4.setPos(0.0f, 16.0f, -8.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 0, 0)).addBox(3.0f, -3.0f, -4.0f, 1, 2, 1);
		head5.setPos(0.0f, 16.0f, -8.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 0, 0)).addBox(3.0f, -3.0f, -2.0f, 1, 2, 1);
		head6.setPos(0.0f, 16.0f, -8.0f);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head8 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -3.0f, 0.0f, 1, 2, 1);
		head8.setPos(0.0f, 16.0f, -8.0f);
		head8.setTexSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -3.0f, -2.0f, 1, 2, 1);
		head9.setPos(0.0f, 16.0f, -8.0f);
		head9.setTexSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -3.0f, -4.0f, 1, 2, 1);
		head10.setPos(0.0f, 16.0f, -8.0f);
		head10.setTexSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -3.0f, -6.0f, 1, 2, 1);
		head11.setPos(0.0f, 16.0f, -8.0f);
		head11.setTexSize(64, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new LegacyModelPart(this, 0, 0)).addBox(3.0f, -3.0f, -6.0f, 1, 2, 1);
		head12.setPos(0.0f, 16.0f, -8.0f);
		head12.setTexSize(64, 32);
		head12.mirror = true;
		setRotation(head12, 0.0f, 0.0f, 0.0f);
		(head13 = new LegacyModelPart(this, 3, 5)).addBox(1.0f, -3.0f, -6.0f, 1, 1, 1);
		head13.setPos(0.0f, 16.0f, -8.0f);
		head13.setTexSize(64, 32);
		head13.mirror = true;
		setRotation(head13, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new LegacyModelPart(this, 0, 21)).addBox(0.0f, 0.0f, -1.0f, 3, 8, 3);
		leg4p2.setPos(4.0f, 16.0f, -4.0f);
		leg4p2.setTexSize(64, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new LegacyModelPart(this, 0, 21)).addBox(-2.0f, 0.0f, -1.0f, 3, 8, 3);
		leg3p2.setPos(-5.0f, 16.0f, -4.0f);
		leg3p2.setTexSize(64, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg3p2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p2.yRot = 0.0f;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
