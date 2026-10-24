package com.deveisia.aoa3.client.model.entity.projectile.thrown;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.projectile.thrown.HellfireEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class HellfireModel extends LegacyEntityModel<HellfireEntity> {
	private final LegacyModelPart part;

	public HellfireModel() {
		texWidth = 32;
		texHeight = 16;

		part = new LegacyModelPart(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		part.texOffs(0, 9).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(HellfireEntity hellfire, float pLimbSwing, float partialTicks, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (hellfire.getDeltaMovement().x() != 0 || hellfire.getDeltaMovement().y() != 0 || hellfire.getDeltaMovement().z() != 0) {
			part.yRot = Mth.lerp(partialTicks, tickAge - 1 % 360, tickAge % 360);
			part.xRot = Mth.lerp(partialTicks, tickAge % 360, tickAge + 1 % 360);
			part.zRot = Mth.lerp(partialTicks, tickAge + 1 % 360, tickAge + 2 % 360);
		}
		else {
			part.xRot = 0;
			part.yRot = 0;
			part.zRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		part.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(LegacyModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
