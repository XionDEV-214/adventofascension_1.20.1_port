package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Mob;

public class GhastusModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart r1;
	private final LegacyModelPart head;
	private final LegacyModelPart r2;
	private final LegacyModelPart r3;
	private final LegacyModelPart r4;
	private final LegacyModelPart r5;
	private final LegacyModelPart r6;
	private final LegacyModelPart r7;
	private final LegacyModelPart r8;

	public GhastusModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 32;
		(r1 = new LegacyModelPart(this, 0, 0)).addBox(-10.0f, -2.0f, 2.0f, 1, 1, 1);
		r1.setPos(0.0f, 15.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 15.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 0, 0)).addBox(-7.0f, -3.0f, -4.0f, 1, 1, 1);
		r2.setPos(0.0f, 15.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, 0.0f, 2.0f, 1, 1, 1);
		r3.setPos(0.0f, 15.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new LegacyModelPart(this, 0, 0)).addBox(-9.0f, 0.0f, -1.0f, 1, 1, 1);
		r4.setPos(0.0f, 15.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new LegacyModelPart(this, 0, 0)).addBox(-7.0f, 2.0f, 2.0f, 1, 1, 1);
		r5.setPos(0.0f, 15.0f, 0.0f);
		r5.setTexSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new LegacyModelPart(this, 0, 0)).addBox(-7.0f, 2.0f, -3.0f, 1, 1, 1);
		r6.setPos(0.0f, 15.0f, 0.0f);
		r6.setTexSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new LegacyModelPart(this, 0, 0)).addBox(-10.0f, 0.0f, -3.0f, 1, 1, 1);
		r7.setPos(0.0f, 15.0f, 0.0f);
		r7.setTexSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new LegacyModelPart(this, 0, 0)).addBox(-6.0f, -2.0f, -1.0f, 1, 1, 1);
		r8.setPos(0.0f, 15.0f, 0.0f);
		r8.setTexSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.zRot = ageInTicks * 0.067f * 1.25f;
		r2.zRot = ageInTicks * 0.067f * 1.25f;
		r3.zRot = ageInTicks * 0.067f * 1.25f;
		r4.zRot = ageInTicks * 0.067f * 1.25f;
		r5.zRot = ageInTicks * 0.067f * 1.25f;
		r6.zRot = ageInTicks * 0.067f * 1.25f;
		r7.zRot = ageInTicks * 0.067f * 1.25f;
		r8.zRot = ageInTicks * 0.067f * 1.25f;
	}
}
