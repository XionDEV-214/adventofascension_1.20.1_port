package com.deveisia.aoa3.client.model.entity.mob.voxponds;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class FischerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart shape2;
	private final LegacyModelPart r1;
	private final LegacyModelPart shape3;
	private final LegacyModelPart r2;

	public FischerModel() {
		texWidth = 128;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		shape1.setPos(-4.0f, 11.0f, 9.0f);
		shape1.setTexSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		shape2.setPos(-6.0f, 9.0f, -6.0f);
		shape2.setTexSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(r1 = new LegacyModelPart(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r1.setPos(0.0f, 22.0f, -0.3333333f);
		r1.setTexSize(128, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		shape3.setPos(-5.0f, 10.0f, 2.0f);
		shape3.setTexSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r2.setPos(0.0f, 6.0f, -0.3333333f);
		r2.setTexSize(128, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.yRot = ageInTicks * 0.067f * 2.25f;
		r2.yRot = ageInTicks * 0.067f * 2.25f;
	}
}
