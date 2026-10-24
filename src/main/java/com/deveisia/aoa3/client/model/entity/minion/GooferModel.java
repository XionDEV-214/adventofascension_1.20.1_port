package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class GooferModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart leg1p2;
	private final LegacyModelPart leg3p2;
	private final LegacyModelPart leg2p2;
	private final LegacyModelPart leg4p2;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;

	public GooferModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 18, 50)).addBox(4.0f, -2.0f, -5.0f, 5, 4, 4);
		head.setPos(0.0f, 7.0f, -8.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 19, 6)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
		body.setPos(0.0f, 5.0f, 2.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 0, 37)).addBox(-4.0f, 7.0f, -3.0f, 6, 5, 6);
		leg1.setPos(-4.0f, 12.0f, 7.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 0, 37)).addBox(-2.0f, 7.0f, -3.0f, 6, 5, 6);
		leg2.setPos(4.0f, 12.0f, 7.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 0, 37)).addBox(-4.0f, 7.0f, -4.0f, 6, 5, 6);
		leg3.setPos(-4.0f, 12.0f, -5.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 0, 37)).addBox(-2.0f, 7.0f, -4.0f, 6, 5, 6);
		leg4.setPos(4.0f, 12.0f, -5.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg1p2 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 7, 4);
		leg1p2.setPos(-4.0f, 12.0f, 7.0f);
		leg1p2.setTexSize(64, 64);
		leg1p2.mirror = true;
		setRotation(leg1p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 7, 4);
		leg3p2.setPos(-4.0f, 12.0f, -5.0f);
		leg3p2.setTexSize(64, 64);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg2p2 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 7, 4);
		leg2p2.setPos(4.0f, 12.0f, 7.0f);
		leg2p2.setTexSize(64, 64);
		leg2p2.mirror = true;
		setRotation(leg2p2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 7, 4);
		leg4p2.setPos(4.0f, 12.0f, -5.0f);
		leg4p2.setTexSize(64, 64);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head2.setPos(0.0f, 7.0f, -8.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 50)).addBox(-2.0f, -9.0f, -5.0f, 4, 5, 4);
		head3.setPos(0.0f, 7.0f, -8.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 18, 50)).addBox(-9.0f, -2.0f, -5.0f, 5, 4, 4);
		head4.setPos(0.0f, 7.0f, -8.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg1p2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1p2.yRot = 0.0f;
		leg3p2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p2.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg2p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
