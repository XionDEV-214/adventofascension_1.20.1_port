package com.deveisia.aoa3.client.model.entity.minion;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Mob;

public class ConstructOfServilityModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart root;
	private final LegacyModelPart r2;

	public ConstructOfServilityModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;

		root = new LegacyModelPart(this);
		root.setPos(0.0F, 24.0F, 0.0F);
		setRotation(root, 0.1745F, 0.0F, 0.0F);

		LegacyModelPart head = new LegacyModelPart(this);
		head.setPos(0.0F, -24.0F, 2.0F);
		setRotation(head, -0.1745F, 0.0F, 0.0F);
		root.addChild(head);
		head.texOffs(0, 0).addBox(-3.0F, -4.6527F, -5.9696F, 6, 6, 6, 0.0F, true);

		LegacyModelPart bone = new LegacyModelPart(this);
		bone.setPos(0.0F, -2.6527F, -1.9696F);
		setRotation(bone, -1.1345F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.texOffs(24, 2).addBox(-2.0F, -5.22F, -3.5031F, 4, 7, 3, 0.0F, true);

		LegacyModelPart body = new LegacyModelPart(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		root.addChild(body);
		body.texOffs(16, 14).addBox(-4.0F, -22.0F, -4.0F, 8, 10, 8, 0.0F, true);
		body.texOffs(6, 32).addBox(-5.0F, -12.0F, -5.0F, 10, 4, 10, 0.0F, true);

		r2 = new LegacyModelPart(this);
		r2.setPos(0.0F, -5.0F, 0.0F);
		body.addChild(r2);
		r2.texOffs(0, 31).addBox(4.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false);
		r2.texOffs(0, 31).addBox(-8.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true);
		r2.texOffs(0, 31).addBox(-2.0F, -2.0F, 4.0F, 4, 4, 4, 0.0F, true);
		r2.texOffs(0, 31).addBox(-2.0F, -2.0F, -8.0F, 4, 4, 4, 0.0F, true);

		LegacyModelPart leftarm = new LegacyModelPart(this);
		leftarm.setPos(-5.0F, -22.0F, 0.0F);
		setRotation(leftarm, 0.0873F, 0.0F, 0.3491F);
		body.addChild(leftarm);
		leftarm.texOffs(0, 16).addBox(-3.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, false);

		LegacyModelPart rightarm = new LegacyModelPart(this);
		rightarm.setPos(5.0F, -22.0F, 0.0F);
		setRotation(rightarm, 0.0873F, 0.0F, -0.3491F);
		body.addChild(rightarm);
		rightarm.texOffs(0, 16).addBox(-1.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, true);
	}

	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r2.yRot = ageInTicks * 0.335f;
	}
}
