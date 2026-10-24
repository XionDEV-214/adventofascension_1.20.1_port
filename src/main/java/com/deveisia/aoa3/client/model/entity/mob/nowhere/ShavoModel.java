package com.deveisia.aoa3.client.model.entity.mob.nowhere;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ShavoModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart body;
	private final LegacyModelPart leg1;
	private final LegacyModelPart leg2;
	private final LegacyModelPart leg3;
	private final LegacyModelPart leg4;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart leg2p2;
	private final LegacyModelPart leg1p2;
	private final LegacyModelPart leg4p2;
	private final LegacyModelPart leg3p2;
	private final LegacyModelPart head4;
	private final LegacyModelPart head5;
	private final LegacyModelPart head6;
	private final LegacyModelPart head7;
	private final LegacyModelPart head8;

	public ShavoModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(head = new LegacyModelPart(this, 52, 0)).addBox(4.0f, -7.0f, -2.0f, 2, 6, 2);
		head.setPos(0.0f, 2.0f, -8.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new LegacyModelPart(this, 20, 11)).addBox(-6.0f, -10.0f, 0.0f, 12, 17, 4);
		body.setPos(0.0f, 5.0f, 2.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new LegacyModelPart(this, 53, 16)).addBox(-2.0f, 0.0f, -1.0f, 2, 7, 2);
		leg1.setPos(-3.0f, 5.0f, 7.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new LegacyModelPart(this, 53, 16)).addBox(0.0f, 0.0f, -1.0f, 2, 7, 2);
		leg2.setPos(3.0f, 5.0f, 7.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new LegacyModelPart(this, 53, 16)).addBox(-2.0f, 0.0f, -2.0f, 2, 7, 2);
		leg3.setPos(-3.0f, 5.0f, -5.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new LegacyModelPart(this, 53, 16)).addBox(0.0f, 0.0f, -2.0f, 2, 7, 2);
		leg4.setPos(3.0f, 5.0f, -5.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 43, 1)).addBox(-1.0f, -7.0f, -2.0f, 2, 2, 2);
		head2.setPos(0.0f, 2.0f, -8.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 52, 0)).addBox(-6.0f, -7.0f, -2.0f, 2, 6, 2);
		head3.setPos(0.0f, 2.0f, -8.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(leg2p2 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 7.0f, -2.0f, 4, 12, 4);
		leg2p2.setPos(3.0f, 5.0f, 7.0f);
		leg2p2.setTexSize(64, 32);
		leg2p2.mirror = true;
		setRotation(leg2p2, 0.0f, 0.0f, 0.0f);
		(leg1p2 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 7.0f, -2.0f, 4, 12, 4);
		leg1p2.setPos(-3.0f, 5.0f, 7.0f);
		leg1p2.setTexSize(64, 32);
		leg1p2.mirror = true;
		setRotation(leg1p2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new LegacyModelPart(this, 0, 16)).addBox(-1.0f, 7.0f, -3.0f, 4, 12, 4);
		leg4p2.setPos(3.0f, 5.0f, -5.0f);
		leg4p2.setTexSize(64, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new LegacyModelPart(this, 0, 16)).addBox(-3.0f, 7.0f, -3.0f, 4, 12, 4);
		leg3p2.setPos(-3.0f, 5.0f, -5.0f);
		leg3p2.setTexSize(64, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -4.0f, -2.0f, 8, 11, 2);
		head4.setPos(0.0f, 2.0f, -8.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new LegacyModelPart(this, 21, 0)).addBox(-2.0f, -5.0f, -2.0f, 4, 1, 2);
		head5.setPos(0.0f, 2.0f, -8.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new LegacyModelPart(this, 44, 6)).addBox(2.0f, -10.0f, -2.0f, 1, 2, 2);
		head6.setPos(0.0f, 2.0f, -8.0f);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new LegacyModelPart(this, 21, 4)).addBox(-3.0f, -8.0f, -2.0f, 6, 1, 2);
		head7.setPos(0.0f, 2.0f, -8.0f);
		head7.setTexSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new LegacyModelPart(this, 44, 6)).addBox(-3.0f, -10.0f, -2.0f, 1, 2, 2);
		head8.setPos(0.0f, 2.0f, -8.0f);
		head8.setTexSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg1p2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1p2.yRot = 0.0f;
		leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg3p2.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p2.yRot = 0.0f;
		leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg2p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
