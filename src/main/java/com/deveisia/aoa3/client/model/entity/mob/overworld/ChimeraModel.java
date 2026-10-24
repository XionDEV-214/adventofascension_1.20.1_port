package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class ChimeraModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart root;
	private final LegacyModelPart Leg1;
	private final LegacyModelPart Leg2;
	private final LegacyModelPart Leg3;
	private final LegacyModelPart Leg4;
	private final LegacyModelPart Tail3;
	private final LegacyModelPart Tail4;
	private final LegacyModelPart Tail5;
	private final LegacyModelPart Tail6;
	private final LegacyModelPart Tail7;
	private final LegacyModelPart Tail1;
	private final LegacyModelPart Tail2;
	private final LegacyModelPart Head1;
	private final LegacyModelPart Head2;
	private final LegacyModelPart Body2;

	public ChimeraModel() {
		texWidth = 64;
		texHeight = 32;

		root = new LegacyModelPart(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		Leg1 = new LegacyModelPart(this);
		Leg1.setPos(3.5F, -8.0F, 7.0F);
		root.addChild(Leg1);
		Leg1.texOffs(39, 14).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		Leg2 = new LegacyModelPart(this);
		Leg2.setPos(-1.5F, -8.0F, 7.0F);
		root.addChild(Leg2);
		Leg2.texOffs(39, 14).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false);

		Leg3 = new LegacyModelPart(this);
		Leg3.setPos(4.5F, -8.0F, -4.0F);
		root.addChild(Leg3);
		Leg3.texOffs(39, 14).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		Leg4 = new LegacyModelPart(this);
		Leg4.setPos(-2.5F, -8.0F, -4.0F);
		root.addChild(Leg4);
		Leg4.texOffs(39, 14).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false);

		Tail3 = new LegacyModelPart(this);
		Tail3.setPos(1.0F, -11.0F, 8.0F);
		root.addChild(Tail3);
		setRotation(Tail3, 2.0944F, 0.0F, 0.0F);
		Tail3.texOffs(33, 24).addBox(-2.0F, 1.0F, -2.0F, 4, 4, 4, 0.0F, true);

		Tail4 = new LegacyModelPart(this);
		Tail4.setPos(0.0F, 0.0F, 0.0F);
		Tail3.addChild(Tail4);
		setRotation(Tail4, 0.6109F, 0.0F, 0.0F);
		Tail4.texOffs(26, 14).addBox(-1.0F, 3.5F, -3.5F, 2, 6, 2, 0.0F, true);

		Tail5 = new LegacyModelPart(this);
		Tail5.setPos(0.0F, 0.0F, 0.0F);
		Tail4.addChild(Tail5);
		setRotation(Tail5, 0.4363F, 0.0F, 0.0F);
		Tail5.texOffs(33, 24).addBox(-2.0F, 7.0F, -8.0F, 4, 4, 4, 0.0F, true);

		Tail6 = new LegacyModelPart(this);
		Tail6.setPos(0.0F, 0.0F, 0.0F);
		Tail5.addChild(Tail6);
		setRotation(Tail6, 0.6109F, 0.0F, 0.0F);
		Tail6.texOffs(26, 14).addBox(-1.0F, 5.0F, -12.0F, 2, 6, 2, 0.0F, true);

		Tail7 = new LegacyModelPart(this);
		Tail7.setPos(0.0F, 0.0F, 0.0F);
		Tail6.addChild(Tail7);
		setRotation(Tail7, 0.6109F, 0.0F, 0.0F);
		Tail7.texOffs(33, 24).addBox(-2.0F, 1.0F, -17.0F, 4, 4, 4, 0.0F, true);

		Tail1 = new LegacyModelPart(this);
		Tail1.setPos(0.0F, 0.0F, 0.0F);
		Tail7.addChild(Tail1);
		setRotation(Tail1, 0.4363F, 0.0F, 0.0F);
		Tail1.texOffs(26, 14).addBox(-1.0F, -3.0F, -17.0F, 2, 6, 2, 0.0F, true);

		Tail2 = new LegacyModelPart(this);
		Tail2.setPos(0.0F, 0.0F, 0.0F);
		Tail1.addChild(Tail2);
		setRotation(Tail2, 0.3491F, 0.0F, 0.0F);
		Tail2.texOffs(33, 24).addBox(-2.0F, -4.0F, -18.0F, 4, 4, 4, 0.0F, true);

		Head1 = new LegacyModelPart(this);
		Head1.setPos(0.0F, -2.0F, -16.0F);
		Tail2.addChild(Head1);
		setRotation(Head1, 1.1345F, 0.0F, 0.0F);
		Head1.texOffs(0, 0).addBox(-3.0F, -2.659F, -4.4716F, 6, 6, 4, 0.0F, true);
		Head1.texOffs(55, 1).addBox(3.0F, -3.659F, -2.4716F, 3, 8, 1, 0.0F, false);
		Head1.texOffs(55, 1).addBox(-6.0F, -3.659F, -2.4716F, 3, 8, 1, 0.0F, true);
		Head1.texOffs(0, 10).addBox(-1.5F, 0.341F, -5.4716F, 3, 3, 1, 0.0F, true);

		Head2 = new LegacyModelPart(this);
		Head2.setPos(1.0F, -10.5F, -7.0F);
		root.addChild(Head2);
		Head2.texOffs(0, 0).addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F, true);
		Head2.texOffs(0, 10).addBox(-1.5F, 0.0F, -3.0F, 3, 3, 1, 0.0F, true);
		Head2.texOffs(55, 1).addBox(3.0F, -4.0F, 0.0F, 3, 8, 1, 0.0F, false);
		Head2.texOffs(55, 1).addBox(-6.0F, -4.0F, 0.0F, 3, 8, 1, 0.0F, true);

		Body2 = new LegacyModelPart(this);
		Body2.setPos(0.0F, -10.0F, 2.0F);
		root.addChild(Body2);
		setRotation(Body2, 1.5708F, 0.0F, 0.0F);
		Body2.texOffs(0, 16).addBox(-2.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F, true);
		Body2.texOffs(50, 17).addBox(4.0F, 0.0F, -1.0F, 1, 9, 6, 0.0F, true);
		Body2.texOffs(50, 17).addBox(-3.0F, 0.0F, -1.0F, 1, 9, 6, 0.0F, false);
		Body2.texOffs(21, 0).addBox(-3.0F, -8.0F, -3.0F, 8, 6, 7, 0.0F, true);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Head1.xRot = 1.13446401f + (headPitch * (float)Math.PI / 180f * 0.5f);
		Head2.xRot = headPitch * (float)Math.PI / 180f * 0.5f;
		Leg1.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg1.yRot = 0.0f;
		Leg3.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg3.yRot = 0.0f;
		Leg2.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Leg4.xRot = Mth.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}
