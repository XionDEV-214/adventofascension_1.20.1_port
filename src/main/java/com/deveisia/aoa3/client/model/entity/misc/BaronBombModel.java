package com.deveisia.aoa3.client.model.entity.misc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.misc.BaronBombEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class BaronBombModel extends LegacyEntityModel<BaronBombEntity> {
	private final LegacyModelPart r5;
	private final LegacyModelPart r4;
	private final LegacyModelPart r3;
	private final LegacyModelPart r2;
	private final LegacyModelPart r1;
	private final LegacyModelPart shape1;
	private final LegacyModelPart r6;
	private final LegacyModelPart r7;
	private final LegacyModelPart r8;

	public BaronBombModel() {
		texWidth = 64;
		texHeight = 32;

		r5 = new LegacyModelPart(this, 0, 17);
		r5.addBox(6F, -6F, -2F, 4, 4, 4);
		r5.setPos(0F, 0F, 0F);
		r5.setTexSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0F, 0F, 0F);
		r4 = new LegacyModelPart(this, 34, 0);
		r4.addBox(1F, -13F, 1F, 3, 4, 3);
		r4.setPos(0F, 0F, 0F);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0F, 0F, 0F);
		r3 = new LegacyModelPart(this, 0, 17);
		r3.addBox(-2F, -6F, -10F, 4, 4, 4);
		r3.setPos(0F, 0F, 0F);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0F, 0F, 0F);
		r2 = new LegacyModelPart(this, 0, 17);
		r2.addBox(-10F, -6F, -2F, 4, 4, 4);
		r2.setPos(0F, 0F, 0F);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0F, 0F, 0F);
		r1 = new LegacyModelPart(this, 0, 17);
		r1.addBox(-2F, -6F, 6F, 4, 4, 4);
		r1.setPos(0F, 0F, 0F);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0F, 0F, 0F);
		shape1 = new LegacyModelPart(this, 0, 0);
		shape1.addBox(-4F, -8F, -4F, 8, 8, 8);
		shape1.setPos(0F, 0F, 0F);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		r6 = new LegacyModelPart(this, 34, 0);
		r6.addBox(1F, -13F, -4F, 3, 4, 3);
		r6.setPos(0F, 0F, 0F);
		r6.setTexSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0F, 0F, 0F);
		r7 = new LegacyModelPart(this, 34, 0);
		r7.addBox(-4F, -13F, 1F, 3, 4, 3);
		r7.setPos(0F, 0F, 0F);
		r7.setTexSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0F, 0F, 0F);
		r8 = new LegacyModelPart(this, 34, 0);
		r8.addBox(-4F, -13F, -4F, 3, 4, 3);
		r8.setPos(0F, 0F, 0F);
		r8.setTexSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0F, 0F, 0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(BaronBombEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.r1.yRot = (ageInTicks * 0.067F) * 1.25F;
		this.r2.yRot = (ageInTicks * 0.067F) * 1.25F;

		this.r3.yRot = (ageInTicks * 0.067F) * 1.25F;
		this.r4.yRot = (ageInTicks * 0.067F) * 1.25F;

		this.r5.yRot = (ageInTicks * 0.067F) * 1.25F;
		this.r6.yRot = (ageInTicks * 0.067F) * 1.25F;

		this.r7.yRot = (ageInTicks * 0.067F) * 1.25F;
		this.r8.yRot = (ageInTicks * 0.067F) * 1.25F;
	}
}
