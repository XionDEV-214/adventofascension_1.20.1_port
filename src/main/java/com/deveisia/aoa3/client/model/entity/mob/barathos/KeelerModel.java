package com.deveisia.aoa3.client.model.entity.mob.barathos;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class KeelerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart leg4p2;
	private final LegacyModelPart leg3p2;
	private final LegacyModelPart leg4p3;
	private final LegacyModelPart leg3p3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;
	private final LegacyModelPart body8;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;

	public KeelerModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 47, 0)).addBox(-2.0f, -4.0f, -8.0f, 5, 8, 2);
		head.setPos(-0.5f, 11.0f, -2.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 51, 18)).addBox(-4.0f, 1.0f, -7.0f, 8, 8, 6);
		body.setPos(0.0f, 14.0f, 7.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.22173f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 26, 22)).addBox(-2.0f, 9.0f, 1.0f, 2, 2, 8);
		leg3.setPos(-8.0f, 12.0f, -1.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 26, 22)).addBox(0.0f, 9.0f, 1.0f, 2, 2, 8);
		leg4.setPos(8.0f, 12.0f, -1.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new LegacyModelPart(this, 23, 10)).addBox(-2.0f, -2.0f, -4.0f, 6, 4, 6);
		leg4p2.setPos(8.0f, 12.0f, -1.0f);
		leg4p2.setTexSize(128, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new LegacyModelPart(this, 23, 10)).addBox(-4.0f, -2.0f, -4.0f, 6, 4, 6);
		leg3p2.setPos(-8.0f, 12.0f, -1.0f);
		leg3p2.setTexSize(128, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg4p3 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4p3.setPos(8.0f, 12.0f, -1.0f);
		leg4p3.setTexSize(128, 32);
		leg4p3.mirror = true;
		setRotation(leg4p3, 0.0f, 0.0f, 0.0f);
		(leg3p3 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3p3.setPos(-8.0f, 12.0f, -1.0f);
		leg3p3.setTexSize(128, 32);
		leg3p3.mirror = true;
		setRotation(leg3p3, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body2.setPos(-7.0f, 20.0f, 5.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 82, 11)).addBox(-6.0f, -10.0f, -7.0f, 12, 11, 10);
		body3.setPos(0.0f, 14.0f, 7.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.22173f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body4.setPos(-7.0f, 20.0f, 5.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.5235988f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body5.setPos(0.0f, 14.0f, 7.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.22173f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body6.setPos(0.0f, 14.0f, 7.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.6981317f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body7.setPos(7.0f, 20.0f, 5.0f);
		body7.setTexSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, 0.0f, 0.0f);
		(body8 = new LegacyModelPart(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body8.setPos(7.0f, 20.0f, 5.0f);
		body8.setTexSize(128, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 26, 0)).addBox(-1.0f, -3.0f, -10.0f, 3, 6, 2);
		head2.setPos(-0.5f, 11.0f, -2.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, -4.0f, -6.0f, 3, 8, 6);
		head3.setPos(-0.5f, 11.0f, -2.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leg3p3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p3.yRot = 0.0f;
		leg4p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
