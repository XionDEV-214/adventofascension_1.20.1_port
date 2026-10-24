package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.abyss.WebReaperModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.mob.abyss.WebReaperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class WebReaperRenderer extends AoAMobRenderer {
	public WebReaperRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new WebReaperModel(), AoAEntities.Mobs.WEB_REAPER.get().getWidth() / 3f, 1.0f, ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/abyss/web_reaper.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		if (entity instanceof WebReaperEntity) {
			float scale = ((WebReaperEntity)entity).getStageMod();

			matrix.scale(scale, scale, scale);
		}
	}
}
