package com.deveisia.aoa3.client.model.entity.mob.mysterium;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class FungatModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart shape3;
	private final LegacyModelPart WingL;
	private final LegacyModelPart WingR;
	private final LegacyModelPart shape4;
	private final LegacyModelPart shape6;

	public FungatModel() {
		texWidth = 64;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 0, 14)).addBox(0.0f, 0.0f, 0.0f, 5, 2, 2);
		shape1.setPos(3.0f, 14.0f, -2.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape3 = new LegacyModelPart(this, 0, 14)).addBox(0.0f, 0.0f, 0.0f, 5, 2, 2);
		shape3.setPos(-8.0f, 14.0f, -2.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(WingL = new LegacyModelPart(this, 32, 0)).addBox(0.0f, 0.0f, -4.0f, 8, 1, 8);
		WingL.setPos(8.0f, 15.0f, -1.0f);
		WingL.setTexSize(64, 32);
		WingL.mirror = true;
		setRotation(WingL, 0.0f, 0.0f, 0.0f);
		(WingR = new LegacyModelPart(this, 32, 0)).addBox(-8.0f, 0.0f, -4.0f, 8, 1, 8);
		WingR.setPos(-8.0f, 15.0f, -1.0f);
		WingR.setTexSize(64, 32);
		WingR.mirror = true;
		setRotation(WingR, 0.0f, 0.0f, 0.0f);
		(shape4 = new LegacyModelPart(this, 16, 14)).addBox(-3.0f, -6.0f, -2.0f, 12, 6, 12);
		shape4.setPos(-3.0f, 12.0f, -4.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape6 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 8, 6, 8);
		shape6.setPos(-3.0f, 12.0f, -4.0f);
		shape6.setTexSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingL.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		WingR.zRot = Mth.cos(ageInTicks * 2.1f) * 3.1415927f * 0.15f;
		WingL.zRot = -WingR.zRot;
	}
}
