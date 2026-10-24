package com.deveisia.aoa3.content.world.gen.structure;

import com.deveisia.aoa3.common.registration.worldgen.AoAStructures;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class AoAStructure extends Structure {
	public static final Codec<AoAStructure> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			settingsCodec(builder),
			StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
			Codec.intRange(0, 20).optionalFieldOf("size", 10).forGetter(structure -> structure.maxDepth),
			PlacementType.CODEC.optionalFieldOf("placement", PlacementType.SURFACE).forGetter(structure -> structure.placementType),
			Codec.INT.optionalFieldOf("min_y", 0).forGetter(structure -> structure.minY),
			Codec.INT.optionalFieldOf("max_y", 0).forGetter(structure -> structure.maxY),
			Codec.BOOL.optionalFieldOf("ignore_obstructions", false).forGetter(structure -> structure.ignoreObstructions),
			Codec.BOOL.optionalFieldOf("fixed_rotation", false).forGetter(structure -> structure.fixedRotation),
			BlockPos.CODEC.optionalFieldOf("position").forGetter(structure -> structure.fixedPosition),
			Codec.BOOL.optionalFieldOf("haunted_dungeon_rooms", false).forGetter(structure -> structure.hauntedDungeonRooms)
	).apply(builder, AoAStructure::new));

	private final Holder<StructureTemplatePool> startPool;
	private final int maxDepth;
	private final PlacementType placementType;
	private final int minY;
	private final int maxY;
	private final boolean ignoreObstructions;
	private final boolean fixedRotation;
	private final Optional<BlockPos> fixedPosition;
	private final boolean hauntedDungeonRooms;
	private final AoAJigsawAssembler assembler;

	public AoAStructure(StructureSettings settings, Holder<StructureTemplatePool> startPool, int maxDepth, PlacementType placementType, int minY, int maxY, boolean ignoreObstructions, boolean fixedRotation, Optional<BlockPos> fixedPosition, boolean hauntedDungeonRooms) {
		super(settings);

		this.startPool = startPool;
		this.maxDepth = maxDepth;
		this.placementType = placementType;
		this.minY = Math.min(minY, maxY);
		this.maxY = Math.max(minY, maxY);
		this.ignoreObstructions = ignoreObstructions;
		this.fixedRotation = fixedRotation;
		this.fixedPosition = fixedPosition;
		this.hauntedDungeonRooms = hauntedDungeonRooms;
		this.assembler = hauntedDungeonRooms ? new AoAJigsawAssembler.HauntedDungeon(fixedRotation) : new AoAJigsawAssembler(fixedRotation);
	}

	private int randomY(RandomSource rand) {
		return minY == maxY ? maxY : minY + rand.nextInt(1 + maxY - minY);
	}

	private static boolean isEmpty(BlockState state) {
		return state.isAir() || !state.getFluidState().isEmpty();
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
		ChunkPos chunkPos = context.chunkPos();
		ChunkGenerator generator = context.chunkGenerator();
		LevelHeightAccessor heightAccessor = context.heightAccessor();
		RandomState randomState = context.randomState();
		RandomSource rand = context.random();
		int x = chunkPos.getMinBlockX();
		int z = chunkPos.getMinBlockZ();
		int worldMin = heightAccessor.getMinBuildHeight();
		int worldMax = heightAccessor.getMaxBuildHeight();

		switch (placementType) {
			case SURFACE -> {
				if (generator.getFirstFreeHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState) <= worldMin)
					return Optional.empty();

				return assembler.addPieces(context, startPool, maxDepth, new BlockPos(x, 1, z), Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 128);
			}
			case Y_POS -> {
				int y = randomY(rand);

				if (!ignoreObstructions && !isEmpty(generator.getBaseColumn(x, z, heightAccessor, randomState).getBlock(y)))
					return Optional.empty();

				return place(context, x, y, z);
			}
			case UNDERGROUND_SURFACE -> {
				NoiseColumn column = generator.getBaseColumn(x, z, heightAccessor, randomState);
				int y = randomY(rand);

				if (!isEmpty(column.getBlock(y)))
					return Optional.empty();

				while (y > worldMin && isEmpty(column.getBlock(y - 1))) {
					y--;
				}

				if (y <= worldMin + 1)
					return Optional.empty();

				return place(context, x, y, z);
			}
			case KROR_CAVE -> {
				NoiseColumn column = generator.getBaseColumn(x, z, heightAccessor, randomState);
				int y = randomY(rand);

				if (!isEmpty(column.getBlock(y)))
					return Optional.empty();

				while (y > worldMin + 15 && isEmpty(column.getBlock(y - 1))) {
					y--;
				}

				y -= 14;

				if (y <= worldMin)
					return Optional.empty();

				return place(context, x, y, z);
			}
			case UNDERGROUND_CAVE -> {
				NoiseColumn column = generator.getBaseColumn(x, z, heightAccessor, randomState);
				int y = randomY(rand);

				if (isEmpty(column.getBlock(y))) {
					while (y > worldMin && isEmpty(column.getBlock(y - 1))) {
						y--;
					}

					if (y <= worldMin + 2)
						return place(context, x, randomY(rand), z);
				}
				else {
					while (y < worldMax - 2 && !isEmpty(column.getBlock(y))) {
						y++;
					}

					if (y >= worldMax - 2)
						return place(context, x, randomY(rand), z);
				}

				return place(context, x, y, z);
			}
			case HANGING -> {
				NoiseColumn column = generator.getBaseColumn(x, z, heightAccessor, randomState);
				int y = randomY(rand);

				if (!isEmpty(column.getBlock(y)))
					return Optional.empty();

				while (y < worldMax - 1 && isEmpty(column.getBlock(y + 1))) {
					y++;
				}

				if (y >= worldMax - 1)
					return Optional.empty();

				int ceilingY = y;

				return assembler.addPieces(context, startPool, maxDepth, new BlockPos(x, y + 1, z), Optional.empty(), 128).map(stub -> new GenerationStub(stub.position(), builder -> {
					StructurePiecesBuilder innerBuilder = stub.getPiecesBuilder();

					innerBuilder.offsetPiecesVertically(ceilingY - innerBuilder.getBoundingBox().maxY());
					innerBuilder.build().pieces().forEach(builder::addPiece);
				}));
			}
			case OCEAN_FLOOR -> {
				int centerX = x + 7;
				int centerZ = z + 7;

				return place(context, centerX, generator.getFirstFreeHeight(centerX, centerZ, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState) + 1, centerZ);
			}
			case BURIED -> {
				return place(context, x, generator.getFirstFreeHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState) - randomY(rand), z);
			}
			case ABOVE_SURFACE -> {
				int centerX = x + 7;
				int centerZ = z + 7;
				int y = generator.getFirstFreeHeight(centerX, centerZ, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);

				if (y <= worldMin)
					return Optional.empty();

				return place(context, centerX, y + randomY(rand), centerZ);
			}
			case FIXED -> {
				if (fixedPosition.isEmpty())
					return Optional.empty();

				BlockPos pos = fixedPosition.get();

				if (pos.getX() < chunkPos.getMinBlockX() || pos.getX() > chunkPos.getMaxBlockX() || pos.getZ() < chunkPos.getMinBlockZ() || pos.getZ() > chunkPos.getMaxBlockZ())
					return Optional.empty();

				return place(context, pos.getX(), pos.getY(), pos.getZ());
			}
		}

		return Optional.empty();
	}

	private Optional<GenerationStub> place(GenerationContext context, int x, int y, int z) {
		return assembler.addPieces(context, startPool, maxDepth, new BlockPos(x, y + 1, z), Optional.empty(), 128);
	}

	@Override
	public StructureType<?> type() {
		return AoAStructures.AOA_STRUCTURE.get();
	}

	public enum PlacementType implements StringRepresentable {
		SURFACE("surface"),
		Y_POS("y_pos"),
		UNDERGROUND_SURFACE("underground_surface"),
		UNDERGROUND_CAVE("underground_cave"),
		KROR_CAVE("kror_cave"),
		HANGING("hanging"),
		OCEAN_FLOOR("ocean_floor"),
		BURIED("buried"),
		ABOVE_SURFACE("above_surface"),
		FIXED("fixed");

		public static final Codec<PlacementType> CODEC = StringRepresentable.fromEnum(PlacementType::values);

		private final String name;

		PlacementType(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return name;
		}
	}
}
