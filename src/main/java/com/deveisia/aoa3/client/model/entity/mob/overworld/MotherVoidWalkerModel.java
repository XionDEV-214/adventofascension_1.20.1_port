package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class MotherVoidWalkerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart body2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head2;
	private final LegacyModelPart headfin2;
	private final LegacyModelPart head1;
	private final LegacyModelPart headfin1;
	private final LegacyModelPart head4;

	public MotherVoidWalkerModel() {
		texWidth = 64;
		texHeight = 32;
		(body = new LegacyModelPart(this, 16, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		body.setPos(7.0f, 6.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-6.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(6.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 16, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		body2.setPos(-7.0f, 6.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head3.setPos(9.0f, 6.0f, 0.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setPos(-9.0f, 6.0f, 0.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(headfin2 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		headfin2.setPos(-9.0f, 6.0f, 0.0f);
		headfin2.setTexSize(64, 32);
		headfin2.mirror = true;
		setRotation(headfin2, 0.0f, 0.0f, 0.0f);
		(head1 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head1.setPos(0.0f, 10.0f, 0.0f);
		head1.setTexSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(headfin1 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		headfin1.setPos(0.0f, 10.0f, 0.0f);
		headfin1.setTexSize(64, 32);
		headfin1.mirror = true;
		setRotation(headfin1, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		head4.setPos(9.0f, 6.0f, 0.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		headfin2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		headfin1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
