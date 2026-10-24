package com.deveisia.aoa3.client.render.entity.layer;

import com.deveisia.aoa3.content.entity.mob.creeponia.AoACreeponiaCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CustomCreeperChargeRenderLayer extends RenderLayer<AoACreeponiaCreeper, EntityModel<AoACreeponiaCreeper>> {
	private static final ResourceLocation texture = ResourceLocation.parse("textures/entity/creeper/creeper_armor.png");
	private final EntityModel<AoACreeponiaCreeper> model;

	public CustomCreeperChargeRenderLayer(RenderLayerParent<AoACreeponiaCreeper, EntityModel<AoACreeponiaCreeper>> renderer, EntityModel<AoACreeponiaCreeper> model) {
		super(renderer);

		this.model = model;
	}

	@Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, AoACreeponiaCreeper entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isCharged()) {
			float renderTicks = (float)entity.tickCount + partialTicks;
			EntityModel<AoACreeponiaCreeper> model = getExpandedModel();

			model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
			getParentModel().copyPropertiesTo(model);

			VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.energySwirl(getLayerTexture(), getLayerUV(renderTicks), renderTicks * 0.01F));

			model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
		}
	}

	protected float getLayerUV(float uvBase) {
		return uvBase * 0.01f;
	}

	protected ResourceLocation getLayerTexture() {
		return texture;
	}

	protected EntityModel<AoACreeponiaCreeper> getExpandedModel() {
		return model;
	}
}
