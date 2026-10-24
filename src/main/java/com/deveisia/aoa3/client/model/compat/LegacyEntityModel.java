package com.deveisia.aoa3.client.model.compat;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

public abstract class LegacyEntityModel<T extends Entity> extends EntityModel<T> implements LegacyModel {
	public int texWidth = 64;
	public int texHeight = 32;

	protected LegacyEntityModel() {
		super();
	}

	protected LegacyEntityModel(Function<ResourceLocation, RenderType> renderType) {
		super(renderType);
	}

	@Override
	public int getTexWidth() {
		return this.texWidth;
	}

	@Override
	public int getTexHeight() {
		return this.texHeight;
	}
}
