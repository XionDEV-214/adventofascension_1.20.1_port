package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SkeletronModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head1;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart body1;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;

	public SkeletronModel() {
		texWidth = 128;
		texHeight = 32;
		(head1 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head1.setPos(10.0f, 10.0f, -9.0f);
		head1.setTexSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setPos(-7.0f, 16.0f, 7.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setPos(7.0f, 16.0f, 7.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg3.setPos(-7.0f, 16.0f, -5.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg4.setPos(7.0f, 16.0f, -5.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 111, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 6);
		body1.setPos(4.0f, 3.0f, 3.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head2.setPos(0.0f, 10.0f, -11.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head3.setPos(-10.0f, 10.0f, -9.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 71, 0)).addBox(-3.0f, -13.0f, -7.0f, 6, 11, 3);
		body2.setPos(0.0f, 9.0f, -1.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.134464f, -0.6632251f, 0.0f);
		(body3 = new LegacyModelPart(this, 71, 0)).addBox(-3.0f, -10.0f, -7.0f, 6, 11, 3);
		body3.setPos(0.0f, 9.0f, -1.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.134464f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 71, 0)).addBox(-3.0f, -13.0f, -7.0f, 6, 11, 3);
		body4.setPos(0.0f, 9.0f, -1.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.134464f, 0.6632251f, 0.0f);
		(body5 = new LegacyModelPart(this, 33, 0)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body5.setPos(0.0f, 11.0f, 3.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 96, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 4);
		body6.setPos(0.0f, 3.0f, 3.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 96, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 4);
		body7.setPos(8.0f, 3.0f, 3.0f);
		body7.setTexSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
