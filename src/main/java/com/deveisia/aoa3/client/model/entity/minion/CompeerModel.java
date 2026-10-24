package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class CompeerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart Leg_Front_1;
	private final LegacyModelPart Leg_back;
	private final LegacyModelPart Leg_back_2;
	private final LegacyModelPart Leg_Front_2;
	private final LegacyModelPart head;
	private final LegacyModelPart Back_Bone;

	public CompeerModel() {
		texWidth = 128;
		texHeight = 64;
		(body = new LegacyModelPart(this, 0, 0)).addBox(-5.0f, -5.0f, -8.0f, 10, 10, 16);
		body.setPos(0.0f, 10.0f, -2.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Leg_Front_1 = new LegacyModelPart(this, 73, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_Front_1.setPos(3.0f, 15.0f, -6.0f);
		Leg_Front_1.setTexSize(64, 32);
		Leg_Front_1.mirror = true;
		setRotation(Leg_Front_1, 0.0f, 0.0f, 0.0f);
		(Leg_back = new LegacyModelPart(this, 73, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_back.setPos(3.0f, 15.0f, 3.0f);
		Leg_back.setTexSize(64, 32);
		Leg_back.mirror = true;
		setRotation(Leg_back, 0.0f, 0.0f, 0.0f);
		(Leg_back_2 = new LegacyModelPart(this, 73, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_back_2.setPos(-3.0f, 15.0f, 3.0f);
		Leg_back_2.setTexSize(64, 32);
		Leg_back_2.mirror = true;
		setRotation(Leg_back_2, 0.0f, 0.0f, 0.0f);
		(Leg_Front_2 = new LegacyModelPart(this, 73, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_Front_2.setPos(-3.0f, 15.0f, -6.0f);
		Leg_Front_2.setTexSize(64, 32);
		Leg_Front_2.mirror = true;
		setRotation(Leg_Front_2, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 28, 28)).addBox(-4.0f, -4.0f, -9.0f, 8, 8, 9);
		head.setPos(0.0f, 7.0f, -8.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Back_Bone = new LegacyModelPart(this, 0, 27)).addBox(-1.5f, -3.0f, -1.5f, 3, 3, 10);
		Back_Bone.setPos(0.0f, 5.0f, -4.0f);
		Back_Bone.setTexSize(64, 32);
		Back_Bone.mirror = true;
		setRotation(Back_Bone, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {

		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_Front_1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_back.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_back_2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_Front_2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Back_Bone.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		Leg_Front_1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg_Front_1.yRot = 0.0f;
		Leg_back.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg_back.yRot = 0.0f;
		Leg_back_2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Leg_Front_2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
