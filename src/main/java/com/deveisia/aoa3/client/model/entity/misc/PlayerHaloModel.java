package com.deveisia.aoa3.client.model.entity.misc;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.player.AbstractClientPlayer;

public class PlayerHaloModel extends LegacyEntityModel<AbstractClientPlayer> {
	public LegacyModelPart halo;

	public PlayerHaloModel() {
		texWidth = 1;
		texHeight = 1;

		halo = new LegacyModelPart(this, 0, 0);
		halo.setPos(0.0F, 0.0F, 0.0F);
		halo.addBox(-1.0F, -11.5f, -3.0F, 2, 1, 1, 0.0F);
		halo.addBox(-1.0F, -11.5f, 2.0F, 2, 1, 1, 0.0F);
		halo.addBox(-1.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		halo.addBox(-1.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		halo.addBox(-2.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		halo.addBox(-2.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		halo.addBox(-2.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		halo.addBox(-2.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		halo.addBox(-3.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
		halo.addBox(0.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		halo.addBox(0.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		halo.addBox(1.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		halo.addBox(1.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		halo.addBox(1.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		halo.addBox(1.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		halo.addBox(2.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		halo.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(AbstractClientPlayer entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
}
