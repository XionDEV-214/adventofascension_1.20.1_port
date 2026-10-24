package com.deveisia.aoa3.client.model.entity.mob.creeponia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.mob.creeponia.AoACreeponiaCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class MagicalCreeperModel extends LegacyEntityModel<AoACreeponiaCreeper> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;

	public MagicalCreeperModel(float delta) {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 40, 34)).addBox(-2.0f, -20.0f, -2.0f, 4, 4, 4, delta);
		head.setPos(0.0f, 6.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, delta);
		body.setPos(0.0f, 6.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg3.setPos(-2.0f, 18.0f, -4.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg4.setPos(2.0f, 18.0f, -4.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg1.setPos(-4.0f, 18.0f, 4.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg2.setPos(2.0f, 18.0f, 4.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, delta);
		head2.setPos(0.0f, 6.0f, 0.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 32)).addBox(-6.0f, -10.0f, -6.0f, 12, 2, 12, delta);
		head3.setPos(0.0f, 6.0f, 0.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 47)).addBox(-5.0f, -12.0f, -5.0f, 10, 2, 10, delta);
		head4.setPos(0.0f, 6.0f, 0.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 40, 47)).addBox(-3.0f, -16.0f, -3.0f, 6, 4, 6, delta);
		head5.setPos(0.0f, 6.0f, 0.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(AoACreeponiaCreeper entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 57.295776f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 57.295776f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 57.295776f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 57.295776f;
		head5.yRot = netHeadYaw / 57.295776f;
		head5.xRot = headPitch / 57.295776f;
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
