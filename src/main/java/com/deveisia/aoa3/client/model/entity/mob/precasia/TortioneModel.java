package com.deveisia.aoa3.client.model.entity.mob.precasia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class TortioneModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body1;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;

	public TortioneModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -3.0f, -8.0f, 8, 5, 8);
		head.setPos(0.0f, 12.0f, -6.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new LegacyModelPart(this, 77, 4)).addBox(-5.0f, -10.0f, -7.0f, 1, 18, 10);
		body1.setPos(11.0f, 11.0f, 3.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setPos(-3.0f, 18.0f, 7.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setPos(3.0f, 18.0f, 7.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg3.setPos(-3.0f, 18.0f, -5.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg4.setPos(3.0f, 18.0f, -5.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 102, 13)).addBox(-5.0f, -10.0f, -7.0f, 12, 18, 1);
		body2.setPos(-1.0f, 2.0f, 3.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 9, 21)).addBox(-5.0f, -10.0f, -7.0f, 2, 1, 10);
		body3.setPos(10.0f, 11.0f, 2.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 34, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body4.setPos(0.0f, 11.0f, 3.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 77, 4)).addBox(-5.0f, -10.0f, -7.0f, 1, 18, 10);
		body5.setPos(-2.0f, 11.0f, 3.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 79, 1)).addBox(-5.0f, -10.0f, -7.0f, 14, 1, 10);
		body6.setPos(-2.0f, 11.0f, 21.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 9, 21)).addBox(-5.0f, -10.0f, -7.0f, 2, 1, 10);
		body7.setPos(-2.0f, 11.0f, 2.0f);
		body7.setTexSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
