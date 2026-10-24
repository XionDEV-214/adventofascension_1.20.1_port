package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.misc.LunarcherModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.FlyingMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LunarcherRenderer extends HumanoidMobRenderer<FlyingMob, LunarcherModel<FlyingMob>> {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/lunalus/lunarcher.png");

	public LunarcherRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new LunarcherModel(), AoAEntities.Mobs.LUNARCHER.get().getWidth() / 3f);

		addLayer(new ItemInHandLayer<>(this, renderManager.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(FlyingMob entity) {
		return texture;
	}
}
