package com.deveisia.aoa3.client.render.entity.mob;

import net.minecraft.client.model.geom.ModelLayers;
import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;

public class HappyRenderer extends HumanoidMobRenderer<Monster, HumanoidModel<Monster>> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/celeve/happy.png");

	public HappyRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AbstractZombieModel<Monster>(renderManager.bakeLayer(ModelLayers.ZOMBIE)) {
			@Override
			public boolean isAggressive(Monster entityIn) {
				return false;
			}
		}, AoAEntities.Mobs.HAPPY.get().getWidth() / 3f);

		addLayer(new ItemInHandLayer<>(this, renderManager.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(Monster entity) {
		return texture;
	}
}
