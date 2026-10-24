package com.deveisia.aoa3.client.model.entity.mob.dustopia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class DustStriderModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart head;
	private final LegacyModelPart RearEnd;
	private final LegacyModelPart Leg2;
	private final LegacyModelPart Leg1;
	private final LegacyModelPart Leg2p2;
	private final LegacyModelPart Leg1p2;
	private final LegacyModelPart Leg2p3;
	private final LegacyModelPart Leg1p3;
	private final LegacyModelPart Leg2p4;
	private final LegacyModelPart Leg1p4;
	private final LegacyModelPart Leg2p5;
	private final LegacyModelPart Leg1p5;
	private final LegacyModelPart RearEnd2;
	private final LegacyModelPart RearEnd3;
	private final LegacyModelPart RearEnd4;
	private final LegacyModelPart RearEnd5;
	private final LegacyModelPart RearEnd6;
	private final LegacyModelPart RearEnd7;
	private final LegacyModelPart RearEnd8;
	private final LegacyModelPart RearEnd9;
	private final LegacyModelPart RearEnd10;
	private final LegacyModelPart RearEnd11;
	private final LegacyModelPart RearEnd12;
	private final LegacyModelPart RearEnd13;
	private final LegacyModelPart head2;
	private final LegacyModelPart head3;
	private final LegacyModelPart head4;

	public DustStriderModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new LegacyModelPart(this, 58, 23)).addBox(2.0f, 2.0f, -11.0f, 2, 2, 5);
		head.setPos(0.0f, 15.0f, -3.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(RearEnd = new LegacyModelPart(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd.setPos(1.0f, 14.0f, 9.0f);
		RearEnd.setTexSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg2 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2.setPos(4.0f, 18.0f, 10.0f);
		Leg2.setTexSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1 = new LegacyModelPart(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1.setPos(-4.0f, 18.0f, 10.0f);
		Leg1.setTexSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p2 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p2.setPos(4.0f, 18.0f, 6.0f);
		Leg2p2.setTexSize(128, 32);
		Leg2p2.mirror = true;
		setRotation(Leg2p2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p2 = new LegacyModelPart(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p2.setPos(-4.0f, 18.0f, 6.0f);
		Leg1p2.setTexSize(128, 32);
		Leg1p2.mirror = true;
		setRotation(Leg1p2, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p3 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p3.setPos(4.0f, 18.0f, 2.0f);
		Leg2p3.setTexSize(128, 32);
		Leg2p3.mirror = true;
		setRotation(Leg2p3, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p3 = new LegacyModelPart(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p3.setPos(-4.0f, 18.0f, 2.0f);
		Leg1p3.setTexSize(128, 32);
		Leg1p3.mirror = true;
		setRotation(Leg1p3, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p4 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p4.setPos(4.0f, 18.0f, -6.0f);
		Leg2p4.setTexSize(128, 32);
		Leg2p4.mirror = true;
		setRotation(Leg2p4, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p4 = new LegacyModelPart(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p4.setPos(-4.0f, 18.0f, -6.0f);
		Leg1p4.setTexSize(128, 32);
		Leg1p4.mirror = true;
		setRotation(Leg1p4, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p5 = new LegacyModelPart(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p5.setPos(4.0f, 18.0f, -2.0f);
		Leg2p5.setTexSize(128, 32);
		Leg2p5.mirror = true;
		setRotation(Leg2p5, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p5 = new LegacyModelPart(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p5.setPos(-4.0f, 18.0f, -2.0f);
		Leg1p5.setTexSize(128, 32);
		Leg1p5.mirror = true;
		setRotation(Leg1p5, 0.0f, 0.5759587f, -0.1919862f);
		(RearEnd2 = new LegacyModelPart(this, 0, 2)).addBox(-5.0f, -6.0f, -6.0f, 10, 9, 18);
		RearEnd2.setPos(0.0f, 14.0f, 0.0f);
		RearEnd2.setTexSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new LegacyModelPart(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd3.setPos(1.0f, 14.0f, 9.0f);
		RearEnd3.setTexSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
		(RearEnd4 = new LegacyModelPart(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd4.setPos(1.0f, 14.0f, 9.0f);
		RearEnd4.setTexSize(128, 32);
		RearEnd4.mirror = true;
		setRotation(RearEnd4, 0.0f, 0.0f, 0.0f);
		(RearEnd5 = new LegacyModelPart(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd5.setPos(1.0f, 14.0f, 9.0f);
		RearEnd5.setTexSize(128, 32);
		RearEnd5.mirror = true;
		setRotation(RearEnd5, 0.0f, 0.0f, 0.0f);
		(RearEnd6 = new LegacyModelPart(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd6.setPos(1.0f, 14.0f, 9.0f);
		RearEnd6.setTexSize(128, 32);
		RearEnd6.mirror = true;
		setRotation(RearEnd6, 0.0f, 0.0f, 0.0f);
		(RearEnd7 = new LegacyModelPart(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd7.setPos(1.0f, 14.0f, 9.0f);
		RearEnd7.setTexSize(128, 32);
		RearEnd7.mirror = true;
		setRotation(RearEnd7, 0.0f, 0.0f, 0.0f);
		(RearEnd8 = new LegacyModelPart(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd8.setPos(1.0f, 14.0f, 0.0f);
		RearEnd8.setTexSize(128, 32);
		RearEnd8.mirror = true;
		setRotation(RearEnd8, 0.0f, 0.0f, 0.0f);
		(RearEnd9 = new LegacyModelPart(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd9.setPos(1.0f, 14.0f, 0.0f);
		RearEnd9.setTexSize(128, 32);
		RearEnd9.mirror = true;
		setRotation(RearEnd9, 0.0f, 0.0f, 0.0f);
		(RearEnd10 = new LegacyModelPart(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd10.setPos(1.0f, 14.0f, 0.0f);
		RearEnd10.setTexSize(128, 32);
		RearEnd10.mirror = true;
		setRotation(RearEnd10, 0.0f, 0.0f, 0.0f);
		(RearEnd11 = new LegacyModelPart(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd11.setPos(1.0f, 14.0f, 0.0f);
		RearEnd11.setTexSize(128, 32);
		RearEnd11.mirror = true;
		setRotation(RearEnd11, 0.0f, 0.0f, 0.0f);
		(RearEnd12 = new LegacyModelPart(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd12.setPos(1.0f, 14.0f, 0.0f);
		RearEnd12.setTexSize(128, 32);
		RearEnd12.mirror = true;
		setRotation(RearEnd12, 0.0f, 0.0f, 0.0f);
		(RearEnd13 = new LegacyModelPart(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd13.setPos(1.0f, 14.0f, 0.0f);
		RearEnd13.setTexSize(128, 32);
		RearEnd13.mirror = true;
		setRotation(RearEnd13, 0.0f, 0.0f, 0.0f);
		(head2 = new LegacyModelPart(this, 96, 11)).addBox(-5.0f, -9.0f, -7.0f, 10, 10, 0);
		head2.setPos(0.0f, 15.0f, -3.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new LegacyModelPart(this, 58, 23)).addBox(-4.0f, 2.0f, -11.0f, 2, 2, 5);
		head3.setPos(0.0f, 15.0f, -3.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new LegacyModelPart(this, 42, 6)).addBox(-4.0f, -6.0f, -8.0f, 8, 8, 5);
		head4.setPos(0.0f, 15.0f, -3.0f);
		head4.setTexSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		float var8 = ((float)Math.PI / 4F);
		Leg1.zRot = -var8;
		Leg2.zRot = var8;

		Leg1p2.zRot = -var8;
		Leg2p2.zRot = var8;

		Leg1p3.zRot = -var8;
		Leg2p3.zRot = var8;

		Leg1p4.zRot = -var8;
		Leg2p4.zRot = var8;

		Leg1p5.zRot = -var8;
		Leg2p5.zRot = var8;

		float var9 = -0.0F;
		float var10 = 0.3926991F;
		Leg1.yRot = var10 * 2.0F + var9;
		Leg2.yRot = -var10 * 2.0F - var9;

		Leg1p2.yRot = var10 * 2.0F + var9;
		Leg2p2.yRot = -var10 * 2.0F - var9;

		Leg1p3.yRot = var10 * 2.0F + var9;
		Leg2p3.yRot = -var10 * 2.0F - var9;

		Leg1p4.yRot = var10 * 2.0F + var9;
		Leg2p4.yRot = -var10 * 2.0F - var9;

		Leg1p5.yRot = var10 * 2.0F + var9;
		Leg2p5.yRot = -var10 * 2.0F - var9;

		float var11 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float var15 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;

		Leg1.yRot += var11;
		Leg2.yRot += -var11;

		Leg1p2.zRot += var15;
		Leg2p2.zRot += -var15;

		Leg1p3.yRot += var11;
		Leg2p3.yRot += -var11;

		Leg1p4.zRot += var15;
		Leg2p4.zRot += -var15;

		Leg1p5.yRot += var11;
		Leg2p5.yRot += -var11;

		Leg1.zRot += var15;
		Leg2.zRot += -var15;

		Leg1p2.yRot += var11;
		Leg2p2.yRot += -var11;

		Leg1p3.zRot += var15;
		Leg2p3.zRot += -var15;

		Leg1p4.yRot += var11;
		Leg2p4.yRot += -var11;

		Leg1p5.zRot += var15;
		Leg2p5.zRot += -var15;
	}
}
