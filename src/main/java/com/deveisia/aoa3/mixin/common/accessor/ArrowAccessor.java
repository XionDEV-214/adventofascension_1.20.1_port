package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(Arrow.class)
public interface ArrowAccessor {
	@Accessor("potion")
	Potion getPotion();

	@Accessor("potion")
	void setPotion(Potion potion);

	@Accessor("effects")
	Set<MobEffectInstance> getEffects();

	@Invoker("setFixedColor")
	void invokeSetFixedColor(int colour);
}
