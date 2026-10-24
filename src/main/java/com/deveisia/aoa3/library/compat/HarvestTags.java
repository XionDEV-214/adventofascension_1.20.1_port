package com.deveisia.aoa3.library.compat;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.mixin.common.accessor.BlockBehaviourAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HarvestTags {
	private static final String SOURCE = AdventOfAscension.MOD_ID + " harvest data";
	private static final ResourceLocation PICKAXE = ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/pickaxe");
	private static final ResourceLocation AXE = ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/axe");
	private static final ResourceLocation SHOVEL = ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/shovel");
	private static final ResourceLocation HOE = ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/hoe");
	private static final ResourceLocation STONE_TOOL = ResourceLocation.fromNamespaceAndPath("minecraft", "needs_stone_tool");
	private static final ResourceLocation IRON_TOOL = ResourceLocation.fromNamespaceAndPath("minecraft", "needs_iron_tool");
	private static final ResourceLocation DIAMOND_TOOL = ResourceLocation.fromNamespaceAndPath("minecraft", "needs_diamond_tool");
	private static final ResourceLocation NETHERITE_TOOL = ResourceLocation.fromNamespaceAndPath("forge", "needs_netherite_tool");

	public static void inject(Map<ResourceLocation, List<TagLoader.EntryWithSource>> tags) {
		Set<ResourceLocation> alreadyMineable = new HashSet<>();

		for (ResourceLocation tag : new ResourceLocation[] {PICKAXE, AXE, SHOVEL, HOE}) {
			List<TagLoader.EntryWithSource> entries = tags.get(tag);

			if (entries == null)
				continue;

			for (TagLoader.EntryWithSource entry : entries) {
				String raw = entry.entry().toString();

				if (!raw.startsWith("#"))
					alreadyMineable.add(ResourceLocation.tryParse(raw.endsWith("?") ? raw.substring(0, raw.length() - 1) : raw));
			}
		}

		for (Map.Entry<net.minecraft.resources.ResourceKey<Block>, Block> registryEntry : ForgeRegistries.BLOCKS.getEntries()) {
			ResourceLocation id = registryEntry.getKey().location();

			if (!id.getNamespace().equals(AdventOfAscension.MOD_ID))
				continue;

			BlockBehaviour.Properties properties = ((BlockBehaviourAccessor)registryEntry.getValue()).getProperties();
			ToolType tool = HarvestData.getTool(properties);
			ResourceLocation mineableTag = tool != null ? tagFor(tool) : null;

			if (mineableTag == null && !alreadyMineable.contains(id))
				mineableTag = tagFor(Material.ofProperties(properties));

			if (mineableTag != null)
				add(tags, mineableTag, id);

			ResourceLocation levelTag = levelTag(HarvestData.getLevel(properties));

			if (levelTag != null)
				add(tags, levelTag, id);
		}
	}

	private static void add(Map<ResourceLocation, List<TagLoader.EntryWithSource>> tags, ResourceLocation tag, ResourceLocation id) {
		tags.computeIfAbsent(tag, key -> new ArrayList<>()).add(new TagLoader.EntryWithSource(TagEntry.element(id), SOURCE));
	}

	private static ResourceLocation tagFor(ToolType tool) {
		if (tool == ToolType.PICKAXE)
			return PICKAXE;

		if (tool == ToolType.AXE)
			return AXE;

		if (tool == ToolType.SHOVEL)
			return SHOVEL;

		if (tool == ToolType.HOE)
			return HOE;

		return null;
	}

	private static ResourceLocation tagFor(Material material) {
		if (material == null)
			return null;

		if (material == Material.STONE || material == Material.METAL || material == Material.HEAVY_METAL || material == Material.ICE || material == Material.ICE_SOLID || material == Material.AMETHYST || material == Material.PISTON || material == Material.SHULKER_SHELL)
			return PICKAXE;

		if (material == Material.WOOD || material == Material.NETHER_WOOD || material == Material.BAMBOO || material == Material.VEGETABLE)
			return AXE;

		if (material == Material.DIRT || material == Material.GRASS || material == Material.SAND || material == Material.CLAY || material == Material.SNOW || material == Material.TOP_SNOW)
			return SHOVEL;

		if (material == Material.LEAVES || material == Material.SPONGE || material == Material.MOSS)
			return HOE;

		return null;
	}

	private static ResourceLocation levelTag(int level) {
		return switch (level) {
			case 0 -> null;
			case 1 -> STONE_TOOL;
			case 2 -> IRON_TOOL;
			case 3 -> DIAMOND_TOOL;
			default -> level > 3 ? NETHERITE_TOOL : null;
		};
	}
}
