package com.deveisia.aoa3.util;

import com.deveisia.aoa3.library.compat.HarvestData;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 3600000f;

	public static class CompactProperties {
		private final BlockBehaviour.Properties properties;

		public CompactProperties(Material material, Function<BlockState, MapColor> mapColours) {
			this.properties = material.apply(BlockBehaviour.Properties.of().mapColor(mapColours));
		}

		public CompactProperties(Material material, MapColor mapColour) {
			this.properties = material.properties(mapColour);
		}

		public CompactProperties harvestTool(ToolType tool, int harvestLevel) {
			harvestLevel(harvestLevel);

			return harvestTool(tool);
		}

		public CompactProperties harvestTool(ToolType tool) {
			this.properties.requiresCorrectToolForDrops();

			return tool(tool);
		}

		public CompactProperties tool(ToolType tool) {
			HarvestData.setTool(this.properties, tool);

			return this;
		}

		public CompactProperties harvestLevel(int level) {
			HarvestData.setLevel(this.properties, level);

			return this;
		}

		public CompactProperties sound(SoundType sound) {
			this.properties.sound(sound);

			return this;
		}

		public CompactProperties stats(float hardness) {
			return stats(hardness, hardness);
		}

		public CompactProperties stats(float hardness, float resistance) {
			this.properties.strength(hardness, resistance);

			return this;
		}

		public CompactProperties unbreakable() {
			noDrops();

			return stats(UNBREAKABLE_HARDNESS, UNBREAKABLE_RESISTANCE);
		}

		public CompactProperties light(int light) {
			return light(state -> light);
		}

		public CompactProperties light(ToIntFunction<BlockState> light) {
			this.properties.lightLevel(light);

			return this;
		}

		public CompactProperties randomTicks() {
			this.properties.randomTicks();

			return this;
		}

		public CompactProperties isAir() {
			this.properties.air();

			return this;
		}

		public CompactProperties dynamicShape() {
			this.properties.dynamicShape();

			return this;
		}

		public CompactProperties slippery(float slipMod) {
			this.properties.friction(slipMod);

			return this;
		}

		public CompactProperties emissive() {
			return emissive((state, world, pos) -> true);
		}

		public CompactProperties emissive(BlockBehaviour.StatePredicate when) {
			this.properties.emissiveRendering(when);

			return this;
		}

		public CompactProperties renderAdjust() {
			return renderAdjust((state, world, pos) -> true);
		}

		public CompactProperties renderAdjust(BlockBehaviour.StatePredicate when) {
			this.properties.hasPostProcess(when);

			return this;
		}

		public CompactProperties moveSpeed(float speedMod) {
			this.properties.speedFactor(speedMod);

			return this;
		}

		public CompactProperties bouncy(float jumpFactor) {
			this.properties.jumpFactor(jumpFactor);

			return this;
		}

		public CompactProperties noDrops() {
			this.properties.noLootTable();

			return this;
		}

		public CompactProperties noOcclusion() {
			this.properties.noOcclusion();

			return this;
		}

		public CompactProperties noClip() {
			this.properties.noCollission();
			coversScreen((state, world, pos) -> false);

			return this;
		}

		public CompactProperties noScreenCover() {
			return coversScreen((state, world, pos) -> false);
		}

		public CompactProperties coversScreen(BlockBehaviour.StatePredicate when) {
			this.properties.isViewBlocking(when);

			return this;
		}

		public CompactProperties noRedstone() {
			return conductRedstone((state, world, pos) -> false);
		}

		public CompactProperties conductRedstone(BlockBehaviour.StatePredicate when) {
			this.properties.isRedstoneConductor(when);

			return this;
		}

		public CompactProperties noSpawns() {
			return specialSpawns((state, world, pos, entityType) -> false);
		}

		public CompactProperties specialSpawns(BlockBehaviour.StateArgumentPredicate<EntityType<?>> when) {
			this.properties.isValidSpawn(when);

			return this;
		}

		public CompactProperties breathable() {
			return suffocate((state, world, pos) -> false);
		}

		public CompactProperties suffocate(BlockBehaviour.StatePredicate when) {
			this.properties.isSuffocating(when);

			return this;
		}

		public BlockBehaviour.Properties get() {
			return this.properties;
		}
	}

	public static Vec3 posToVec(BlockPos pos) {
		return new Vec3(pos.getX(), pos.getY(), pos.getZ());
	}

	@SafeVarargs
	public static boolean isBlockTaggedAs(Block block, TagKey<Block>... tags) {
		for (TagKey<Block> tag : tags) {
			if (block.defaultBlockState().is(tag))
				return true;
		}

		return false;
	}

	public static boolean isAirBlock(BlockState state) {
		return state.isAir();
	}

	public static class SpawnerBuilder {
		private short initialSpawnDelay = 20;
		private short minSpawnDelay = 200;
		private short maxSpawnDelay = 800;

		private short spawnsPerGroup = 4;
		private short maxNearbyEntities = 6;
		private short requiredPlayerRange = 16;
		private short spawnRange = 4;

		private final ArrayList<Pair<Integer, CompoundTag>> mobs = new ArrayList<>(1);

		public SpawnerBuilder initialSpawnDelay(int ticks) {
			this.initialSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder minSpawnDelay(int ticks) {
			this.minSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder maxSpawnDelay(int ticks) {
			this.maxSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder spawnsPerGroup(int amount) {
			this.spawnsPerGroup = (short)amount;

			return this;
		}

		public SpawnerBuilder maxNearbyEntities(int amount) {
			this.maxNearbyEntities = (short)amount;

			return this;
		}

		public SpawnerBuilder whenPlayerWithin(int distance) {
			this.requiredPlayerRange = (short)distance;

			return this;
		}

		public SpawnerBuilder spawnWithinXBlocks(int distance) {
			this.spawnRange = (short)distance;

			return this;
		}

		public SpawnerBuilder withSpawns(EntityType<?>... entities) {
			for (EntityType<?> entity : entities) {
				withSpawn(1, entity);
			}

			return this;
		}

		@SafeVarargs
		public final SpawnerBuilder withSpawns(RegistryObject<? extends EntityType<?>>... entities) {
			for (RegistryObject<? extends EntityType<?>> registryEntry : entities) {
				withSpawn(1, registryEntry.get());
			}

			return this;
		}

		public SpawnerBuilder withSpawn(int weight, EntityType<?> entity) {
			CompoundTag tag = new CompoundTag();

			tag.putString("id", ForgeRegistries.ENTITY_TYPES.getKey(entity).toString());

			return withSpawn(weight, tag);
		}

		public SpawnerBuilder withSpawn(int weight, CompoundTag nbt) {
			this.mobs.add(Pair.of(weight, nbt));

			return this;
		}

		public CompoundTag build() {
			CompoundTag nbt = new CompoundTag();

			if (this.mobs.isEmpty())
				throw new IllegalStateException("Attempted to create spawner data with no mobs listed.");

			nbt.putShort("Delay", this.initialSpawnDelay);
			nbt.putShort("MinSpawnDelay", this.minSpawnDelay);
			nbt.putShort("MaxSpawnDelay", this.maxSpawnDelay);
			nbt.putShort("SpawnCount", this.spawnsPerGroup);
			nbt.putShort("MaxNearbyEntities", this.maxNearbyEntities);
			nbt.putShort("RequiredPlayerRange", this.requiredPlayerRange);
			nbt.putShort("SpawnRange", this.spawnRange);

			CompoundTag firstSpawn = new CompoundTag();

			firstSpawn.put("entity", this.mobs.get(0).getSecond().copy());
			nbt.put("SpawnData", firstSpawn);

			ListTag potentials = new ListTag();

			for (Pair<Integer, CompoundTag> entry : this.mobs) {
				CompoundTag potential = new CompoundTag();
				CompoundTag data = new CompoundTag();

				data.put("entity", entry.getSecond().copy());
				potential.putInt("weight", entry.getFirst());
				potential.put("data", data);
				potentials.add(potential);
			}

			nbt.put("SpawnPotentials", potentials);

			return nbt;
		}
	}

	public static VoxelShape pixelBasedCube(int minPixelX, int minPixelY, int minPixelZ, int maxPixelX, int maxPixelY, int maxPixelZ) {
		return Shapes.create(new AABB(minPixelX / 16d, minPixelY / 16d, minPixelZ / 16d, maxPixelX / 16d, maxPixelY / 16d, maxPixelZ / 16d));
	}

	public static boolean canPlayerHarvest(BlockState state, Player player, BlockGetter world, BlockPos pos) {
		if (!state.requiresCorrectToolForDrops())
			return true;

		return player.getMainHandItem().isCorrectToolForDrops(state);
	}
}
