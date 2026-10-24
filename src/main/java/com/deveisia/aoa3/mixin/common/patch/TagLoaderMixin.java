package com.deveisia.aoa3.mixin.common.patch;

import com.deveisia.aoa3.library.compat.HarvestTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(TagLoader.class)
public class TagLoaderMixin {
	@Shadow
	@Final
	private String directory;

	@Inject(method = "load", at = @At("RETURN"))
	private void aoa3$injectHarvestTags(ResourceManager resourceManager, CallbackInfoReturnable<Map<ResourceLocation, List<TagLoader.EntryWithSource>>> callback) {
		if ("tags/blocks".equals(this.directory))
			HarvestTags.inject(callback.getReturnValue());
	}
}
