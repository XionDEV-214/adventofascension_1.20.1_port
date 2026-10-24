package com.deveisia.aoa3.client.model.entity.mob.overworld;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Mob;

public class DeadTreeModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart trunk;

	public DeadTreeModel() {
		texWidth = 64;
		texHeight = 32;

		trunk = new LegacyModelPart(this);
		trunk.setPos(0.0F, 24.0F, 0.0F);
		trunk.texOffs(0, 0).addBox(-7.0F, -16.0F, -7.0F, 14, 16, 14, 0.0F, false);
		trunk.texOffs(0, 0).addBox(-7.0F, -32.0F, -7.0F, 14, 16, 14, 0.0F, false);
		trunk.texOffs(0, 0).addBox(-7.0F, -48.0F, -7.0F, 14, 16, 14, 0.0F, false);
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		trunk.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	public void setRotation(LegacyModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
