package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class OrblingModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart shape1;
	private final LegacyModelPart r1;
	private final LegacyModelPart r3;
	private final LegacyModelPart r2;
	private final LegacyModelPart r4;

	public OrblingModel() {
		texWidth = 64;
		texHeight = 32;
		(shape1 = new LegacyModelPart(this, 0, 6)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
		shape1.setPos(0.0f, 15.0f, 0.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(r1 = new LegacyModelPart(this, 0, 20)).addBox(-5.0f, -5.0f, -3.0f, 10, 2, 0);
		r1.setPos(0.0f, 15.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r3 = new LegacyModelPart(this, 0, 16)).addBox(-5.0f, 3.0f, 3.0f, 10, 2, 0);
		r3.setPos(0.0f, 15.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r2 = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, -7.0f, -4.0f, 10, 2, 2);
		r2.setPos(0.0f, 15.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r4 = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, 5.0f, 2.0f, 10, 2, 2);
		r4.setPos(0.0f, 15.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.zRot = ageInTicks * 0.067f * 5.25f;
		r2.zRot = ageInTicks * 0.067f * 5.25f;
		r3.zRot = ageInTicks * 0.067f * -5.25f;
		r4.zRot = ageInTicks * 0.067f * -5.25f;
	}
}
