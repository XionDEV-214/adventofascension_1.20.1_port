package com.deveisia.aoa3.library.compat;

import com.deveisia.aoa3.mixin.common.accessor.BlockBehaviourAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class Material {
	private static final Map<BlockBehaviour.Properties, Material> PROPERTIES_MATERIALS = Collections.synchronizedMap(new IdentityHashMap<>());

	public static final Material AIR = new Material(MapColor.NONE, SoundType.STONE).replaceable().nonSolid().destroyOnPush();
	public static final Material STRUCTURAL_AIR = new Material(MapColor.NONE, SoundType.STONE).replaceable().nonSolid().destroyOnPush();
	public static final Material PORTAL = new Material(MapColor.NONE, SoundType.GLASS).nonSolid().blockOnPush();
	public static final Material CLOTH_DECORATION = new Material(MapColor.WOOL, SoundType.WOOL).flammable().nonSolid();
	public static final Material PLANT = new Material(MapColor.PLANT, SoundType.GRASS).nonSolid().destroyOnPush();
	public static final Material WATER_PLANT = new Material(MapColor.WATER, SoundType.WET_GRASS).nonSolid().destroyOnPush();
	public static final Material REPLACEABLE_PLANT = new Material(MapColor.PLANT, SoundType.GRASS).replaceable().flammable().nonSolid().destroyOnPush();
	public static final Material REPLACEABLE_FIREPROOF_PLANT = new Material(MapColor.PLANT, SoundType.GRASS).replaceable().nonSolid().destroyOnPush();
	public static final Material REPLACEABLE_WATER_PLANT = new Material(MapColor.WATER, SoundType.WET_GRASS).replaceable().nonSolid().destroyOnPush();
	public static final Material WATER = new Material(MapColor.WATER, SoundType.EMPTY).replaceable().nonSolid().liquid().destroyOnPush();
	public static final Material BUBBLE_COLUMN = new Material(MapColor.WATER, SoundType.EMPTY).replaceable().nonSolid().liquid().destroyOnPush();
	public static final Material LAVA = new Material(MapColor.FIRE, SoundType.EMPTY).replaceable().nonSolid().liquid().destroyOnPush();
	public static final Material TOP_SNOW = new Material(MapColor.SNOW, SoundType.SNOW).replaceable().nonSolid().destroyOnPush();
	public static final Material FIRE = new Material(MapColor.NONE, SoundType.WOOL).replaceable().nonSolid().destroyOnPush();
	public static final Material DECORATION = new Material(MapColor.NONE, SoundType.STONE).nonSolid().destroyOnPush();
	public static final Material WEB = new Material(MapColor.WOOL, SoundType.STONE).nonSolid().destroyOnPush();
	public static final Material SCULK = new Material(MapColor.COLOR_BLACK, SoundType.SCULK);
	public static final Material BUILDABLE_GLASS = new Material(MapColor.NONE, SoundType.GLASS);
	public static final Material CLAY = new Material(MapColor.CLAY, SoundType.GRAVEL);
	public static final Material DIRT = new Material(MapColor.DIRT, SoundType.GRAVEL);
	public static final Material GRASS = new Material(MapColor.GRASS, SoundType.GRASS);
	public static final Material ICE_SOLID = new Material(MapColor.ICE, SoundType.GLASS);
	public static final Material SAND = new Material(MapColor.SAND, SoundType.SAND);
	public static final Material SPONGE = new Material(MapColor.COLOR_YELLOW, SoundType.GRASS);
	public static final Material SHULKER_SHELL = new Material(MapColor.COLOR_PURPLE, SoundType.STONE);
	public static final Material WOOD = new Material(MapColor.WOOD, SoundType.WOOD).flammable();
	public static final Material NETHER_WOOD = new Material(MapColor.WOOD, SoundType.NETHER_WOOD);
	public static final Material BAMBOO_SAPLING = new Material(MapColor.WOOD, SoundType.BAMBOO_SAPLING).flammable().destroyOnPush();
	public static final Material BAMBOO = new Material(MapColor.WOOD, SoundType.BAMBOO).flammable().destroyOnPush();
	public static final Material WOOL = new Material(MapColor.WOOL, SoundType.WOOL).flammable();
	public static final Material EXPLOSIVE = new Material(MapColor.FIRE, SoundType.GRASS).flammable();
	public static final Material LEAVES = new Material(MapColor.PLANT, SoundType.GRASS).flammable().destroyOnPush();
	public static final Material GLASS = new Material(MapColor.NONE, SoundType.GLASS);
	public static final Material ICE = new Material(MapColor.ICE, SoundType.GLASS);
	public static final Material CACTUS = new Material(MapColor.PLANT, SoundType.WOOL).destroyOnPush();
	public static final Material STONE = new Material(MapColor.STONE, SoundType.STONE);
	public static final Material METAL = new Material(MapColor.METAL, SoundType.METAL);
	public static final Material SNOW = new Material(MapColor.SNOW, SoundType.SNOW);
	public static final Material HEAVY_METAL = new Material(MapColor.METAL, SoundType.ANVIL).blockOnPush();
	public static final Material BARRIER = new Material(MapColor.NONE, SoundType.STONE).blockOnPush();
	public static final Material PISTON = new Material(MapColor.STONE, SoundType.STONE).blockOnPush();
	public static final Material MOSS = new Material(MapColor.PLANT, SoundType.MOSS).destroyOnPush();
	public static final Material VEGETABLE = new Material(MapColor.PLANT, SoundType.WOOD).destroyOnPush();
	public static final Material EGG = new Material(MapColor.PLANT, SoundType.STONE).destroyOnPush();
	public static final Material CAKE = new Material(MapColor.NONE, SoundType.WOOL).destroyOnPush();
	public static final Material AMETHYST = new Material(MapColor.COLOR_PURPLE, SoundType.AMETHYST);
	public static final Material POWDER_SNOW = new Material(MapColor.SNOW, SoundType.POWDER_SNOW).nonSolid();
	public static final Material FROGSPAWN = new Material(MapColor.WATER, SoundType.FROGSPAWN).nonSolid().destroyOnPush();
	public static final Material FROGLIGHT = new Material(MapColor.NONE, SoundType.FROGLIGHT);
	public static final Material DECORATED_POT = new Material(MapColor.TERRACOTTA_RED, SoundType.DECORATED_POT).destroyOnPush();

	private final MapColor colour;
	private final SoundType sound;
	private boolean replaceable = false;
	private boolean flammable = false;
	private boolean solid = true;
	private boolean liquid = false;
	private PushReaction pushReaction = PushReaction.NORMAL;

	private Material(MapColor colour, SoundType sound) {
		this.colour = colour;
		this.sound = sound;
	}

	private Material replaceable() {
		this.replaceable = true;

		return this;
	}

	private Material flammable() {
		this.flammable = true;

		return this;
	}

	private Material nonSolid() {
		this.solid = false;

		return this;
	}

	private Material liquid() {
		this.liquid = true;

		return this;
	}

	private Material destroyOnPush() {
		this.pushReaction = PushReaction.DESTROY;

		return this;
	}

	private Material blockOnPush() {
		this.pushReaction = PushReaction.BLOCK;

		return this;
	}

	public MapColor getColor() {
		return this.colour;
	}

	public SoundType getSound() {
		return this.sound;
	}

	public boolean isReplaceable() {
		return this.replaceable;
	}

	public boolean isFlammable() {
		return this.flammable;
	}

	public boolean isSolid() {
		return this.solid;
	}

	public boolean isLiquid() {
		return this.liquid;
	}

	public PushReaction getPushReaction() {
		return this.pushReaction;
	}

	public boolean blocksMotion() {
		return this.solid && !this.liquid;
	}

	public static Material of(BlockState state) {
		if (state.isAir())
			return AIR;

		Material known = PROPERTIES_MATERIALS.get(((BlockBehaviourAccessor)state.getBlock()).getProperties());

		if (known != null)
			return known;

		FluidState fluid = state.getFluidState();

		if (!fluid.isEmpty() && state.liquid())
			return fluid.is(FluidTags.LAVA) ? LAVA : WATER;

		if (state.is(BlockTags.LEAVES))
			return LEAVES;

		if (state.is(BlockTags.LOGS) || state.is(BlockTags.PLANKS) || state.is(BlockTags.MINEABLE_WITH_AXE))
			return WOOD;

		if (state.is(BlockTags.SAND))
			return SAND;

		if (state.is(BlockTags.SNOW))
			return SNOW;

		if (state.is(BlockTags.ICE))
			return ICE;

		if (state.is(BlockTags.WOOL))
			return WOOL;

		if (state.is(BlockTags.DIRT))
			return state.getSoundType() == SoundType.GRASS ? GRASS : DIRT;

		if (state.getBlock() instanceof BushBlock)
			return state.canBeReplaced() ? REPLACEABLE_PLANT : PLANT;

		SoundType sound = state.getSoundType();

		if (sound == SoundType.GLASS)
			return GLASS;

		if (sound == SoundType.METAL)
			return METAL;

		if (sound == SoundType.GRASS)
			return state.canBeReplaced() ? REPLACEABLE_PLANT : PLANT;

		return STONE;
	}

	public static Material ofProperties(BlockBehaviour.Properties properties) {
		return PROPERTIES_MATERIALS.get(properties);
	}

	public static void track(BlockBehaviour.Properties properties, Material material) {
		PROPERTIES_MATERIALS.put(properties, material);
	}

	public BlockBehaviour.Properties apply(BlockBehaviour.Properties properties) {
		track(properties, this);

		properties.sound(this.sound).pushReaction(this.pushReaction);

		if (this.replaceable)
			properties.replaceable();

		if (this.flammable)
			properties.ignitedByLava();

		if (this.liquid)
			properties.liquid();

		if (!this.solid)
			properties.forceSolidOff();

		return properties;
	}

	public BlockBehaviour.Properties properties() {
		return apply(BlockBehaviour.Properties.of().mapColor(this.colour));
	}

	public BlockBehaviour.Properties properties(MapColor colour) {
		return apply(BlockBehaviour.Properties.of().mapColor(colour));
	}
}
