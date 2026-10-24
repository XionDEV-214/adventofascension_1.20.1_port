package com.deveisia.aoa3.client.model;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public abstract class BaseGeoModel<T extends GeoAnimatable> extends GeoModel<T> {
	private final ResourceLocation model;
	private final ResourceLocation texture;
	private final ResourceLocation animations;

	public BaseGeoModel(String assetSubpath) {
		this(assetSubpath, assetSubpath, assetSubpath);
	}

	public BaseGeoModel(String modelSubpath, String textureSubpath) {
		this(modelSubpath, textureSubpath, modelSubpath);
	}

	public BaseGeoModel(String modelSubpath, String textureSubpath, String animationSubpath) {
		this.model = AdventOfAscension.id("geo/" + subtype() + "/" + modelSubpath + ".geo.json");
		this.texture = AdventOfAscension.id("textures/" + subtype() + "/" + textureSubpath + ".png");
		this.animations = AdventOfAscension.id("animations/" + subtype() + "/" + animationSubpath + ".animation.json");
	}

	protected abstract String subtype();

	@Override
	public ResourceLocation getModelResource(T animatable) {
		return model;
	}

	@Override
	public ResourceLocation getTextureResource(T animatable) {
		return texture;
	}

	@Override
	public ResourceLocation getAnimationResource(T animatable) {
		return animations;
	}
}
