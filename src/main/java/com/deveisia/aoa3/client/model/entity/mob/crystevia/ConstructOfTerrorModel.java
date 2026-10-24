package com.deveisia.aoa3.client.model.entity.mob.crystevia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Mob;

public class ConstructOfTerrorModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart r1;
	private final LegacyModelPart shape1;
	private final LegacyModelPart shape2;
	private final LegacyModelPart shape3;
	private final LegacyModelPart shape4;
	private final LegacyModelPart shape5;
	private final LegacyModelPart r3;
	private final LegacyModelPart r4;
	private final LegacyModelPart r2;

	public ConstructOfTerrorModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(r1 = new LegacyModelPart(this, 29, 12)).addBox(-8.0f, -15.0f, -1.0f, 2, 3, 2);
		r1.setPos(0.0f, 16.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(shape1 = new LegacyModelPart(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 14, 6);
		shape1.setPos(-10.0f, 10.0f, -3.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 0, 0)).addBox(14.0f, 0.0f, 0.0f, 6, 14, 6);
		shape2.setPos(-10.0f, 10.0f, -3.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 25, 0)).addBox(6.0f, 5.0f, 1.0f, 2, 4, 4);
		shape3.setPos(-10.0f, 10.0f, -3.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new LegacyModelPart(this, 25, 0)).addBox(12.0f, 5.0f, 1.0f, 2, 4, 4);
		shape4.setPos(-10.0f, 10.0f, -3.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new LegacyModelPart(this, 39, 0)).addBox(8.0f, 4.0f, 1.0f, 4, 6, 4);
		shape5.setPos(-10.0f, 10.0f, -3.0f);
		shape5.setTexSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(r3 = new LegacyModelPart(this, 29, 12)).addBox(6.0f, -15.0f, -1.0f, 2, 3, 2);
		r3.setPos(0.0f, 16.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new LegacyModelPart(this, 39, 12)).addBox(-9.0f, -12.0f, -2.0f, 4, 5, 4);
		r4.setPos(0.0f, 16.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 39, 12)).addBox(5.0f, -12.0f, -2.0f, 4, 5, 4);
		r2.setPos(0.0f, 16.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.yRot = ageInTicks * 0.067f * 1.25f;
		r2.yRot = ageInTicks * 0.067f * 1.25f;
		r3.yRot = ageInTicks * 0.067f * 1.25f;
		r4.yRot = ageInTicks * 0.067f * 1.25f;
	}
}
