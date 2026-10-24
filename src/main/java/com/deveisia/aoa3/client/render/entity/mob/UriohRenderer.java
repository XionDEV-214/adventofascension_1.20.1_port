package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.nowhere.UriohModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class UriohRenderer extends AoAMobRenderer {
	private static final float shadowScale = AoAEntities.Mobs.URIOH.get().getWidth() / 3f;
	public UriohRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new UriohModel(), shadowScale, 1.0f, ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/nowhere/urioh.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		float scale = Math.max(0.1f, entity.getHealth() / entity.getMaxHealth());

		matrix.scale(scale, scale, scale);
		shadowRadius = shadowScale * scale;
	}
}
