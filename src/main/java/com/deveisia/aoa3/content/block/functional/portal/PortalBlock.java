package com.deveisia.aoa3.content.block.functional.portal;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.LiquidBlockContainer;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.particletype.PortalFloaterParticleType;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import com.deveisia.aoa3.content.world.teleporter.specific.AbyssTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.BarathosTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.CandylandTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.CeleveTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.CreeponiaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.CrysteviaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.DeeplandsTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.DustopiaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.GardenciaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.GreckonTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.HavenTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.IromineTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.LBoreanTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.LelyetiaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.LunalusTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.MysteriumTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.NetherTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.NowhereTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.PrecasiaTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.RunandorTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.ShyrelandsTeleporter;
import com.deveisia.aoa3.content.world.teleporter.specific.VoxPondsTeleporter;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;

public class PortalBlock extends Block implements LiquidBlockContainer {
	@Override
	public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
		return false;
	}

	private static final VoxelShape X_SHAPE = Shapes.create(new AABB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = Shapes.create(new AABB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final ResourceKey<Level> world;

	public PortalBlock(ResourceKey<Level> world, MapColor mapColour, int particleColour) {
		super(new BlockUtil.CompactProperties(Material.PORTAL, mapColour).unbreakable().light(11).sound(SoundType.GLASS).noClip().get());

		registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.world = world;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		switch(state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
			case Z:
				return Z_SHAPE;
			case X:
			default:
				return X_SHAPE;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, EntityUtil.getDirectionFacing(context.getPlayer(), true).getAxis());
	}

	private boolean isCompatibleNeighbour(Level world, BlockPos pos) {
		BlockState block = world.getBlockState(pos);

		return block.getBlock() == this || !block.isAir();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjacent, Direction side) {
		if (adjacent.getBlock() == this) {
			Direction.Axis axis;

			switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
				case X:
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);

					if (axis == Direction.Axis.X)
						return true;

					break;
				case Z:
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);

					if (axis == Direction.Axis.Z)
						return true;

					break;
				default:
					break;
			}
		}

		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		for (int i = 0; i < 4; ++i) {
			double posXStart = (float)pos.getX() + rand.nextFloat();
			double posYStart = (float)pos.getY() + rand.nextFloat();
			double posZStart = (float)pos.getZ() + rand.nextFloat();
			double motionX = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double motionY = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double motionZ = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int randomMod = rand.nextInt(2) * 2 - 1;

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = rand.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = rand.nextFloat() * 2.0F * (float)randomMod;
			}

			world.addParticle(new PortalFloaterParticleType.Data(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), particleColour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (!world.isClientSide() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			if (!AoAConfig.SERVER.allowNonPlayerPortalTravel.get() & !(entity instanceof Player))
				return;

			if (entity.getPortalCooldown() > 0) {
				entity.setPortalCooldown(30);

				return;
			}

			if (this.world == null || !world.getServer().isNetherEnabled())
				return;

			ITeleporter teleporter = this.world == Level.NETHER ? new NetherTeleporter() : getTeleporterForWorld(world.getServer().getLevel(this.world));
			PortalCoordinatesContainer portalLoc = null;

			if (entity instanceof Player) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);
				portalLoc = plData.getPortalReturnLocation(world.dimension());

				((ServerPlayer)entity).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), entity.getYRot(), entity.getXRot());

				if (portalLoc != null && world.getServer().getLevel(portalLoc.fromDim) == null)
					portalLoc = null;
			}
			else {
				entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
			}

			if (portalLoc == null) {
				if (world.dimension() == this.world) {
					entity = entity.changeDimension(world.getServer().getLevel(Level.OVERWORLD), teleporter);
				}
				else {
					entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
				}
			}
			else if (world.dimension() != this.world) {
				entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
			}
			else {
				entity = entity.changeDimension(world.getServer().getLevel(portalLoc.fromDim), teleporter);
			}

			if (entity != null)
				entity.setPortalCooldown(100);
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
					case Z:
						return state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);
					case X:
						return state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_AXIS);
	}

	@Override
	public void attack(BlockState state, Level world, BlockPos pos, Player player) {
		if (world.isEmptyBlock(pos.above()) || world.isEmptyBlock(pos.below())) {
			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

			return;
		}

		switch (world.getBlockState(pos).getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
			case Z:
				if (world.isEmptyBlock(pos.east()) || world.isEmptyBlock(pos.west()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
			case X:
				if (world.isEmptyBlock(pos.north()) || world.isEmptyBlock(pos.south()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		Direction.Axis facing = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);

		switch (facing) {
			case Z:
				if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.east()) || !isCompatibleNeighbour(world, pos.west()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
			case X:
				if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.north()) || !isCompatibleNeighbour(world, pos.south()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
		}
	}

	public static ITeleporter getTeleporterForWorld(ServerLevel world) {
		if (!world.dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID))
			return world.getPortalForcer();

		AoADimensions.AoADimension aoaDim = AoADimensions.getDim(world.dimension());

		if (aoaDim == null)
			return world.getPortalForcer();

		switch (aoaDim) {
			case ABYSS:
				return new AbyssTeleporter();
			case BARATHOS:
				return new BarathosTeleporter();
			case CANDYLAND:
				return new CandylandTeleporter();
			case CELEVE:
				return new CeleveTeleporter();
			case CREEPONIA:
				return new CreeponiaTeleporter();
			case CRYSTEVIA:
				return new CrysteviaTeleporter();
			case DEEPLANDS:
				return new DeeplandsTeleporter();
			case DUSTOPIA:
				return new DustopiaTeleporter();
			case GARDENCIA:
				return new GardenciaTeleporter();
			case GRECKON:
				return new GreckonTeleporter();
			case HAVEN:
				return new HavenTeleporter();
			case IROMINE:
				return new IromineTeleporter();
			case LBOREAN:
				return new LBoreanTeleporter();
			case LELYETIA:
				return new LelyetiaTeleporter();
			case LUNALUS:
				return new LunalusTeleporter();
			case MYSTERIUM:
				return new MysteriumTeleporter();
			case NOWHERE:
				return new NowhereTeleporter();
			case PRECASIA:
				return new PrecasiaTeleporter();
			case RUNANDOR:
				return new RunandorTeleporter();
			case SHYRELANDS:
				return new ShyrelandsTeleporter();
			case VOX_PONDS:
				return new VoxPondsTeleporter();
			default:
				return world.getPortalForcer();
		}
	}
}
