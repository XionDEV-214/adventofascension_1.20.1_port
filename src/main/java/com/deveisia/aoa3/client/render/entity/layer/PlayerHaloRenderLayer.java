package com.deveisia.aoa3.client.render.entity.layer;

import com.deveisia.aoa3.client.model.entity.misc.PlayerHaloModel;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.util.AoAHaloUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class PlayerHaloRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/player/halo_texture.png");
	private static final PlayerHaloModel MODEL = new PlayerHaloModel();
	private final PlayerRenderer renderer;

	public PlayerHaloRenderLayer(PlayerRenderer playerRenderer) {
		super(playerRenderer);

		this.renderer = playerRenderer;
	}

	@Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLightIn, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (player.isCapeLoaded() && !player.isInvisible() && AoAConfig.CLIENT.showPlayerHalos.get()) {
			AoAHaloUtil.Type chosenHalo = AoAHaloUtil.getHalo(player.getUUID());
			float red = 0;
			float green = 0;
			float blue = 0;

			if (chosenHalo == null)
				return;

			switch (chosenHalo) {
				case Donator:
					red = 1;
					green = 1;
					break;
				case Super_Donator:
				case Crazy_Donator:
					red = 1;
					break;
				case Tslat:
					red = 0.1647f;
					green = 1;
					break;
				case Staff:
					red = 0.6f;
					green = 1;
					blue = 1;
					break;
				case Wiki_Editor:
					blue = 1;
					break;
			}

			MODEL.halo.copyFrom(this.renderer.getModel().head);
			VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.eyes(TEXTURE));
			MODEL.renderToBuffer(matrix, vertexBuilder, 15728640, OverlayTexture.NO_OVERLAY, red, green, blue, 1f);
		}
	}

	@Override
	protected ResourceLocation getTextureLocation(AbstractClientPlayer entityIn) {
		return TEXTURE;
	}
}
