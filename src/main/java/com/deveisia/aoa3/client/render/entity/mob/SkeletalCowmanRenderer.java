package com.deveisia.aoa3.client.render.entity.mob;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletalCowmanRenderer extends HumanoidMobRenderer<Monster, HumanoidModel<Monster>> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/nether/skeletal_cowman.png");

	public SkeletalCowmanRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new HumanoidModel<Monster>(LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0), 64, 32).bakeRoot()), AoAEntities.Mobs.SKELETAL_COWMAN.get().getWidth() / 3f);
		addLayer(new ItemInHandLayer<Monster, HumanoidModel<Monster>>(this, renderManager.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(Monster entity) {
		return texture;
	}
}
