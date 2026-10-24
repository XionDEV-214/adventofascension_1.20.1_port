package com.deveisia.aoa3.library.compat;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Locale;

public enum BiomeCategory {
	NONE("none", null),
	TAIGA("taiga", BiomeTags.IS_TAIGA),
	EXTREME_HILLS("extreme_hills", BiomeTags.IS_MOUNTAIN),
	JUNGLE("jungle", BiomeTags.IS_JUNGLE),
	MESA("mesa", BiomeTags.IS_BADLANDS),
	PLAINS("plains", Tags.Biomes.IS_PLAINS),
	SAVANNA("savanna", BiomeTags.IS_SAVANNA),
	ICY("icy", Tags.Biomes.IS_SNOWY),
	THEEND("the_end", BiomeTags.IS_END),
	BEACH("beach", BiomeTags.IS_BEACH),
	FOREST("forest", BiomeTags.IS_FOREST),
	OCEAN("ocean", BiomeTags.IS_OCEAN),
	DESERT("desert", Tags.Biomes.IS_DESERT),
	RIVER("river", BiomeTags.IS_RIVER),
	SWAMP("swamp", Tags.Biomes.IS_SWAMP),
	MUSHROOM("mushroom", Tags.Biomes.IS_MUSHROOM),
	NETHER("nether", BiomeTags.IS_NETHER);

	private static final BiomeCategory[] PRIORITY = new BiomeCategory[] {NETHER, THEEND, OCEAN, RIVER, BEACH, MUSHROOM, SWAMP, DESERT, MESA, SAVANNA, JUNGLE, TAIGA, ICY, EXTREME_HILLS, FOREST, PLAINS};

	private final String name;
	@Nullable
	private final TagKey<Biome> tag;

	BiomeCategory(String name, @Nullable TagKey<Biome> tag) {
		this.name = name;
		this.tag = tag;
	}

	public String getName() {
		return this.name;
	}

	public static BiomeCategory of(Holder<Biome> biome) {
		for (BiomeCategory category : PRIORITY) {
			if (biome.is(category.tag))
				return category;
		}

		return NONE;
	}

	@Nullable
	public static BiomeCategory byName(String name) {
		String lower = name.toLowerCase(Locale.ROOT);

		for (BiomeCategory category : values()) {
			if (category.name.equals(lower) || category.name().toLowerCase(Locale.ROOT).equals(lower))
				return category;
		}

		return null;
	}
}
