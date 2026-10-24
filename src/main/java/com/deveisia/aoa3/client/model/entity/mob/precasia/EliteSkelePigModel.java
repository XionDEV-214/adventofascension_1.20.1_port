package com.deveisia.aoa3.client.model.entity.mob.precasia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class EliteSkelePigModel extends LegacyEntityModel<Mob> {
	private LegacyModelPart head1;
	private LegacyModelPart body1;
	private LegacyModelPart leg1;
	private LegacyModelPart leg2;
	private LegacyModelPart leg4;
	private LegacyModelPart head7;
	private LegacyModelPart head3;
	private LegacyModelPart body2;
	private LegacyModelPart body3;
	private LegacyModelPart body4;
	private LegacyModelPart body5;
	private LegacyModelPart head4;
	private LegacyModelPart head5;
	private LegacyModelPart head2;
	private LegacyModelPart head6;
	private LegacyModelPart leg3;

	public EliteSkelePigModel() {
		texWidth = 64;
		texHeight = 32;
		(head1 = new LegacyModelPart(this, 36, 0)).addBox(-2.0f, 4.0f, -12.0f, 1, 1, 6);
		head1.setPos(0.0f, 12.0f, -6.0f);
		head1.setTexSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body1.setPos(1.0f, 11.0f, 10.0f);
		body1.setTexSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setPos(-5.0f, 18.0f, -5.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setPos(6.0f, 18.0f, 7.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg4.setPos(6.0f, 18.0f, -5.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head7 = new LegacyModelPart(this, 16, 21)).addBox(2.0f, -7.0f, -6.0f, 2, 3, 4);
		head7.setPos(0.0f, 12.0f, -6.0f);
		head7.setTexSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 36, 0)).addBox(-4.0f, 4.0f, -12.0f, 1, 1, 6);
		head3.setPos(0.0f, 12.0f, -6.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body2.setPos(0.0f, 11.0f, 3.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body3.setPos(1.0f, 11.0f, 3.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body4.setPos(-3.0f, 11.0f, 10.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body5.setPos(-3.0f, 11.0f, 3.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 36, 0)).addBox(3.0f, 4.0f, -12.0f, 1, 1, 6);
		head4.setPos(0.0f, 12.0f, -6.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 36, 0)).addBox(1.0f, 4.0f, -12.0f, 1, 1, 6);
		head5.setPos(0.0f, 12.0f, -6.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head2.setPos(0.0f, 12.0f, -6.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 16, 19)).addBox(-4.0f, -7.0f, -6.0f, 2, 3, 4);
		head6.setPos(0.0f, 12.0f, -6.0f);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg3.setPos(-6.0f, 18.0f, 7.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.yRot = netHeadYaw / 57.295776f;
		head1.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 54.11268f;
		head5.yRot = netHeadYaw / 57.295776f;
		head5.xRot = headPitch / 54.11268f;
		head6.yRot = netHeadYaw / 57.295776f;
		head6.xRot = headPitch / 54.11268f;
		head7.yRot = netHeadYaw / 57.295776f;
		head7.xRot = headPitch / 54.11268f;
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
