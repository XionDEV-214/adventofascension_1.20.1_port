package com.deveisia.aoa3.client.render.entity.animal;

import com.deveisia.aoa3.client.model.entity.animal.ShikModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.animal.ShikEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShikRenderer extends MobRenderer<ShikEntity, EntityModel<ShikEntity>> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/passive/shik.png");

	public ShikRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ShikModel(), AoAEntities.Animals.SHIK.get().getWidth() / 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(ShikEntity entity) {
		return texture;
	}
}
