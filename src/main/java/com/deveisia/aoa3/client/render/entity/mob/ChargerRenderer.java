package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.overworld.ChargerModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ChargerRenderer extends AoAMobRenderer {
	private final ResourceLocation texture;

	public ChargerRenderer(EntityRendererProvider.Context renderManager, final ResourceLocation resource) {
		super(renderManager, new ChargerModel(), AoAEntities.Mobs.CHARGER.get().getWidth() / 3f, 1, resource);

		texture = ChargerModel.getChargerTexture((ChargerModel)model, resource);
	}

	public ResourceLocation getTexture() {
		return texture;
	}
}
