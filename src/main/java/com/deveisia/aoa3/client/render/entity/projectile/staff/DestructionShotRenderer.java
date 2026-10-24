package com.deveisia.aoa3.client.render.entity.projectile.staff;

import com.deveisia.aoa3.content.entity.projectile.staff.DestructionShotEntity;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DestructionShotRenderer extends EntityRenderer<DestructionShotEntity> {
	private final ResourceLocation texture;

	public DestructionShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(DestructionShotEntity entity) {
		return texture;
	}
}
