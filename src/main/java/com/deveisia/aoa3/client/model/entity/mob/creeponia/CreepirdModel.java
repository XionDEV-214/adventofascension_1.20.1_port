package com.deveisia.aoa3.client.model.entity.mob.creeponia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class CreepirdModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart wingR1;
	private final LegacyModelPart head;
	private final LegacyModelPart wingR2;
	private final LegacyModelPart shape1;
	private final LegacyModelPart wingL1;
	private final LegacyModelPart wingL2;
	private final LegacyModelPart shape2;

	public CreepirdModel() {
		texWidth = 64;
		texHeight = 32;
		(wingR1 = new LegacyModelPart(this, 30, 20)).addBox(-10.0f, 0.0f, -4.0f, 8, 0, 8);
		wingR1.setPos(-6.0f, 17.0f, 0.0f);
		wingR1.setTexSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 17.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingR2 = new LegacyModelPart(this, 33, 0)).addBox(-2.0f, -1.0f, -4.0f, 2, 2, 8);
		wingR2.setPos(-6.0f, 17.0f, 0.0f);
		wingR2.setTexSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape1 = new LegacyModelPart(this, 30, 2)).addBox(-10.0f, -0.5f, -1.0f, 2, 1, 2);
		shape1.setPos(4.0f, 17.0f, 0.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(wingL1 = new LegacyModelPart(this, 30, 11)).addBox(2.0f, 0.0f, -4.0f, 8, 0, 8);
		wingL1.setPos(6.0f, 17.0f, 0.0f);
		wingL1.setTexSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingL2 = new LegacyModelPart(this, 33, 0)).addBox(0.0f, -1.0f, -4.0f, 2, 2, 8);
		wingL2.setPos(6.0f, 17.0f, 0.0f);
		wingL2.setTexSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(shape2 = new LegacyModelPart(this, 30, 2)).addBox(0.0f, -0.5f, -1.0f, 2, 1, 2);
		shape2.setPos(4.0f, 17.0f, 0.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		wingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingR1.zRot = Mth.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		wingL1.zRot = -wingR1.zRot;
		wingR2.zRot = Mth.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		wingL2.zRot = -wingR1.zRot;
	}
}
