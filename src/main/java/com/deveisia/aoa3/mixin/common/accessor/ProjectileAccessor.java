package com.deveisia.aoa3.mixin.common.accessor;

import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Projectile.class)
public interface ProjectileAccessor {
	@Accessor("leftOwner")
	boolean getLeftOwner();

	@Accessor("leftOwner")
	void setLeftOwner(boolean leftOwner);

	@Invoker("checkLeftOwner")
	boolean invokeCheckLeftOwner();
}
