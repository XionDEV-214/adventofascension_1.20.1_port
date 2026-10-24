package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.HiveKingModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.HiveKingEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HiveKingRenderer extends MobRenderer<HiveKingEntity, EntityModel<HiveKingEntity>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/hiveking/hive_king.png");

	public HiveKingRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new HiveKingModel(), AoAEntities.Mobs.HIVE_KING.get().getWidth() / 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(HiveKingEntity entity) {
		return TEXTURE;
	}
}
