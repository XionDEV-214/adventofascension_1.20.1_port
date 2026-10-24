package com.deveisia.aoa3.client.model.entity.mob.shyrelands;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class ArcflowerModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart body5;
	private final LegacyModelPart body9;

	public ArcflowerModel() {
		texWidth = 64;
		texHeight = 64;

		body5 = new LegacyModelPart(this, 23, 35);
		body5.addBox(-7.5F, -17.0F, -7.0F, 16, 16, 0);
		body5.setPos(5.0F, 25.0F, 5.0F);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.7853982F, 0.0F);
		body9 = new LegacyModelPart(this, 23, 35);
		body9.addBox(-8.5F, -17.0F, -7.0F, 16, 16, 0);
		body9.setPos(-4.0F, 25.0F, 5.0F);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, -0.7853982F, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(LegacyModelPart model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}
