package com.deveisia.aoa3.client.model.entity.projectile.thrown;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ChakramModel extends LegacyEntityModel<BaseBullet> {
	private final LegacyModelPart part;

	public ChakramModel() {
		texWidth = 16;
		texHeight = 16;

		part = new LegacyModelPart(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 8).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		LegacyModelPart part2 = new LegacyModelPart(this);

		part2.setPos(0.0F, 0.0F, 0.0F);
		part.addChild(part2);
		setRotation(part2, 0.0F, -0.7854F, 0.0F);
		part2.texOffs(0, 0).addBox(-1.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, -0.1F, false);
		part2.texOffs(0, 0).addBox(0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, -0.1F, true);
		part2.texOffs(0, 6).addBox(-2.5F, -0.5F, -1.5F, 5.0F, 1.0F, 1.0F, -0.1F, true);
		part2.texOffs(0, 6).addBox(-2.5F, -0.5F, 0.5F, 5.0F, 1.0F, 1.0F, -0.1F, true);
	}

	@Override
	public void setupAnim(BaseBullet chakram, float pLimbSwing, float pLimbSwingAmount, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (chakram.getDeltaMovement().x() != 0 || chakram.getDeltaMovement().y() != 0 || chakram.getDeltaMovement().z() != 0) {
			part.yRot = Mth.lerp(pLimbSwingAmount, tickAge - 1 % 360, tickAge % 360);
		}
		else {
			part.yRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		part.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotation(LegacyModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
