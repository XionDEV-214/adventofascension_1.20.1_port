package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.RockRiderModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.RockRiderEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RockRiderRenderer extends MobRenderer<RockRiderEntity, EntityModel<RockRiderEntity>> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/rockrider/rock_rider.png");
	private static final ResourceLocation ALTERNATE_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/rockrider/rock_rider_alternate.png");

	public RockRiderRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RockRiderModel(), AoAEntities.Mobs.ROCK_RIDER.get().getWidth() / 3);
	}

	@Override
	protected void scale(RockRiderEntity rockRider, PoseStack matrix, float partialTicks) {
		matrix.scale(1.5f, 1.5f, 1.5f);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(RockRiderEntity corallus) {
		return corallus.isAlternateForm() ? ALTERNATE_TEXTURE : TEXTURE;
	}
}
