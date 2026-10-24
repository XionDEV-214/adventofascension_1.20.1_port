package com.deveisia.aoa3.client.model.entity.misc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;

public class LottoTotemModel extends LegacyEntityModel<Entity> {
	private final LegacyModelPart base;
	private final LegacyModelPart coin;

	public LottoTotemModel() {
		texWidth = 32;
		texHeight = 32;

		base = new LegacyModelPart(this);
		base.setPos(0.0F, 24.0F, 0.0F);
		base.texOffs(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false);
		base.texOffs(0, 20).addBox(-3.0F, -9.0F, -3.0F, 6, 1, 6, 0.0F, false);

		LegacyModelPart cube3 = new LegacyModelPart(this);
		cube3.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube3, 0.0F, -0.7854F, 0.0F);
		base.addChild(cube3);
		cube3.texOffs(0, 0).addBox(2.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(0, 20).addBox(5.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false);
		cube3.texOffs(0, 20).addBox(-7.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false);
		cube3.texOffs(0, 20).addBox(5.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false);
		cube3.texOffs(0, 20).addBox(-7.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false);
		cube3.texOffs(22, 25).addBox(1.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false);
		cube3.texOffs(22, 25).addBox(1.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false);
		cube3.texOffs(22, 25).addBox(-2.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false);
		cube3.texOffs(22, 25).addBox(-2.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false);
		cube3.texOffs(20, 0).addBox(2.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(20, 0).addBox(-2.8284F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(0, 0).addBox(-2.8284F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(0, 0).addBox(-0.1716F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(20, 0).addBox(-0.1716F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(20, 0).addBox(-5.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false);
		cube3.texOffs(0, 0).addBox(-5.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false);

		LegacyModelPart cube4 = new LegacyModelPart(this);
		cube4.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube4, 0.0F, 0.0F, -0.4363F);
		cube3.addChild(cube4);
		cube4.texOffs(26, 20).addBox(5.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false);
		cube4.texOffs(26, 20).addBox(5.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false);

		LegacyModelPart cube5 = new LegacyModelPart(this);
		cube5.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube5, 0.0F, 0.0F, 0.4363F);
		cube3.addChild(cube5);
		cube5.texOffs(26, 20).addBox(-7.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false);
		cube5.texOffs(26, 20).addBox(-7.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false);

		LegacyModelPart cube6 = new LegacyModelPart(this);
		cube6.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube6, -0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube6);
		cube6.texOffs(18, 20).addBox(-2.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false);
		cube6.texOffs(18, 20).addBox(1.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false);

		LegacyModelPart cube7 = new LegacyModelPart(this);
		cube7.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube7, 0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube7);
		cube7.texOffs(18, 20).addBox(-2.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false);
		cube7.texOffs(18, 20).addBox(1.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false);

		LegacyModelPart cube2 = new LegacyModelPart(this);
		cube2.setPos(0.0F, 0.0F, 0.0F);
		setRotation(cube2, 0.0F, -1.5708F, 0.0F);
		base.addChild(cube2);
		cube2.texOffs(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false);

		coin = new LegacyModelPart(this);
		coin.setPos(0.0F, 11.5F, 0.0F);
		setRotation(coin, -1.5708F, 0.0F, 0.0F);
		coin.texOffs(0, 27).addBox(-1.5F, -0.499F, 0.6F, 3, 1, 2, 0.0F, false);
		coin.texOffs(10, 27).addBox(-1.5F, -0.501F, -2.5962F, 3, 1, 2, 0.0F, false);

		LegacyModelPart cube8 = new LegacyModelPart(this);
		cube8.setPos(-0.5F, 11.5F, 1.6F);
		setRotation(cube8, 0.0F, -1.0472F, 0.0F);
		coin.addChild(cube8);
		cube8.texOffs(10, 27).addBox(-2.634F, -11.998F, -3.8301F, 3, 1, 2, 0.0F, false);
		cube8.texOffs(0, 27).addBox(-2.634F, -12.002F, -0.634F, 3, 1, 2, 0.0F, false);

		LegacyModelPart cube9 = new LegacyModelPart(this);
		cube9.setPos(-0.5F, 11.5F, 1.6F);
		setRotation(cube9, 0.0F, 1.0472F, 0.0F);
		coin.addChild(cube9);
		cube9.texOffs(0, 27).addBox(0.134F, -12.0F, 0.2321F, 3, 1, 2, 0.0F, false);
		cube9.texOffs(10, 27).addBox(0.134F, -12.0F, -2.9641F, 3, 1, 2, 0.0F, false);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		coin.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	public void setRotation(LegacyModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		coin.yRot = (ageInTicks * 0.045f) % 360;
	}
}
