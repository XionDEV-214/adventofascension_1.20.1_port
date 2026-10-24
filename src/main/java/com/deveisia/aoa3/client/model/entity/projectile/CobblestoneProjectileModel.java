package com.deveisia.aoa3.client.model.entity.projectile;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;

public class CobblestoneProjectileModel extends LegacyEntityModel<Entity> {
	private final LegacyModelPart block;

	public CobblestoneProjectileModel() {
		texWidth = 64;
		texHeight = 32;

		block = new LegacyModelPart(this);
		block.setPos(0.0F, 16.0F, 0.0F);
		block.texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, -2.0F, true);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		block.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		block.xRot += limbSwingAmount * 0.03f + 1;
		block.yRot += limbSwingAmount * 0.03f + 1;
		block.zRot += limbSwingAmount * 0.03f + 1;
	}
}
