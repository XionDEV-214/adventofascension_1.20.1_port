package com.deveisia.aoa3.content.entity.animal.fish;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.content.entity.base.AbstractLavaFishEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BasicLavaFishEntity extends AbstractLavaFishEntity implements GeoEntity {
	private final AnimatableInstanceCache animationFactory = GeckoLibUtil.createInstanceCache(this);

	public BasicLavaFishEntity(EntityType<? extends BasicLavaFishEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}

	@Override
	protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
		return InteractionResult.PASS;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(AoAAnimations.genericSwimController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return animationFactory;
	}
}
