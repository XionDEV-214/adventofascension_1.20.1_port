package com.deveisia.aoa3.client.model.entity.boss;

import com.deveisia.aoa3.client.model.entity.EntityGeoModel;
import com.deveisia.aoa3.content.entity.boss.KrorEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;

public class KrorModel extends EntityGeoModel<KrorEntity> {
	public KrorModel() {
		super("boss/kror/kror");
	}

	@Override
	public void setCustomAnimations(KrorEntity entity, long instanceId, AnimationState<KrorEntity> animationState) {
		super.setCustomAnimations(entity, instanceId, animationState);

		CoreGeoBone root = getAnimationProcessor().getBone("body");

		if (root != null) {
			root.setScaleX(1.25f);
			root.setScaleY(1.25f);
			root.setScaleZ(1.25f);
		}
	}
}
