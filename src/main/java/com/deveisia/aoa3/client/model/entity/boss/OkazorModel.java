package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class OkazorModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart root;
	private final LegacyModelPart rightarm2;
	private final LegacyModelPart leftarm2;
	private final LegacyModelPart head;
	private final LegacyModelPart leftleg;
	private final LegacyModelPart rightleg;

	public OkazorModel() {
		texWidth = 128;
		texHeight = 64;

		root = new LegacyModelPart(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		LegacyModelPart body4 = new LegacyModelPart(this);
		body4.setPos(0.0F, -29.0F, 0.0F);
		root.addChild(body4);
		body4.texOffs(57, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, true);
		body4.texOffs(50, 50).addBox(-3.0F, 9.0F, -3.5F, 6, 3, 2, 0.0F, true);
		body4.texOffs(50, 39).addBox(-5.5F, 12.0F, -3.5F, 11, 2, 6, 0.0F, true);
		body4.texOffs(27, 47).addBox(-5.0F, -1.0F, -3.0F, 3, 4, 6, 0.0F, true);
		body4.texOffs(16, 16).addBox(-5.0F, 14.0F, -3.0F, 10, 12, 5, 0.0F, true);
		body4.texOffs(2, 47).addBox(2.0F, -1.0F, -3.0F, 3, 4, 6, 0.0F, true);
		body4.texOffs(23, 36).addBox(-9.0F, -1.0F, -3.0F, 4, 2, 6, 0.0F, true);
		body4.texOffs(1, 36).addBox(5.0F, -1.0F, -3.0F, 4, 2, 6, 0.0F, true);

		rightarm2 = new LegacyModelPart(this);
		rightarm2.setPos(7.5F, 1.25F, -0.5F);
		body4.addChild(rightarm2);
		rightarm2.texOffs(83, 16).addBox(-1.5F, -0.25F, -1.5F, 3, 16, 3, 0.0F, true);
		rightarm2.texOffs(104, 16).addBox(1.5F, 8.75F, -2.5F, 1, 6, 5, 0.0F, true);

		leftarm2 = new LegacyModelPart(this);
		leftarm2.setPos(-7.5F, 1.0F, -0.5F);
		body4.addChild(leftarm2);
		leftarm2.texOffs(83, 16).addBox(-1.5F, 0.0F, -1.5F, 3, 16, 3, 0.0F, true);
		leftarm2.texOffs(117, 16).addBox(-2.5F, 9.0F, -2.5F, 1, 6, 5, 0.0F, true);

		LegacyModelPart mace = new LegacyModelPart(this);
		mace.setPos(15.0F, 14.25F, 0.0F);
		leftarm2.addChild(mace);
		mace.texOffs(88, 39).addBox(-16.0F, -1.25F, -7.5F, 2, 2, 6, 0.0F, true);
		mace.texOffs(83, 49).addBox(-18.0F, -3.25F, -10.5F, 6, 6, 3, 0.0F, true);
		mace.texOffs(83, 59).addBox(-18.0F, 2.75F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-12.0F, 0.75F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-14.0F, 2.75F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-14.0F, -5.25F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-18.0F, -5.25F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-12.0F, -3.25F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-20.0F, 0.75F, -10.5F, 2, 2, 2, 0.0F, true);
		mace.texOffs(83, 59).addBox(-20.0F, -3.25F, -10.5F, 2, 2, 2, 0.0F, true);

		head = new LegacyModelPart(this);
		head.setPos(0.0F, 0.0F, -1.0F);
		body4.addChild(head);
		head.texOffs(0, 0).addBox(-3.0F, -8.0F, -4.0F, 6, 8, 6, 0.0F, true);
		head.texOffs(65, 0).addBox(-4.0F, -9.0F, -3.0F, 1, 7, 6, 0.0F, true);
		head.texOffs(25, 0).addBox(-3.0F, -9.0F, -3.0F, 6, 1, 5, 0.0F, true);
		head.texOffs(80, 0).addBox(-3.0F, -9.0F, 2.0F, 6, 7, 1, 0.0F, true);
		head.texOffs(49, 0).addBox(3.0F, -9.0F, -3.0F, 1, 7, 6, 0.0F, true);

		LegacyModelPart head3 = new LegacyModelPart(this);
		head3.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(head3, 0.0F, 0.0F, 0.7854F);
		head.addChild(head3);
		head3.texOffs(111, 3).addBox(-2.0F, -11.0F, -1.0F, 2, 7, 3, 0.0F, false);

		LegacyModelPart head7 = new LegacyModelPart(this);
		head7.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(head7, 0.0F, 0.0F, -0.7854F);
		head.addChild(head7);
		head7.texOffs(97, 3).addBox(0.0F, -11.0F, -1.0F, 2, 7, 3, 0.0F, true);

		leftleg = new LegacyModelPart(this);
		leftleg.setPos(-2.0F, 23.0F, 0.0F);
		body4.addChild(leftleg);
		leftleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F, true);

		rightleg = new LegacyModelPart(this);
		rightleg.setPos(2.0F, 23.0F, 0.0F);
		body4.addChild(rightleg);
		rightleg.texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F, true);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotationAngle(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = (float)(netHeadYaw / 180f / Math.PI);
		rightarm2.xRot = Mth.cos((float)(limbSwing * 0.6662f + Math.PI)) * 2.0f * limbSwingAmount * 0.5f;
		rightarm2.zRot = 0;
		leftarm2.xRot = Mth.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftarm2.zRot = 0;
		leftleg.xRot = Mth.cos((float)(limbSwing * 0.6662f + Math.PI)) * 1.4f * limbSwingAmount;
		rightleg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}
