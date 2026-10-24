package com.deveisia.aoa3.mixin.common.accessor;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(AbstractArrow.class)
public interface AbstractArrowAccessor {
	@Accessor("piercingIgnoreEntityIds")
	IntOpenHashSet getPiercingIgnoreEntityIds();

	@Accessor("piercingIgnoreEntityIds")
	void setPiercingIgnoreEntityIds(IntOpenHashSet ids);

	@Accessor("piercedAndKilledEntities")
	List<Entity> getPiercedAndKilledEntities();

	@Accessor("piercedAndKilledEntities")
	void setPiercedAndKilledEntities(List<Entity> entities);

	@Accessor("lastState")
	void setLastState(BlockState state);

	@Accessor("knockback")
	int getKnockbackStrength();
}
