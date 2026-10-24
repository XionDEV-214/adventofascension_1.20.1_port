package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.CottonCandorModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.CottonCandorEntity;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CottonCandorRenderer extends MobRenderer<CottonCandorEntity, EntityModel<CottonCandorEntity>> {
	private static final ResourceLocation WIND_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wind.png");
	private static final ResourceLocation WATER_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_water.png");
	private static final ResourceLocation FIRE_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_fire.png");
	private static final ResourceLocation WITHER_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wither.png");
	private static final ResourceLocation POISON_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_poison.png");
	private static final ResourceLocation LUNAR_PHASE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_lunar.png");

	public CottonCandorRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CottonCandorModel(), AoAEntities.Mobs.COTTON_CANDOR.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CottonCandorEntity cottonCandor) {
		switch (cottonCandor.getStage()) {
			case 0:
				return WIND_PHASE_TEXTURE;
			case 1:
				return WATER_PHASE_TEXTURE;
			case 2:
				return FIRE_PHASE_TEXTURE;
			case 3:
				return POISON_PHASE_TEXTURE;
			case 4:
				return WITHER_PHASE_TEXTURE;
			case 5:
				return LUNAR_PHASE_TEXTURE;
			default:
				return WIND_PHASE_TEXTURE;
		}
	}
}
