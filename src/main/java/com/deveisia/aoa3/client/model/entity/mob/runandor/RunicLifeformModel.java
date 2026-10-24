package com.deveisia.aoa3.client.model.entity.mob.runandor;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class RunicLifeformModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart shape2;
	private final LegacyModelPart shape3;
	private final LegacyModelPart shape4;
	private final LegacyModelPart shape5;
	private final LegacyModelPart shape6;
	private final LegacyModelPart shape7;
	private final LegacyModelPart shape8;
	private final LegacyModelPart shape9;
	private final LegacyModelPart shape10;
	private final LegacyModelPart shape11;
	private final LegacyModelPart shape12;

	public RunicLifeformModel() {
		texWidth = 128;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		shape1.setPos(3.0f, 17.0f, -3.0f);
		shape1.setTexSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		shape2.setPos(6.0f, 6.0f, -3.0f);
		shape2.setTexSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		shape3.setPos(-5.0f, 3.0f, -3.0f);
		shape3.setTexSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new LegacyModelPart(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		shape4.setPos(-8.0f, 6.0f, -3.0f);
		shape4.setTexSize(128, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new LegacyModelPart(this, 42, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		shape5.setPos(-5.0f, 2.0f, -6.0f);
		shape5.setTexSize(128, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new LegacyModelPart(this, 84, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		shape6.setPos(-5.0f, 13.0f, -6.0f);
		shape6.setTexSize(128, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new LegacyModelPart(this, 73, 23)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
		shape7.setPos(-2.0f, 14.0f, -3.0f);
		shape7.setTexSize(128, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new LegacyModelPart(this, 73, 14)).addBox(0.0f, 0.0f, 0.0f, 16, 3, 4);
		shape8.setPos(-8.0f, 19.0f, -3.0f);
		shape8.setTexSize(128, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new LegacyModelPart(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		shape9.setPos(-6.0f, 17.0f, -3.0f);
		shape9.setTexSize(128, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new LegacyModelPart(this, 25, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		shape10.setPos(5.0f, 2.0f, -6.0f);
		shape10.setTexSize(128, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new LegacyModelPart(this, 2, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		shape11.setPos(-6.0f, 2.0f, -6.0f);
		shape11.setTexSize(128, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		shape12.setPos(-5.0f, 3.0f, 3.0f);
		shape12.setTexSize(128, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
