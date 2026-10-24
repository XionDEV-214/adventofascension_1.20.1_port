package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class AoABannerPatterns {
	public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, AdventOfAscension.MOD_ID);

	public static final RegistryObject<BannerPattern> COMPASS_RUNE = register("compass_rune", "aoacomr");
	public static final RegistryObject<BannerPattern> DISTORTION_RUNE = register("distortion_rune", "aoadisr");
	public static final RegistryObject<BannerPattern> ENERGY_RUNE = register("energy_rune", "aoaener");
	public static final RegistryObject<BannerPattern> FIRE_RUNE = register("fire_rune", "aoafirr");
	public static final RegistryObject<BannerPattern> KINETIC_RUNE = register("kinetic_rune", "aoakinr");
	public static final RegistryObject<BannerPattern> LIFE_RUNE = register("life_rune", "aoalifr");
	public static final RegistryObject<BannerPattern> LUNAR_RUNE = register("lunar_rune", "aoalunr");
	public static final RegistryObject<BannerPattern> POISON_RUNE = register("poison_rune", "aoapoir");
	public static final RegistryObject<BannerPattern> POWER_RUNE = register("power_rune", "aoapowr");
	public static final RegistryObject<BannerPattern> STORM_RUNE = register("storm_rune", "aoastor");
	public static final RegistryObject<BannerPattern> STRIKE_RUNE = register("strike_rune", "aoastrr");
	public static final RegistryObject<BannerPattern> WATER_RUNE = register("water_rune", "aoawatr");
	public static final RegistryObject<BannerPattern> WIND_RUNE = register("wind_rune", "aoawinr");
	public static final RegistryObject<BannerPattern> WITHER_RUNE = register("wither_rune", "aoawithr");

	private static RegistryObject<BannerPattern> register(String id, String hash) {
		return BANNER_PATTERNS.register(id, () -> new BannerPattern(hash));
	}

	public static TagKey<BannerPattern> itemTag(String id) {
		return TagKey.create(Registries.BANNER_PATTERN, AdventOfAscension.id("pattern_item/" + id));
	}
}
