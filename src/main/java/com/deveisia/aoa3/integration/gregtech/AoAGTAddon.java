package com.deveisia.aoa3.integration.gregtech;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@GTAddon
public class AoAGTAddon implements IGTAddon {
	public static final GTRegistrate REGISTRATE = GTRegistrate.create(AdventOfAscension.MOD_ID);

	@Override
	public GTRegistrate getRegistrate() {
		return REGISTRATE;
	}

	@Override
	public void initializeAddon() {}

	@Override
	public String addonModId() {
		return AdventOfAscension.MOD_ID;
	}

	@Override
	public void registerElements() {
		AoAGTElements.init();
	}

	@Override
	public void registerTagPrefixes() {
		AoAOrePrefixes.init();
	}

	@Override
	public void registerWorldgenLayers() {
		AoAWorldGenLayers.init();
	}

	@Override
	public void registerOreVeins() {
		AoAOreVeins.init();
	}

	@Override
	public void addRecipes(Consumer<FinishedRecipe> provider) {
		AoAGTRecipes.addRecipes(provider);
	}
}
