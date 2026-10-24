package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Player.class)
public interface PlayerAccessor {
	@Accessor("jumpTriggerTime")
	int getJumpTriggerTime();

	@Accessor("jumpTriggerTime")
	void setJumpTriggerTime(int ticks);
}
