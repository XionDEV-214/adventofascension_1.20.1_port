package com.deveisia.aoa3.client.render;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import net.minecraft.world.entity.LivingEntity;

public final class AoAAnimations {
	public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("misc.idle");
	public static final RawAnimation RECOVER = RawAnimation.begin().thenPlay("misc.rest");
	public static final RawAnimation EAT = RawAnimation.begin().thenPlay("misc.eat");
	public static final RawAnimation SUCCEED = RawAnimation.begin().thenPlay("misc.succeed");
	public static final RawAnimation SPAWN = RawAnimation.begin().thenPlay("misc.spawn");

	public static final RawAnimation WALK = RawAnimation.begin().thenLoop("move.walk");
	public static final RawAnimation RUN = RawAnimation.begin().thenLoop("move.run");
	public static final RawAnimation FLY = RawAnimation.begin().thenLoop("move.fly");
	public static final RawAnimation SWIM = RawAnimation.begin().thenLoop("move.swim");

	public static final RawAnimation ATTACK_SWING = RawAnimation.begin().thenPlay("attack.swing");
	public static final RawAnimation ATTACK_THROW = RawAnimation.begin().thenPlay("attack.throw");
	public static final RawAnimation ATTACK_BITE = RawAnimation.begin().thenPlay("attack.bite");
	public static final RawAnimation ATTACK_SLAM = RawAnimation.begin().thenPlay("attack.slam");
	public static final RawAnimation ATTACK_STRIKE = RawAnimation.begin().thenPlay("attack.strike");
	public static final RawAnimation ATTACK_SPIN = RawAnimation.begin().thenPlay("attack.spin");
	public static final RawAnimation ATTACK_FLYING_BITE = RawAnimation.begin().thenPlay("attack.midair_bite");
	public static final RawAnimation ATTACK_SHOOT = RawAnimation.begin().thenPlay("attack.shoot");

	public static <T extends GeoEntity> AnimationController<T> genericIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			event.getController().setAnimation(IDLE);

			return PlayState.CONTINUE;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericWalkController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(WALK);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericSwimController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(SWIM);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericSwimIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(SWIM);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericWalkIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(WALK);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericFlyController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			event.getController().setAnimation(FLY);

			return PlayState.CONTINUE;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericFlyIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(FLY);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends GeoEntity> AnimationController<T> genericWalkRunIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(WALK);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> genericAttackController(T entity, RawAnimation attackAnimation) {
		return new AnimationController<T>(entity, "attacking", 0, event -> {
			if (entity.swinging) {
				event.getController().setAnimation(attackAnimation);

				return PlayState.CONTINUE;
			}

			event.getController().forceAnimationReset();
			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> customAttackController(T entity, AnimationController.AnimationStateHandler<T> controllerPredicate) {
		return new AnimationController<T>(entity, "attacking", 0, controllerPredicate);
	}

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> genericSpawnController(T entity, int spawnTicks) {
		return new AnimationController<T>(entity, "spawning", 0, event -> {
			if (entity.tickCount < spawnTicks) {
				event.getController().setAnimation(SPAWN);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}
}
