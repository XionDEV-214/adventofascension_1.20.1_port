package com.deveisia.aoa3.client.model.entity.mob.runandor;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class BouncerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body;
	private final LegacyModelPart legp1;
	private final LegacyModelPart legp2;
	private final LegacyModelPart legp3;
	private final LegacyModelPart legp4;
	private final LegacyModelPart head;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;

	public BouncerModel() {
		texWidth = 64;
		texHeight = 32;
		(body = new LegacyModelPart(this, 16, 16)).addBox(-4.0f, 0.0f, 0.0f, 8, 9, 4);
		body.setPos(0.0f, 4.0f, -1.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(legp1 = new LegacyModelPart(this, 47, 24)).addBox(1.0f, 12.0f, -6.0f, 2, 2, 6);
		legp1.setPos(0.0f, 10.0f, 0.0f);
		legp1.setTexSize(64, 32);
		legp1.mirror = true;
		setRotation(legp1, 0.0f, 0.0f, 0.0f);
		(legp2 = new LegacyModelPart(this, 33, 1)).addBox(-1.0f, 1.0f, -1.0f, 2, 9, 4);
		legp2.setPos(0.0f, 10.0f, 0.0f);
		legp2.setTexSize(64, 32);
		legp2.mirror = true;
		setRotation(legp2, -0.3490659f, 0.0f, 0.0f);
		(legp3 = new LegacyModelPart(this, 47, 13)).addBox(-2.0f, 7.0f, -6.0f, 4, 6, 4);
		legp3.setPos(0.0f, 10.0f, 0.0f);
		legp3.setTexSize(64, 32);
		legp3.mirror = true;
		setRotation(legp3, 0.1745329f, 0.0f, 0.0f);
		(legp4 = new LegacyModelPart(this, 47, 24)).addBox(-3.0f, 12.0f, -6.0f, 2, 2, 6);
		legp4.setPos(0.0f, 10.0f, 0.0f);
		legp4.setTexSize(64, 32);
		legp4.mirror = true;
		setRotation(legp4, 0.0f, 0.0f, 0.0f);
		(head = new LegacyModelPart(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 9.0f, -5.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 0, 16)).addBox(-4.0f, -8.0f, 4.0f, 0, 8, 8);
		head2.setPos(8.0f, 17.0f, -5.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, -1.570796f, 0.0f);
		(head3 = new LegacyModelPart(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head3.setPos(0.0f, 17.0f, -5.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head4.setPos(8.0f, 17.0f, -5.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		legp1.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		legp2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.349f;
		legp3.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount + 0.174f;
		legp4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
