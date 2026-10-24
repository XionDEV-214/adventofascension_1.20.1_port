package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CraftingMenu.class)
public interface CraftingMenuAccessor {
	@Accessor("craftSlots")
	CraftingContainer getCraftSlots();

	@Accessor("access")
	ContainerLevelAccess getAccess();
}
