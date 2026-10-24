package com.deveisia.aoa3.client.model.entity.mob.shyrelands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class AxiolightModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body2;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart r1;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart body;
	private final LegacyModelPart body3;
	private final LegacyModelPart body4;

	public AxiolightModel() {
		texWidth = 64;
		texHeight = 64;

		head = new LegacyModelPart(this, 33, 0);
		head.addBox(4.0F, -15.0F, -4.0F, 2, 12, 8);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body2 = new LegacyModelPart(this, 25, 53);
		body2.addBox(-3.0F, 18.0F, -3.0F, 6, 5, 6);
		body2.setPos(0.0F, -3.0F, 0.0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightLeg = new LegacyModelPart(this, 0, 53);
		rightLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6);
		rightLeg.setPos(-4.0F, 20.0F, 0.0F);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new LegacyModelPart(this, 0, 53);
		leftLeg.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6);
		leftLeg.setPos(4.0F, 20.0F, 0.0F);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		r1 = new LegacyModelPart(this, 51, 0);
		r1.addBox(-2.0F, -12.0F, -1.0F, 4, 6, 2);
		r1.setPos(0.0F, 0.0F, 0.0F);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		head2 = new LegacyModelPart(this, 0, 0);
		head2.addBox(-4.0F, -15.0F, -4.0F, 8, 2, 8);
		head2.setPos(0.0F, 0.0F, 0.0F);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new LegacyModelPart(this, 33, 0);
		head3.addBox(-6.0F, -15.0F, -4.0F, 2, 12, 8);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
		head4 = new LegacyModelPart(this, 0, 0);
		head4.addBox(-4.0F, -5.0F, -4.0F, 8, 2, 8);
		head4.setPos(0.0F, 0.0F, 0.0F);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		body = new LegacyModelPart(this, 8, 16);
		body.addBox(-4.0F, 0.0F, -3.0F, 8, 6, 6);
		body.setPos(0.0F, -3.0F, 0.0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		body3 = new LegacyModelPart(this, 16, 29);
		body3.addBox(-5.0F, 6.0F, -3.0F, 10, 6, 6);
		body3.setPos(0.0F, -3.0F, 0.0F);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new LegacyModelPart(this, 16, 41);
		body4.addBox(-6.0F, 12.0F, -3.0F, 12, 6, 6);
		body4.setPos(0.0F, -3.0F, 0.0F);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = (Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		leftLeg.xRot = (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		r1.yRot = (ageInTicks * 0.067F * 1.25F);
	}
}
