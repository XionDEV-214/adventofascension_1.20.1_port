package com.deveisia.aoa3.library.compat;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ToolType {
	private static final Map<String, ToolType> VALUES = new ConcurrentHashMap<>();

	public static final ToolType PICKAXE = get("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE);
	public static final ToolType AXE = get("axe", BlockTags.MINEABLE_WITH_AXE);
	public static final ToolType SHOVEL = get("shovel", BlockTags.MINEABLE_WITH_SHOVEL);
	public static final ToolType HOE = get("hoe", BlockTags.MINEABLE_WITH_HOE);
	public static final ToolType SWORD = get("sword", BlockTags.SWORD_EFFICIENT);

	private final String name;
	private final TagKey<Block> mineableTag;

	private ToolType(String name, TagKey<Block> mineableTag) {
		this.name = name;
		this.mineableTag = mineableTag;
	}

	private static ToolType get(String name, TagKey<Block> tag) {
		return VALUES.computeIfAbsent(name, key -> new ToolType(key, tag));
	}

	public static ToolType get(String name) {
		return VALUES.computeIfAbsent(name, key -> new ToolType(key, null));
	}

	public String getName() {
		return this.name;
	}

	public TagKey<Block> getMineableTag() {
		return this.mineableTag;
	}

	public boolean isEffectiveOn(BlockState state) {
		return this.mineableTag != null && state.is(this.mineableTag);
	}

	public static ToolType forState(BlockState state) {
		for (ToolType type : VALUES.values()) {
			if (type.isEffectiveOn(state))
				return type;
		}

		return null;
	}

	public static int harvestLevelOf(BlockState state) {
		if (state.is(BlockTags.NEEDS_DIAMOND_TOOL))
			return 3;

		if (state.is(BlockTags.NEEDS_IRON_TOOL))
			return 2;

		if (state.is(BlockTags.NEEDS_STONE_TOOL))
			return 1;

		return 0;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
