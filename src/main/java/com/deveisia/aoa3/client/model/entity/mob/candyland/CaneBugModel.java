package com.deveisia.aoa3.client.model.entity.mob.candyland;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class CaneBugModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart body;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart leg6;
	private final LegacyModelPart leg5;

	public CaneBugModel() {
		texWidth = 128;
		texHeight = 32;
		(leg1 = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, 0.0f, -1.0f, 2, 8, 2);
		leg1.setPos(-4.0f, 16.0f, 12.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, -1.0f, 2, 8, 2);
		leg2.setPos(4.0f, 16.0f, 12.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 2, 8, 2);
		leg3.setPos(-4.0f, 16.0f, -5.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, -2.0f, 2, 8, 2);
		leg4.setPos(4.0f, 16.0f, -5.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 5, 4)).addBox(-6.0f, -10.0f, -7.0f, 8, 21, 7);
		body.setPos(2.0f, 10.0f, 19.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 71, 12)).addBox(-6.0f, -10.0f, -7.0f, 8, 11, 8);
		body2.setPos(2.0f, 1.0f, 11.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 37, 2)).addBox(-6.0f, -10.0f, -7.0f, 8, 22, 8);
		body3.setPos(2.0f, 14.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(leg6 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, -1.0f, 2, 8, 2);
		leg6.setPos(4.0f, 16.0f, 3.0f);
		leg6.setTexSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0f, 0.0f, 0.0f);
		(leg5 = new LegacyModelPart(this, 0, 0)).addBox(-2.0f, 0.0f, -1.0f, 2, 8, 2);
		leg5.setPos(-4.0f, 16.0f, 3.0f);
		leg5.setTexSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leg5.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg5.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg6.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
