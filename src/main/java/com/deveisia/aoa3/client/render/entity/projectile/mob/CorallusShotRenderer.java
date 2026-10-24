package com.deveisia.aoa3.client.render.entity.projectile.mob;

import com.deveisia.aoa3.client.model.entity.projectile.CorallusShotModel;
import com.deveisia.aoa3.content.entity.projectile.mob.CorallusShotEntity;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class CorallusShotRenderer extends LivingEntityRenderer<CorallusShotEntity, EntityModel<CorallusShotEntity>> {
	private final ResourceLocation TEXTURE;

	public CorallusShotRenderer(EntityRendererProvider.Context renderManager, final ResourceLocation resource) {
		super(renderManager, new CorallusShotModel(), 0);
		TEXTURE = resource;
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CorallusShotEntity entity) {
		return TEXTURE;
	}
}
