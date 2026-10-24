package com.deveisia.aoa3.integration.patchouli;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import vazkii.patchouli.api.PatchouliAPI;

public class PatchouliIntegration {
	public static void preInit() {
		if (FMLEnvironment.dist == Dist.CLIENT)
			PatchouliClientIntegration.init();
	}

	public static boolean isBookLoaded(ResourceLocation id) {
		try {
			PatchouliAPI.get().getSubtitle(id);

			return true;
		}
		catch (IllegalArgumentException ex) {
			return false;
		}
	}

	public static ItemStack getBook(ResourceLocation id) {
		return PatchouliAPI.get().getBookStack(id);
	}

	public static void openBook(ResourceLocation id) {
		PatchouliAPI.get().openBookGUI(id);
	}
}
