package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Villager.class)
public interface VillagerAccessor {
	@Accessor("villagerXp")
	int getVillagerXp();

	@Accessor("villagerXp")
	void setVillagerXp(int xp);

	@Accessor("lastTradedPlayer")
	void setLastTradedPlayer(Player player);

	@Accessor("updateMerchantTimer")
	void setUpdateMerchantTimer(int ticks);

	@Accessor("increaseProfessionLevelOnUpdate")
	void setIncreaseProfessionLevelOnUpdate(boolean increase);

	@Invoker("shouldIncreaseLevel")
	boolean invokeShouldIncreaseLevel();

	@Invoker("tellWitnessesThatIWasMurdered")
	void invokeTellWitnessesThatIWasMurdered(Entity murderer);

	@Invoker("allowedToRestock")
	boolean invokeAllowedToRestock();
}
