package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.NethengeicWitherModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.NethengeicWitherEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NethengeicWitherRenderer extends MobRenderer<NethengeicWitherEntity, EntityModel<NethengeicWitherEntity>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither.png");
	private static final ResourceLocation ENRAGED_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither_enraged.png");
	private static final ResourceLocation CATACLYSMIC_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither_cataclysmic.png");

	public NethengeicWitherRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new NethengeicWitherModel(), AoAEntities.Mobs.NETHENGEIC_WITHER.get().getWidth() / 3);
	}

	@Override
	protected void scale(NethengeicWitherEntity nethengeicWither, PoseStack matrix, float partialTicks) {
		matrix.scale(1.5f, 1.5f, 1.5f);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(NethengeicWitherEntity nethengeicWither) {
		switch (nethengeicWither.getStage()) {
			case 1:
			default:
				return TEXTURE;
			case 2:
				return ENRAGED_TEXTURE;
			case 3:
				return CATACLYSMIC_TEXTURE;
		}
	}
}
