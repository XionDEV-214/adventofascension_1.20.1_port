package com.deveisia.aoa3.client.model.entity.mob.dustopia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class UrkaModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head1;
	private final LegacyModelPart body;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart body5;

	public UrkaModel() {
		texWidth = 128;
		texHeight = 32;
		(head1 = new LegacyModelPart(this, 100, 23)).addBox(2.5f, -5.5f, -17.0f, 1, 1, 5);
		head1.setPos(0.0f, 2.0f, -7.0f);
		head1.setTexSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.8726646f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 46, 0)).addBox(-6.0f, 0.0f, -7.0f, 12, 7, 7);
		body.setPos(0.0f, 5.0f, 2.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 90, 18)).addBox(-1.0f, 0.0f, 0.0f, 2, 12, 2);
		leg1.setPos(-5.0f, 12.0f, 7.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 90, 18)).addBox(-1.0f, 0.0f, 0.0f, 2, 12, 2);
		leg2.setPos(5.0f, 12.0f, 7.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 90, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leg3.setPos(-5.0f, 12.0f, -5.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 90, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leg4.setPos(5.0f, 12.0f, -5.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 89, 7)).addBox(-6.0f, -4.0f, -5.0f, 12, 4, 7);
		body2.setPos(0.0f, 5.0f, 2.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 0, 11)).addBox(0.0f, -9.0f, -1.0f, 0, 15, 6);
		body3.setPos(0.0f, 4.0f, 2.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 15, 18)).addBox(-6.0f, -8.0f, -7.0f, 12, 4, 10);
		body4.setPos(0.0f, 5.0f, 2.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -5.0f, -9.0f, 6, 6, 9);
		head2.setPos(0.0f, 2.0f, -7.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.8726646f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 100, 23)).addBox(-3.5f, -5.5f, -17.0f, 1, 1, 5);
		head3.setPos(0.0f, 2.0f, -7.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.8726646f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 106, 21)).addBox(2.0f, -6.0f, -12.0f, 2, 2, 9);
		head4.setPos(0.0f, 2.0f, -7.0f);
		head4.setTexSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.8726646f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 106, 21)).addBox(-4.0f, -6.0f, -12.0f, 2, 2, 9);
		head5.setPos(0.0f, 2.0f, -7.0f);
		head5.setTexSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.8726646f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 62, 17)).addBox(-4.0f, -12.0f, -4.0f, 8, 10, 4);
		body5.setPos(0.0f, 4.0f, 2.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.047198f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.yRot = netHeadYaw / (float)(180f / Math.PI);
		head2.yRot = netHeadYaw / (float)(180f / Math.PI);
		head3.yRot = netHeadYaw / (float)(180f / Math.PI);
		head4.yRot = netHeadYaw / (float)(180f / Math.PI);
		head5.yRot = netHeadYaw / (float)(180f / Math.PI);
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
