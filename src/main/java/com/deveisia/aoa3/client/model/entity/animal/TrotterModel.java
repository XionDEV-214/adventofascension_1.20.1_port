package com.deveisia.aoa3.client.model.entity.animal;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class TrotterModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart leg3p2;
	private final LegacyModelPart leg4p2;
	private final LegacyModelPart leg3p3;
	private final LegacyModelPart leg4p3;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;

	public TrotterModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setPos(0.0f, 10.0f, -8.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 25, 34)).addBox(6.0f, -7.0f, -1.0f, 2, 20, 7);
		body.setPos(0.0f, 8.0f, 2.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 2.356194f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 53)).addBox(0.0f, 10.0f, -7.0f, 2, 2, 4);
		leg3.setPos(-8.0f, 12.0f, 4.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 53)).addBox(2.0f, 10.0f, -7.0f, 2, 2, 4);
		leg4.setPos(8.0f, 12.0f, 4.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new LegacyModelPart(this, 0, 30)).addBox(-4.0f, 0.0f, -3.0f, 6, 12, 6);
		leg3p2.setPos(-8.0f, 12.0f, 4.0f);
		leg3p2.setTexSize(64, 64);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new LegacyModelPart(this, 0, 30)).addBox(-2.0f, 0.0f, -3.0f, 6, 12, 6);
		leg4p2.setPos(8.0f, 12.0f, 4.0f);
		leg4p2.setTexSize(64, 64);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p3 = new LegacyModelPart(this, 0, 53)).addBox(-4.0f, 10.0f, -7.0f, 2, 2, 4);
		leg3p3.setPos(-8.0f, 12.0f, 4.0f);
		leg3p3.setTexSize(64, 64);
		leg3p3.mirror = true;
		setRotation(leg3p3, 0.0f, 0.0f, 0.0f);
		(leg4p3 = new LegacyModelPart(this, 0, 53)).addBox(-2.0f, 10.0f, -7.0f, 2, 2, 4);
		leg4p3.setPos(8.0f, 12.0f, 4.0f);
		leg4p3.setTexSize(64, 64);
		leg4p3.mirror = true;
		setRotation(leg4p3, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 18, 4)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
		body2.setPos(0.0f, 8.0f, 2.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 25, 34)).addBox(-8.0f, -7.0f, -1.0f, 2, 20, 7);
		body3.setPos(0.0f, 8.0f, 2.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 2.356194f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
