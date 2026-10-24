package com.deveisia.aoa3.mixin.common.accessor;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerLevel.class)
public interface ServerWorldAccessor {
	@Accessor("customSpawners")
	List<CustomSpawner> getCustomSpawners();

	@Mutable
	@Accessor("customSpawners")
	void setCustomSpawners(List<CustomSpawner> spawners);
}
