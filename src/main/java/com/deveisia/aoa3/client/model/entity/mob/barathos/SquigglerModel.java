package com.deveisia.aoa3.client.model.entity.mob.barathos;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class SquigglerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body1;
	private final LegacyModelPart Leg8;
	private final LegacyModelPart Leg2;
	private final LegacyModelPart Leg7;
	private final LegacyModelPart Leg1;
	private final LegacyModelPart Leg2p2;
	private final LegacyModelPart Leg8p2;
	private final LegacyModelPart Leg1p2;
	private final LegacyModelPart Leg7p2;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart head;
	private final LegacyModelPart body4;

	public SquigglerModel() {
		texWidth = 64;
		texHeight = 64;
		(body1 = new LegacyModelPart(this, 0, 32)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 4);
		body1.setPos(5.0f, 7.0f, 6.0f);
		body1.setTexSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, -1.22173f);
		(Leg8 = new LegacyModelPart(this, 0, 51)).addBox(11.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg8.setPos(4.0f, 21.0f, 1.0f);
		Leg8.setTexSize(64, 64);
		Leg8.mirror = true;
		setRotation(Leg8, 0.0f, 0.5759587f, 0.0f);
		(Leg2 = new LegacyModelPart(this, 0, 51)).addBox(11.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg2.setPos(4.0f, 21.0f, 8.0f);
		Leg2.setTexSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.0f);
		(Leg7 = new LegacyModelPart(this, 0, 51)).addBox(-15.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg7.setPos(-4.0f, 21.0f, 1.0f);
		Leg7.setTexSize(64, 64);
		Leg7.mirror = true;
		setRotation(Leg7, 0.0f, -0.5759587f, 0.0f);
		(Leg1 = new LegacyModelPart(this, 0, 51)).addBox(-15.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg1.setPos(-4.0f, 21.0f, 8.0f);
		Leg1.setTexSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, 0.0f);
		(Leg2p2 = new LegacyModelPart(this, 18, 55)).addBox(-1.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg2p2.setPos(4.0f, 21.0f, 8.0f);
		Leg2p2.setTexSize(64, 64);
		Leg2p2.mirror = true;
		setRotation(Leg2p2, 0.0f, -0.5759587f, 0.0f);
		(Leg8p2 = new LegacyModelPart(this, 18, 55)).addBox(-1.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg8p2.setPos(4.0f, 21.0f, 1.0f);
		Leg8p2.setTexSize(64, 64);
		Leg8p2.mirror = true;
		setRotation(Leg8p2, 0.0f, 0.5759587f, 0.0f);
		(Leg1p2 = new LegacyModelPart(this, 18, 55)).addBox(-15.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg1p2.setPos(-4.0f, 21.0f, 8.0f);
		Leg1p2.setTexSize(64, 64);
		Leg1p2.mirror = true;
		setRotation(Leg1p2, 0.0f, 0.5759587f, 0.0f);
		(Leg7p2 = new LegacyModelPart(this, 18, 55)).addBox(-15.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg7p2.setPos(-4.0f, 21.0f, 1.0f);
		Leg7p2.setTexSize(64, 64);
		Leg7p2.mirror = true;
		setRotation(Leg7p2, 0.0f, -0.5759587f, 0.0f);
		(body2 = new LegacyModelPart(this, 21, 35)).addBox(-5.0f, -4.0f, -6.0f, 10, 7, 11);
		body2.setPos(0.0f, 20.0f, 5.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 34, 16)).addBox(-5.0f, -4.0f, -6.0f, 8, 11, 6);
		body3.setPos(1.0f, 9.0f, 8.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 14)).addBox(-5.0f, -4.0f, -6.0f, 8, 8, 8);
		head.setPos(1.0f, 1.0f, 7.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body4 = new LegacyModelPart(this, 0, 32)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 4);
		body4.setPos(-4.0f, 11.0f, 6.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 1.22173f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		final float var8 = 0.7853982f;
		Leg1.zRot = -var8;
		Leg2.zRot = var8;
		Leg7.zRot = -var8;
		Leg8.zRot = var8;
		Leg1p2.zRot = -var8;
		Leg2p2.zRot = var8;
		Leg7p2.zRot = -var8;
		Leg8p2.zRot = var8;
		final float var9 = -0.0f;
		final float var10 = 0.3926991f;
		Leg1.yRot = var10 * 2.0f + var9;
		Leg2.yRot = -var10 * 2.0f - var9;
		Leg7.yRot = -var10 * 2.0f + var9;
		Leg8.yRot = var10 * 2.0f - var9;
		Leg1p2.yRot = var10 * 2.0f + var9;
		Leg2p2.yRot = -var10 * 2.0f - var9;
		Leg7p2.yRot = -var10 * 2.0f + var9;
		Leg8p2.yRot = var10 * 2.0f - var9;
		final float var11 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var12 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var13 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var14 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbSwingAmount;
		final float var15 = Math.abs(Mth.sin(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var16 = Math.abs(Mth.sin(limbSwing * 0.6662f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var17 = Math.abs(Mth.sin(limbSwing * 0.6662f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var18 = Math.abs(Mth.sin(limbSwing * 0.6662f + 4.712389f) * 0.4f) * limbSwingAmount;
		final LegacyModelPart leg1 = Leg1;
		leg1.yRot += var11;
		final LegacyModelPart leg2 = Leg2;
		leg2.yRot += -var11;
		final LegacyModelPart leg3 = Leg7;
		leg3.yRot += var14;
		final LegacyModelPart leg4 = Leg8;
		leg4.yRot += -var14;
		final LegacyModelPart leg5 = Leg1;
		leg5.zRot += var15;
		final LegacyModelPart leg6 = Leg2;
		leg6.zRot += -var15;
		final LegacyModelPart leg7 = Leg7;
		leg7.zRot += var18;
		final LegacyModelPart leg8 = Leg8;
		leg8.zRot += -var18;
		final LegacyModelPart leg1p2 = Leg1p2;
		leg1p2.yRot += var11;
		final LegacyModelPart leg2p2 = Leg2p2;
		leg2p2.yRot += -var11;
		final LegacyModelPart leg7p2 = Leg7p2;
		leg7p2.yRot += var14;
		final LegacyModelPart leg8p2 = Leg8p2;
		leg8p2.yRot += -var14;
		final LegacyModelPart leg1p3 = Leg1p2;
		leg1p3.zRot += var15;
		final LegacyModelPart leg2p3 = Leg2p2;
		leg2p3.zRot += -var15;
		final LegacyModelPart leg7p3 = Leg7p2;
		leg7p3.zRot += var18;
		final LegacyModelPart leg8p3 = Leg8p2;
		leg8p3.zRot += -var18;
	}
}
