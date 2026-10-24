package com.deveisia.aoa3.mixin.common.patch;

import com.deveisia.aoa3.library.compat.HarvestData;
import com.deveisia.aoa3.mixin.common.accessor.BlockBehaviourAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.Properties.class)
public class BlockPropertiesCopyMixin {
	@Inject(method = "copy", at = @At("RETURN"))
	private static void aoa3$copyHarvestData(BlockBehaviour block, CallbackInfoReturnable<BlockBehaviour.Properties> callback) {
		HarvestData.copy(((BlockBehaviourAccessor)block).getProperties(), callback.getReturnValue());
	}
}
