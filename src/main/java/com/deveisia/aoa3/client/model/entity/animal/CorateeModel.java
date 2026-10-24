package com.deveisia.aoa3.client.model.entity.animal;

import com.deveisia.aoa3.client.model.entity.EntityGeoModel;
import com.deveisia.aoa3.content.entity.animal.CorateeEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;

public class CorateeModel extends EntityGeoModel<CorateeEntity> {
	public CorateeModel() {
		super("animal/lborean/coratee");
	}

	@Override
	public void setCustomAnimations(CorateeEntity entity, long instanceId, AnimationState<CorateeEntity> animationState) {
		super.setCustomAnimations(entity, instanceId, animationState);

		if (entity.isBaby()) {
			CoreGeoBone root = getAnimationProcessor().getBone("bone");
			CoreGeoBone head = getAnimationProcessor().getBone("head");

			if (root != null) {
				root.setScaleX(0.5f);
				root.setScaleY(0.5f);
				root.setScaleZ(0.5f);
			}

			if (head != null) {
				head.setScaleX(1.5f);
				head.setScaleY(1.5f);
				head.setScaleZ(1.5f);
			}
		}
	}
}
