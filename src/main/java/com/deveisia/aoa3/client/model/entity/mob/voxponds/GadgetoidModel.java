package com.deveisia.aoa3.client.model.entity.mob.voxponds;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class GadgetoidModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart rightLeg;
	private final LegacyModelPart leftLeg;
	private final LegacyModelPart rightLeg2;
	private final LegacyModelPart leftLeg2;
	private final LegacyModelPart Turninghead;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head7;
	private final LegacyModelPart head8;
	private final LegacyModelPart head9;
	private final LegacyModelPart head10;
	private final LegacyModelPart head11;
	private final LegacyModelPart head12;

	public GadgetoidModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, 0.0f, -4.0f, 6, 2, 6);
		head.setPos(1.0f, 5.0f, 1.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-6.0f, 12.0f, 6.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(6.0f, 12.0f, 6.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg2.setPos(-6.0f, 12.0f, -6.0f);
		rightLeg2.setTexSize(128, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg2.setPos(6.0f, 12.0f, -6.0f);
		leftLeg2.setTexSize(128, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(Turninghead = new LegacyModelPart(this, 36, 16)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		Turninghead.setPos(0.0f, 3.0f, 0.0f);
		Turninghead.setTexSize(128, 32);
		Turninghead.mirror = true;
		setRotation(Turninghead, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 36, 1)).addBox(6.0f, 0.5f, 12.0f, 4, 2, 1);
		head3.setPos(-4.0f, 9.0f, -4.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 18, 15)).addBox(-4.0f, 0.0f, -4.0f, 4, 6, 4);
		head4.setPos(2.0f, 3.0f, 2.0f);
		head4.setTexSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 61, 0)).addBox(-4.0f, 0.0f, -4.0f, 16, 3, 16);
		head5.setPos(-4.0f, 9.0f, -4.0f);
		head5.setTexSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 36, 1)).addBox(-2.0f, 0.5f, 12.0f, 4, 2, 1);
		head6.setPos(-4.0f, 9.0f, -4.0f);
		head6.setTexSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new LegacyModelPart(this, 36, 1)).addBox(6.0f, 0.5f, -5.0f, 4, 2, 1);
		head7.setPos(-4.0f, 9.0f, -4.0f);
		head7.setTexSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new LegacyModelPart(this, 36, 6)).addBox(12.0f, 0.5f, 6.0f, 1, 2, 4);
		head8.setPos(-4.0f, 9.0f, -4.0f);
		head8.setTexSize(128, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new LegacyModelPart(this, 36, 1)).addBox(-2.0f, 0.5f, -5.0f, 4, 2, 1);
		head9.setPos(-4.0f, 9.0f, -4.0f);
		head9.setTexSize(128, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new LegacyModelPart(this, 36, 6)).addBox(12.0f, 0.5f, -2.0f, 1, 2, 4);
		head10.setPos(-4.0f, 9.0f, -4.0f);
		head10.setTexSize(128, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new LegacyModelPart(this, 36, 6)).addBox(-5.0f, 0.5f, 6.0f, 1, 2, 4);
		head11.setPos(-4.0f, 9.0f, -4.0f);
		head11.setTexSize(128, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new LegacyModelPart(this, 36, 6)).addBox(-5.0f, 0.5f, -2.0f, 1, 2, 4);
		head12.setPos(-4.0f, 9.0f, -4.0f);
		head12.setTexSize(128, 32);
		head12.mirror = true;
		setRotation(head12, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Turninghead.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Turninghead.yRot = netHeadYaw / 57.295776f;
		Turninghead.xRot = headPitch / 54.11268f;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftLeg.yRot = 0.0f;
		leftLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightLeg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
