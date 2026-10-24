package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.precasia.TerradonModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class TerradonRenderer extends AoAMobRenderer {
	private final ResourceLocation invulnTexture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/precasia/terradon_invincible.png");

	public TerradonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TerradonModel(), AoAEntities.Mobs.TERRADON.get().getWidth() / 3f, 1.0f, ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/precasia/terradon.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return entity.isInvulnerable() ? invulnTexture : super.getTextureLocation(entity);
	}
}
