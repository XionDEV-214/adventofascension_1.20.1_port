package com.deveisia.aoa3.client.model.misc;

import com.deveisia.aoa3.client.model.compat.LegacyModel;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class FullbrightModelRenderer extends LegacyModelPart {
	public FullbrightModelRenderer(LegacyModel model) {
		super(model);
	}

	public FullbrightModelRenderer(LegacyModel model, int uOffset, int vOffset) {
		super(model, uOffset, vOffset);
	}

	public FullbrightModelRenderer(int textureWidth, int textureHeight, int uOffset, int vOffset) {
		super(textureWidth, textureHeight, uOffset, vOffset);
	}

	@Override
	public void render(PoseStack matrixStack, VertexConsumer vertexBuilder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.render(matrixStack, vertexBuilder, 15728880, packedOverlay, red, green, blue, alpha);
	}
}
