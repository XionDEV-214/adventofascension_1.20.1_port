package com.deveisia.aoa3.client.model.entity.mob.deeplands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class RockCrawlerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart RearEnd;
	private final LegacyModelPart Leg6;
	private final LegacyModelPart Leg4;
	private final LegacyModelPart Leg2;
	private final LegacyModelPart Leg5;
	private final LegacyModelPart Leg3;
	private final LegacyModelPart Leg1;
	private final LegacyModelPart Leg8;
	private final LegacyModelPart Leg7;
	private final LegacyModelPart RearEnd2;
	private final LegacyModelPart RearEnd3;

	public RockCrawlerModel() {
		texWidth = 128;
		texHeight = 32;
		(RearEnd = new LegacyModelPart(this, 0, 10)).addBox(-9.0f, -15.0f, -7.0f, 8, 8, 8);
		RearEnd.setPos(4.0f, 8.0f, 4.0f);
		RearEnd.setTexSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg6 = new LegacyModelPart(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg6.setPos(6.0f, 16.0f, 2.0f);
		Leg6.setTexSize(128, 32);
		Leg6.mirror = true;
		setRotation(Leg6, 0.0f, 0.2792527f, 0.1919862f);
		(Leg4 = new LegacyModelPart(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg4.setPos(6.0f, 16.0f, 4.0f);
		Leg4.setTexSize(128, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0.0f, -0.2792527f, 0.1919862f);
		(Leg2 = new LegacyModelPart(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg2.setPos(6.0f, 16.0f, 6.0f);
		Leg2.setTexSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg5 = new LegacyModelPart(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg5.setPos(-6.0f, 16.0f, 2.0f);
		Leg5.setTexSize(128, 32);
		Leg5.mirror = true;
		setRotation(Leg5, 0.0f, -0.2792527f, -0.1919862f);
		(Leg3 = new LegacyModelPart(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg3.setPos(-6.0f, 16.0f, 4.0f);
		Leg3.setTexSize(128, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0.0f, 0.2792527f, -0.1919862f);
		(Leg1 = new LegacyModelPart(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg1.setPos(-6.0f, 16.0f, 6.0f);
		Leg1.setTexSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg8 = new LegacyModelPart(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg8.setPos(6.0f, 16.0f, -1.0f);
		Leg8.setTexSize(128, 32);
		Leg8.mirror = true;
		setRotation(Leg8, 0.0f, 0.5759587f, 0.1919862f);
		(Leg7 = new LegacyModelPart(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg7.setPos(-6.0f, 16.0f, -1.0f);
		Leg7.setTexSize(128, 32);
		Leg7.mirror = true;
		setRotation(Leg7, 0.0f, -0.5759587f, -0.1919862f);
		(RearEnd2 = new LegacyModelPart(this, 70, 6)).addBox(-8.0f, -6.0f, -6.0f, 14, 12, 14);
		RearEnd2.setPos(0.0f, 12.0f, 1.0f);
		RearEnd2.setTexSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new LegacyModelPart(this, 35, 10)).addBox(-7.0f, -7.0f, -5.0f, 4, 5, 4);
		RearEnd3.setPos(4.0f, 8.0f, 4.0f);
		RearEnd3.setTexSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		RearEnd.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		final float var8 = 0.7853982f;
		Leg1.zRot = -var8;
		Leg2.zRot = var8;
		Leg3.zRot = -var8 * 0.74f;
		Leg4.zRot = var8 * 0.74f;
		Leg5.zRot = -var8 * 0.74f;
		Leg6.zRot = var8 * 0.74f;
		Leg7.zRot = -var8;
		Leg8.zRot = var8;
		final float var9 = -0.0f;
		final float var10 = 0.3926991f;
		Leg1.yRot = var10 * 2.0f + var9;
		Leg2.yRot = -var10 * 2.0f - var9;
		Leg3.yRot = var10 * 1.0f + var9;
		Leg4.yRot = -var10 * 1.0f - var9;
		Leg5.yRot = -var10 * 1.0f + var9;
		Leg6.yRot = var10 * 1.0f - var9;
		Leg7.yRot = -var10 * 2.0f + var9;
		Leg8.yRot = var10 * 2.0f - var9;
		final float var11 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var12 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var13 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var14 = -(Mth.cos(limbSwing * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbSwingAmount;
		final float var15 = Math.abs(Mth.sin(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var16 = Math.abs(Mth.sin(limbSwing * 0.6662f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var17 = Math.abs(Mth.sin(limbSwing * 0.6662f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var18 = Math.abs(Mth.sin(limbSwing * 0.6662f + 4.712389f) * 0.4f) * limbSwingAmount;
		Leg1.yRot += var11;
		Leg2.yRot += -var11;
		Leg3.yRot += var12;
		Leg4.yRot += -var12;
		Leg5.yRot += var13;
		Leg6.yRot += -var13;
		Leg7.yRot += var14;
		Leg8.yRot += -var14;
		Leg1.zRot += var15;
		Leg2.zRot += -var15;
		Leg3.zRot += var16;
		Leg4.zRot += -var16;
		Leg5.zRot += var17;
		Leg6.zRot += -var17;
		Leg7.zRot += var18;
		Leg8.zRot += -var18;
	}
}
