package com.deveisia.aoa3.client.model.entity;

import com.deveisia.aoa3.client.model.BaseGeoModel;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public class EntityGeoModel<T extends Entity & GeoAnimatable> extends BaseGeoModel<T> {
	public EntityGeoModel(String assetSubpath) {
		super(assetSubpath);
	}

	public EntityGeoModel(String modelSubpath, String textureSubpath) {
		super(modelSubpath, textureSubpath);
	}

	public EntityGeoModel(String modelSubpath, String textureSubpath, String animationSubpath) {
		super(modelSubpath, textureSubpath, animationSubpath);
	}

	@Override
	protected String subtype() {
		return "entity";
	}
}
