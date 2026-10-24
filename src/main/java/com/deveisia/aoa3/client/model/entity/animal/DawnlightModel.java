package com.deveisia.aoa3.client.model.entity.animal;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class DawnlightModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart horn1;
	private final LegacyModelPart horn2;
	private final LegacyModelPart body2;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;

	public DawnlightModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setPos(0.0f, 6.0f, -8.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 18, 51)).addBox(-6.0f, -7.0f, -6.0f, 6, 1, 8);
		body.setPos(1.0f, 20.0f, 14.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, -0.7853982f, -0.6981317f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setPos(-3.0f, 12.0f, 7.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setPos(3.0f, 12.0f, 7.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3.setPos(-3.0f, 12.0f, -5.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4.setPos(3.0f, 12.0f, -5.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(horn1 = new LegacyModelPart(this, 17, 34)).addBox(-4.0f, -5.0f, -4.0f, 2, 2, 2);
		horn1.setPos(0.0f, 5.0f, -7.0f);
		horn1.setTexSize(64, 64);
		horn1.mirror = true;
		setRotation(horn1, 0.0f, 0.0f, 0.0f);
		(horn2 = new LegacyModelPart(this, 17, 34)).addBox(2.0f, -5.0f, -4.0f, 2, 2, 2);
		horn2.setPos(0.0f, 5.0f, -7.0f);
		horn2.setTexSize(64, 64);
		horn2.mirror = true;
		setRotation(horn2, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 18, 33)).addBox(-6.0f, -2.0f, -7.0f, 8, 3, 8);
		body2.setPos(2.0f, 12.0f, 14.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, -0.3490659f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 18, 51)).addBox(-6.0f, -7.0f, -7.0f, 6, 1, 8);
		body3.setPos(4.0f, 21.0f, 11.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, -0.7853982f, 0.6981317f, 0.0f);
		(body4 = new LegacyModelPart(this, 18, 9)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 6);
		body4.setPos(0.0f, 5.0f, 2.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body5.setPos(-1.0f, 11.0f, 10.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, -0.4363323f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body6.setPos(-1.0f, 7.0f, 2.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.4363323f, 0.0f, 0.0f);
		(body7 = new LegacyModelPart(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body7.setPos(-1.0f, 8.0f, 6.5f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head.yRot = netHeadYaw / 79.57747f;
		head.xRot = headPitch / 76.39437f;
		horn1.yRot = netHeadYaw / 79.57747f;
		horn1.xRot = headPitch / 76.39437f;
		horn2.yRot = netHeadYaw / 79.57747f;
		horn2.xRot = headPitch / 76.39437f;
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
