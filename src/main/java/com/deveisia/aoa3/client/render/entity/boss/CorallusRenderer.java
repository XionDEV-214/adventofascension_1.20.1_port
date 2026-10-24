package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.CorallusModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.CorallusEntity;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CorallusRenderer extends MobRenderer<CorallusEntity, EntityModel<CorallusEntity>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/corallus/corallus.png");
	private static final ResourceLocation ENRAGED_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/corallus/corallus_enraged.png");

	public CorallusRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CorallusModel(), AoAEntities.Mobs.CORALLUS.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CorallusEntity corallus) {
		return corallus.isEnraged() ? ENRAGED_TEXTURE : TEXTURE;
	}
}
