package com.deveisia.aoa3.client.render.entity.misc;

import com.deveisia.aoa3.client.model.entity.misc.LottoTotemModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

public class LottoTotemRenderer extends EntityRenderer<Entity> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/misc/lotto_totem.png");
	private final EntityModel<Entity> model = new LottoTotemModel();

	public LottoTotemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public void render(Entity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.translate(0, 1.5d, 0);
		matrix.mulPose(Axis.XP.rotationDegrees(180));
		matrix.scale(-1, 1, -1);

		VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Entity entity) {
		return texture;
	}
}
