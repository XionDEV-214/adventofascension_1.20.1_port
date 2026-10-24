package com.deveisia.aoa3.client.model.entity.npc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class StoreKeeperModel extends LegacyEntityModel<Mob> {
	private LegacyModelPart head;
	private LegacyModelPart body;
	private LegacyModelPart rightArm;
	private LegacyModelPart leftArm;
	private LegacyModelPart body2;
	private LegacyModelPart head2;
	private LegacyModelPart head3;
	private LegacyModelPart head4;
	private LegacyModelPart head5;
	private LegacyModelPart head6;

	public StoreKeeperModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setPos(-4.0f, 3.0f, 5.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 28, 17)).addBox(-4.0f, 10.0f, -2.0f, 8, 5, 10);
		body.setPos(0.0f, 9.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new LegacyModelPart(this, 40, 1)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 11.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new LegacyModelPart(this, 40, 1)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 11.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new LegacyModelPart(this, 0, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 10, 6);
		body2.setPos(0.0f, 9.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head2.setPos(0.0f, 9.0f, -2.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head3.setPos(5.0f, 8.0f, 1.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head4.setPos(-5.0f, 8.0f, 2.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head5.setPos(1.0f, 4.0f, 2.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head6.setPos(6.0f, 3.0f, 5.0f);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
	}
}
