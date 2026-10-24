package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.runandor.RuneTemplarModel;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.mob.runandor.templars.RuneTemplarEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RuneTemplarRenderer extends MobRenderer<RuneTemplarEntity, EntityModel<RuneTemplarEntity>> {
	private final ResourceLocation texture;
	private final ResourceLocation disabledTexture;

	public RuneTemplarRenderer(EntityRendererProvider.Context renderManager, ResourceLocation texture, ResourceLocation disabledTexture) {
		super(renderManager, new RuneTemplarModel(), AoAEntities.Mobs.BLUE_RUNE_TEMPLAR.get().getWidth() / 3f);

		this.texture = texture;
		this.disabledTexture = disabledTexture;
	}

	@Override
	public ResourceLocation getTextureLocation(RuneTemplarEntity entity) {
		return entity.isDisabled() ? disabledTexture : texture;
	}
}
