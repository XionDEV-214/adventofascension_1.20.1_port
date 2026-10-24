package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class ShadowStalkerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart Eye;
	private final LegacyModelPart shape2;
	private final LegacyModelPart shape3;
	private final LegacyModelPart shape4;
	private final LegacyModelPart shape5;

	public ShadowStalkerModel() {
		texWidth = 64;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 3, 20)).addBox(-8.0f, 0.0f, -1.0f, 12, 1, 10);
		shape1.setPos(2.0f, 21.0f, -4.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.1745329f, 0.0f, 0.0f);
		(Eye = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, -10.0f, -4.0f, 12, 10, 8);
		Eye.setPos(0.0f, 19.0f, 0.0f);
		Eye.setTexSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 54, 20)).addBox(2.0f, -14.0f, 6.0f, 2, 5, 2);
		shape2.setPos(0.0f, 19.0f, -4.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.5235988f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 0, 20)).addBox(-8.0f, -12.0f, -1.0f, 16, 2, 10);
		shape3.setPos(0.0f, 19.0f, -4.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new LegacyModelPart(this, 54, 20)).addBox(-4.0f, -14.0f, 6.0f, 2, 5, 2);
		shape4.setPos(0.0f, 19.0f, -4.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.5235988f, 0.0f, 0.0f);
		(shape5 = new LegacyModelPart(this, 0, 20)).addBox(-8.0f, 0.0f, -1.0f, 16, 1, 10);
		shape5.setPos(0.0f, 19.0f, -4.0f);
		shape5.setTexSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Eye.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Eye.yRot = netHeadYaw / 57.295776f;
	}
}
