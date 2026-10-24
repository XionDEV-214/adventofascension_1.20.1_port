package com.deveisia.aoa3.client.model.entity.mob.gardencia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class BroccoheadModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart head;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;
	private final LegacyModelPart body5;
	private final LegacyModelPart body6;
	private final LegacyModelPart body7;

	public BroccoheadModel() {
		texWidth = 128;
		texHeight = 32;
		(body = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body.setPos(-4.0f, -8.0f, -5.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.6108652f, 1.117011f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 79, 12)).addBox(-4.0f, 0.0f, -4.0f, 8, 12, 8);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 2, 11)).addBox(-5.0f, -5.0f, 2.0f, 12, 5, 12);
		head.setPos(-1.0f, -7.0f, -1.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 53, 14)).addBox(-2.0f, -7.0f, 5.0f, 6, 2, 6);
		head2.setPos(-1.0f, -7.0f, -1.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 2, 11)).addBox(-14.0f, -5.0f, -12.0f, 12, 5, 12);
		head3.setPos(1.0f, -6.0f, 3.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 53, 14)).addBox(-11.0f, -7.0f, -9.0f, 6, 2, 6);
		head4.setPos(1.0f, -6.0f, 3.0f);
		head4.setTexSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 2, 11)).addBox(1.0f, -5.0f, -16.0f, 12, 5, 12);
		head5.setPos(-2.0f, -5.0f, 6.0f);
		head5.setTexSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 53, 14)).addBox(4.0f, -7.0f, -13.0f, 6, 2, 6);
		head6.setPos(-2.0f, -5.0f, 6.0f);
		head6.setTexSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(body3 = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body3.setPos(-4.0f, -8.0f, -5.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.6108652f, 1.117011f, 0.0f);
		(body4 = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, 0.0f, -4.0f, 4, 10, 4);
		body4.setPos(2.0f, -8.0f, 11.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, -0.5235988f, 0.0f, 0.0f);
		(body5 = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, 10.0f, -4.0f, 4, 5, 4);
		body5.setPos(2.0f, -8.0f, 11.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, -0.5235988f, 0.0f, 0.0f);
		(body6 = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body6.setPos(6.0f, -8.0f, -2.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.5235988f, -0.5235988f, 0.0f);
		(body7 = new LegacyModelPart(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body7.setPos(6.0f, -8.0f, -2.0f);
		body7.setTexSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, -0.5235988f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
