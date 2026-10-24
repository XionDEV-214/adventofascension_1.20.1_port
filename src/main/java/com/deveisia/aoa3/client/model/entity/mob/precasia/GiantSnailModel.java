package com.deveisia.aoa3.client.model.entity.mob.precasia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class GiantSnailModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart shape2;
	private final LegacyModelPart shape3;
	private final LegacyModelPart shape5;
	private final LegacyModelPart shape6;
	private final LegacyModelPart shape4;

	public GiantSnailModel() {
		texWidth = 64;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 21, 21)).addBox(-2.5f, 0.0f, -0.5f, 2, 2, 2);
		shape1.setPos(4.0f, 16.0f, -6.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 6, 10, 10);
		shape2.setPos(-2.0f, 14.0f, -2.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 0, 21)).addBox(-1.0f, 0.0f, 0.0f, 4, 4, 5);
		shape3.setPos(-1.0f, 20.0f, -7.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape5 = new LegacyModelPart(this, 21, 21)).addBox(-2.5f, 0.0f, -0.5f, 2, 2, 2);
		shape5.setPos(-1.0f, 16.0f, -6.0f);
		shape5.setTexSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new LegacyModelPart(this, 19, 26)).addBox(-2.0f, 0.0f, 0.0f, 1, 4, 1);
		shape6.setPos(4.0f, 18.0f, -6.0f);
		shape6.setTexSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape4 = new LegacyModelPart(this, 19, 26)).addBox(-2.0f, 0.0f, 0.0f, 1, 4, 1);
		shape4.setPos(-1.0f, 18.0f, -6.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}
