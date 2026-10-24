package com.deveisia.aoa3.content.entity.animal.fish;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.deveisia.aoa3.client.render.AoAAnimations;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicFishEntity extends AbstractFish implements GeoEntity {
	private final AnimatableInstanceCache animationFactory = GeckoLibUtil.createInstanceCache(this);

	public BasicFishEntity(EntityType<? extends BasicFishEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return ItemStack.EMPTY;
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
