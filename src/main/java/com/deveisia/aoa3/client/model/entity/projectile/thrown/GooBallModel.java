package com.deveisia.aoa3.client.model.entity.projectile.thrown;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.content.entity.projectile.thrown.GooBallEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class GooBallModel extends LegacyEntityModel<GooBallEntity> {
	private final LegacyModelPart part;

	public GooBallModel() {
		texWidth = 32;
		texHeight = 16;

		part = new LegacyModelPart(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		part.texOffs(0, 0).addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
		part.texOffs(16, 2).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GooBallEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		part.yRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks - 1 % 360, pAgeInTicks % 360);
		part.xRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks % 360, pAgeInTicks + 1 % 360);
		part.zRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks + 1 % 360, pAgeInTicks + 2 % 360);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		part.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}
