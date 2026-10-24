package com.deveisia.aoa3.client.model.entity.mob.mysterium;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class MushroomSpiderModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart Body;
	private final LegacyModelPart Head;
	private final LegacyModelPart Leg1;
	private final LegacyModelPart Leg2;
	private final LegacyModelPart Leg3;
	private final LegacyModelPart Leg4;
	private final LegacyModelPart Leg5;
	private final LegacyModelPart Leg6;
	private final LegacyModelPart Leg7;
	private final LegacyModelPart Leg8;
	private final LegacyModelPart RearEnd;

	public MushroomSpiderModel() {
		texWidth = 64;
		texHeight = 64;

		Body = new LegacyModelPart(this);
		Body.setPos(0.0F, 20.0F, 0.0F);
		Body.texOffs(42, 20).addBox(-2.0F, -2.0F, 3.0F, 4, 4, 7, 0.0F, true);
		Body.texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, true);

		Head = new LegacyModelPart(this);
		Head.setPos(0.0F, 0.0F, -3.0F);
		Body.addChild(Head);
		Head.texOffs(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F, true);
		Head.texOffs(18, 14).addBox(1.0F, -8.0F, -8.0F, 3, 2, 3, 0.0F, true);
		Head.texOffs(26, 6).addBox(2.0F, -7.0F, -7.0F, 1, 4, 1, 0.0F, true);
		Head.texOffs(26, 6).addBox(-3.0F, -7.0F, -7.0F, 1, 4, 1, 0.0F, true);
		Head.texOffs(18, 14).addBox(-4.0F, -8.0F, -8.0F, 3, 2, 3, 0.0F, true);

		Leg1 = new LegacyModelPart(this);
		Leg1.setPos(2.0F, 0.0F, 0.25F);
		Body.addChild(Leg1);
		setRotation(Leg1, 0.0F, -0.576F, -0.2443F);
		Leg1.texOffs(18, 0).addBox(0.5655F, -1.5413F, -0.5794F, 16, 2, 2, 0.0F, true);

		Leg2 = new LegacyModelPart(this);
		Leg2.setPos(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg2);
		setRotation(Leg2, 0.0F, 0.576F, 0.2443F);
		Leg2.texOffs(18, 0).addBox(-16.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, false);

		Leg3 = new LegacyModelPart(this);
		Leg3.setPos(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg3);
		setRotation(Leg3, 0.0F, -0.2793F, -0.2443F);
		Leg3.texOffs(18, 0).addBox(0.1327F, -1.5226F, -0.5712F, 16, 2, 2, 0.0F, true);

		Leg4 = new LegacyModelPart(this);
		Leg4.setPos(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg4);
		setRotation(Leg4, 0.0F, 0.2793F, 0.2443F);
		Leg4.texOffs(18, 0).addBox(-16.1327F, -1.5226F, -0.5712F, 16, 2, 2, 0.0F, false);

		Leg5 = new LegacyModelPart(this);
		Leg5.setPos(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg5);
		setRotation(Leg5, 0.0F, 0.2793F, -0.2443F);
		Leg5.texOffs(18, 0).addBox(-0.143F, -1.5276F, -0.4675F, 16, 2, 2, 0.0F, true);

		Leg6 = new LegacyModelPart(this);
		Leg6.setPos(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg6);
		setRotation(Leg6, 0.0F, -0.2793F, 0.2443F);
		Leg6.texOffs(18, 0).addBox(-15.857F, -1.5276F, -0.4675F, 16, 2, 2, 0.0F, false);

		Leg7 = new LegacyModelPart(this);
		Leg7.setPos(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg7);
		setRotation(Leg7, 0.0F, 0.576F, -0.2443F);
		Leg7.texOffs(18, 0).addBox(0.157F, -1.5513F, -0.7916F, 16, 2, 2, 0.0F, true);

		Leg8 = new LegacyModelPart(this);
		Leg8.setPos(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg8);
		setRotation(Leg8, 0.0F, -0.576F, 0.2443F);
		Leg8.texOffs(18, 0).addBox(-16.157F, -1.5513F, -0.7916F, 16, 2, 2, 0.0F, false);

		RearEnd = new LegacyModelPart(this);
		RearEnd.setPos(0.0F, 0.0F, 9.0F);
		Body.addChild(RearEnd);
		RearEnd.texOffs(0, 23).addBox(-6.0F, -6.0F, 1.0F, 12, 10, 10, 0.0F, true);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rot1 = -(Mth.cos(limbSwing * 1.324f) * 0.4f) * limbSwingAmount;
		float rot2 = -(Mth.cos(limbSwing * 1.324f + (float)Math.PI) * 0.4f) * limbSwingAmount;
		float rot3 = -(Mth.cos(limbSwing * 1.324f + (float)Math.PI / 2f) * 0.4f) * limbSwingAmount;
		float rot4 = -(Mth.cos(limbSwing * 1.324f + (float)Math.PI * 1.5f) * 0.4f) * limbSwingAmount;
		float lift1 = Math.abs(Mth.sin(limbSwing * 0.6662f) * 0.4f) * limbSwingAmount;
		float lift2 = Math.abs(Mth.sin(limbSwing * 0.6662f + (float)Math.PI) * 0.4f) * limbSwingAmount;
		float lift3 = Math.abs(Mth.sin(limbSwing * 0.6662f + (float)Math.PI / 2f) * 0.4f) * limbSwingAmount;
		float lift4 = Math.abs(Mth.sin(limbSwing * 0.6662f + (float)Math.PI * 1.5f) * 0.4f) * limbSwingAmount;
		float quarterPi = -(float)Math.PI / 4f;
		float eighthPi = -(float)Math.PI / 8f;

		Leg1.yRot = quarterPi + rot1;
		Leg2.yRot = -quarterPi + rot1;
		Leg3.yRot = eighthPi + rot2;
		Leg4.yRot = -eighthPi + rot2;
		Leg5.yRot = -eighthPi + rot3;
		Leg6.yRot = eighthPi + rot3;
		Leg7.yRot = -quarterPi + rot4;
		Leg8.yRot = quarterPi + rot4;

		Leg1.zRot = -eighthPi + lift1;
		Leg2.zRot = eighthPi - lift1;
		Leg3.zRot = -eighthPi * 0.74f + lift2;
		Leg4.zRot = eighthPi * 0.74f - lift2;
		Leg5.zRot = -eighthPi * 0.74f + lift3;
		Leg6.zRot = eighthPi * 0.74f - lift3;
		Leg7.zRot = -eighthPi + lift4;
		Leg8.zRot = eighthPi - lift4;
	}
}
