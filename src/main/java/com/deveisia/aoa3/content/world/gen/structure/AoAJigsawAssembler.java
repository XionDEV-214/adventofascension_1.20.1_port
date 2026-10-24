package com.deveisia.aoa3.content.world.gen.structure;

import com.deveisia.aoa3.advent.Logging;
import com.google.common.collect.Queues;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class AoAJigsawAssembler {
	private final boolean ignoreRotations;

	public AoAJigsawAssembler(boolean ignoreRotations) {
		this.ignoreRotations = ignoreRotations;
	}

	@Nullable
	protected Pair<StructureTemplatePool, StructureTemplatePool> getPools(Registry<StructureTemplatePool> pools, ResourceLocation poolPath, int minY, RandomSource rand) {
		Optional<StructureTemplatePool> pool = pools.getOptional(poolPath);

		if (pool.isEmpty() || (pool.get().size() == 0 && !poolPath.equals(Pools.EMPTY.location()))) {
			Logging.logMessage(Level.WARN, "Empty or non-existent pool: " + poolPath);

			return null;
		}

		Holder<StructureTemplatePool> fallback = pool.get().getFallback();

		if (fallback.value().size() == 0 && !fallback.is(Pools.EMPTY)) {
			Logging.logMessage(Level.WARN, "Empty or non-existent fallback pool: " + fallback.unwrapKey().map(key -> key.location().toString()).orElse("unknown"));

			return null;
		}

		return Pair.of(pool.get(), fallback.value());
	}

	public Optional<Structure.GenerationStub> addPieces(Structure.GenerationContext genContext, Holder<StructureTemplatePool> templatePoolHolder, int maxPieces, BlockPos startPos, Optional<Heightmap.Types> heightmap, int maxRadius) {
		StructureTemplateManager templateManager = genContext.structureTemplateManager();
		WorldgenRandom rand = genContext.random();
		Rotation rotation = ignoreRotations ? Rotation.NONE : Rotation.getRandom(rand);
		StructurePoolElement poolElement = templatePoolHolder.value().getRandomTemplate(rand);

		if (poolElement == EmptyPoolElement.INSTANCE)
			return Optional.empty();

		PoolElementStructurePiece startPiece = new PoolElementStructurePiece(templateManager, poolElement, startPos, poolElement.getGroundLevelDelta(), rotation, poolElement.getBoundingBox(templateManager, startPos, rotation));
		BoundingBox startPieceBounds = startPiece.getBoundingBox();
		int structurePosX = (startPieceBounds.minX() + startPieceBounds.maxX()) / 2;
		int structurePosZ = (startPieceBounds.minZ() + startPieceBounds.maxZ()) / 2;
		int startY = startPos.getY();

		if (heightmap.isPresent())
			startY = startPos.getY() + genContext.chunkGenerator().getFirstFreeHeight(structurePosX, structurePosZ, heightmap.get(), genContext.heightAccessor(), genContext.randomState());

		startPiece.move(0, startY - (startPieceBounds.minY() + startPiece.getGroundLevelDelta()), 0);

		BoundingBox movedBounds = startPiece.getBoundingBox();

		return Optional.of(new Structure.GenerationStub(new BlockPos(structurePosX, startY, structurePosZ), pieceBuilder -> {
			List<PoolElementStructurePiece> pieces = new ObjectArrayList<>();

			pieces.add(startPiece);

			if (maxPieces > 0) {
				addPieces(genContext.randomState(), maxPieces, genContext.chunkGenerator(), templateManager, genContext.heightAccessor(), rand, genContext.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL), startPiece, pieces,
						Shapes.join(Shapes.create(new AABB(structurePosX - maxRadius, -4000, structurePosZ - maxRadius, structurePosX + maxRadius + 1, 4000, structurePosZ + maxRadius + 1)), Shapes.create(AABB.of(movedBounds)), BooleanOp.ONLY_FIRST));
			}

			pieces.forEach(pieceBuilder::addPiece);
		}));
	}

	protected void addPieces(RandomState genState, int maxPieces, ChunkGenerator chunkGen, StructureTemplateManager templateManager, LevelHeightAccessor heightAccessor, RandomSource rand, Registry<StructureTemplatePool> templatePools, PoolElementStructurePiece parentPiece, List<PoolElementStructurePiece> childPieces, VoxelShape bounds) {
		PiecePlacer piecePlacer = new PiecePlacer(templatePools, maxPieces, chunkGen, templateManager, childPieces, rand);

		piecePlacer.placing.addLast(new PieceState(parentPiece, new MutableObject<>(bounds), 0));

		while (!piecePlacer.placing.isEmpty()) {
			PieceState pieceState = piecePlacer.placing.removeFirst();

			piecePlacer.tryPlacingChildren(pieceState.piece, pieceState.bounds, pieceState.depth, heightAccessor, genState);
		}
	}

	protected static final class PieceState {
		private final PoolElementStructurePiece piece;
		private final MutableObject<VoxelShape> bounds;
		private final int depth;

		PieceState(PoolElementStructurePiece piece, MutableObject<VoxelShape> bounds, int pieceDepth) {
			this.piece = piece;
			this.bounds = bounds;
			this.depth = pieceDepth;
		}
	}

	protected final class PiecePlacer {
		private final Registry<StructureTemplatePool> pools;
		private final int maxPieces;
		private final ChunkGenerator chunkGenerator;
		private final StructureTemplateManager structureTemplateManager;
		private final List<? super PoolElementStructurePiece> pieces;
		private final RandomSource random;
		final Deque<PieceState> placing = Queues.newArrayDeque();

		private PiecePlacer(Registry<StructureTemplatePool> templatePoolRegistry, int maxPieces, ChunkGenerator chunkGen, StructureTemplateManager templateManager, List<? super PoolElementStructurePiece> pieces, RandomSource rand) {
			this.pools = templatePoolRegistry;
			this.maxPieces = maxPieces;
			this.chunkGenerator = chunkGen;
			this.structureTemplateManager = templateManager;
			this.pieces = pieces;
			this.random = rand;
		}

		void tryPlacingChildren(PoolElementStructurePiece parentPiece, MutableObject<VoxelShape> bounds, int pieceDepth, LevelHeightAccessor heightAccessor, RandomState genState) {
			StructurePoolElement poolElement = parentPiece.getElement();
			StructureTemplatePool.Projection projection = poolElement.getProjection();
			MutableObject<VoxelShape> shape = new MutableObject<>();
			BoundingBox parentBounds = parentPiece.getBoundingBox();
			int minY = parentBounds.minY();

			for (StructureTemplate.StructureBlockInfo jigsawBlockInfo : poolElement.getShuffledJigsawBlocks(this.structureTemplateManager, parentPiece.getPosition(), parentPiece.getRotation(), this.random)) {
				BlockPos jigsawPos = jigsawBlockInfo.pos();
				BlockPos jigsawFacingPos = jigsawPos.relative(JigsawBlock.getFrontFacing(jigsawBlockInfo.state()));
				ResourceLocation poolPath = ResourceLocation.parse(jigsawBlockInfo.nbt().getString("pool"));
				Pair<StructureTemplatePool, StructureTemplatePool> poolPair = getPools(this.pools, poolPath, minY, this.random);

				if (poolPair == null)
					continue;

				MutableObject<VoxelShape> placementBounds;

				if (parentBounds.isInside(jigsawFacingPos)) {
					placementBounds = shape;

					if (shape.getValue() == null)
						shape.setValue(Shapes.create(AABB.of(parentBounds)));
				}
				else {
					placementBounds = bounds;
				}

				List<StructurePoolElement> piecesToGen = new ObjectArrayList<>();

				if (pieceDepth != this.maxPieces)
					piecesToGen.addAll(poolPair.getFirst().getShuffledTemplates(this.random));

				piecesToGen.addAll(poolPair.getSecond().getShuffledTemplates(this.random));

				placeChildren(jigsawBlockInfo, heightAccessor, genState, parentPiece, jigsawFacingPos, placementBounds, jigsawPos.getY() - minY, projection == StructureTemplatePool.Projection.RIGID, minY, pieceDepth, projection, jigsawPos, piecesToGen);
			}
		}

		private void placeChildren(StructureTemplate.StructureBlockInfo jigsawBlockInfo, LevelHeightAccessor heightAccessor, RandomState genState, PoolElementStructurePiece parentPiece, BlockPos jigsawFacingPos, MutableObject<VoxelShape> placementBounds, int jigsawPosDelta, boolean isRigid, int minY, int pieceDepth, StructureTemplatePool.Projection projection, BlockPos jigsawPos, List<StructurePoolElement> piecesToGen) {
			int terrainMatchDelta = -1;

			for (StructurePoolElement childPoolElement : piecesToGen) {
				if (childPoolElement == EmptyPoolElement.INSTANCE)
					return;

				for (Rotation pieceRotation : Rotation.getShuffled(this.random)) {
					List<StructureTemplate.StructureBlockInfo> childPieceJigsawBlocks = childPoolElement.getShuffledJigsawBlocks(this.structureTemplateManager, BlockPos.ZERO, pieceRotation, this.random);

					for (StructureTemplate.StructureBlockInfo childPieceJigsawBlock : childPieceJigsawBlocks) {
						if (JigsawBlock.canAttach(jigsawBlockInfo, childPieceJigsawBlock)) {
							BlockPos childPieceJigsawPos = childPieceJigsawBlock.pos();
							BlockPos jigsawToPieceDelta = jigsawFacingPos.subtract(childPieceJigsawPos);
							BoundingBox childBounds = childPoolElement.getBoundingBox(this.structureTemplateManager, jigsawToPieceDelta, pieceRotation);
							StructureTemplatePool.Projection childProjection = childPoolElement.getProjection();
							boolean childIsRigid = childProjection == StructureTemplatePool.Projection.RIGID;
							int childJigsawYPos = childPieceJigsawPos.getY();
							int deltaY = jigsawPosDelta - childJigsawYPos + JigsawBlock.getFrontFacing(jigsawBlockInfo.state()).getStepY();
							int placementYOffset = minY + deltaY;

							if (!isRigid || !childIsRigid) {
								if (terrainMatchDelta == -1)
									terrainMatchDelta = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, genState);

								placementYOffset = terrainMatchDelta - childJigsawYPos;
							}

							int childBoundsOffset = placementYOffset - childBounds.minY();
							childBounds = childBounds.moved(0, childBoundsOffset, 0);
							BlockPos childPos = jigsawToPieceDelta.offset(0, childBoundsOffset, 0);

							if (!Shapes.joinIsNotEmpty(placementBounds.getValue(), Shapes.create(AABB.of(childBounds).deflate(0.25D)), BooleanOp.ONLY_SECOND)) {
								placementBounds.setValue(Shapes.joinUnoptimized(placementBounds.getValue(), Shapes.create(AABB.of(childBounds)), BooleanOp.ONLY_FIRST));

								int parentGroundDelta = parentPiece.getGroundLevelDelta();
								int groundLevelDelta = childPoolElement.getGroundLevelDelta();

								if (childIsRigid)
									groundLevelDelta = parentGroundDelta - deltaY;

								PoolElementStructurePiece childPiece = new PoolElementStructurePiece(this.structureTemplateManager, childPoolElement, childPos, groundLevelDelta, pieceRotation, childBounds);
								int childBaseY;

								if (isRigid) {
									childBaseY = minY + jigsawPosDelta;
								}
								else if (childIsRigid) {
									childBaseY = placementYOffset + childJigsawYPos;
								}
								else {
									if (terrainMatchDelta == -1)
										terrainMatchDelta = this.chunkGenerator.getFirstFreeHeight(jigsawPos.getX(), jigsawPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, genState);

									childBaseY = terrainMatchDelta + deltaY / 2;
								}

								parentPiece.addJunction(new JigsawJunction(jigsawFacingPos.getX(), childBaseY - jigsawPosDelta + parentGroundDelta, jigsawFacingPos.getZ(), deltaY, childProjection));
								childPiece.addJunction(new JigsawJunction(jigsawPos.getX(), childBaseY - childJigsawYPos + groundLevelDelta, jigsawPos.getZ(), -deltaY, projection));
								this.pieces.add(childPiece);

								if (pieceDepth + 1 <= this.maxPieces)
									this.placing.addLast(new PieceState(childPiece, placementBounds, pieceDepth + 1));

								return;
							}
						}
					}
				}
			}
		}
	}

	public static class HauntedDungeon extends AoAJigsawAssembler {
		public HauntedDungeon(boolean ignoreRotations) {
			super(ignoreRotations);
		}

		@Nullable
		@Override
		protected Pair<StructureTemplatePool, StructureTemplatePool> getPools(Registry<StructureTemplatePool> pools, ResourceLocation basePool, int minY, RandomSource rand) {
			if (minY > 60 || basePool.getPath().contains("large_rooms"))
				return super.getPools(pools, basePool, minY, rand);

			if (minY <= 15)
				return null;

			ResourceLocation newPool;

			if (basePool.getPath().endsWith("medium_rooms")) {
				newPool = minY <= 35 || rand.nextFloat() < 5 / (float)minY ? ResourceLocation.fromNamespaceAndPath(basePool.getNamespace(), basePool.getPath() + "_to_large") : basePool;
			}
			else if (basePool.getPath().endsWith("small_rooms")) {
				newPool = minY <= 50 || rand.nextFloat() < 2 / (float)minY ? ResourceLocation.fromNamespaceAndPath(basePool.getNamespace(), basePool.getPath() + "_to_med") : basePool;
			}
			else {
				return null;
			}

			return super.getPools(pools, newPool, minY, rand);
		}
	}
}
