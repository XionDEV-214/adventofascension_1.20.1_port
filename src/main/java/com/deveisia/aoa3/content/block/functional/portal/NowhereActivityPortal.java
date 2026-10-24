package com.deveisia.aoa3.content.block.functional.portal;

import com.deveisia.aoa3.common.particletype.PortalFloaterParticleType;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.Locale;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;

public class NowhereActivityPortal extends PortalBlock {
	private static final EnumProperty<Activity> ACTIVITY = EnumProperty.create("activity", Activity.class);

	public NowhereActivityPortal() {
		super(AoADimensions.NOWHERE.key, MapColor.GOLD, 0);

		registerDefaultState(getStateDefinition().any().setValue(ACTIVITY, Activity.UTILITY));
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity.getVehicle() == null && !entity.isVehicle() && entity instanceof ServerPlayer) {
			if (entity.getPortalCooldown() > 0) {
				entity.setPortalCooldown(30);

				return;
			}

			entity.setPortalCooldown(100);
			Activity activity = state.getValue(ACTIVITY);
			BlockPos teleportPos = activity.teleportPos;

			entity.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
			activity.onUse((ServerPlayer)entity);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(ACTIVITY);
	}

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
			int colour;

			switch (state.getValue(ACTIVITY)) {
				case PARKOUR:
					colour = 39103;
					break;
				case BOSSES:
					colour = 12189696;
					break;
				case DUNGEON:
					colour = 9502944;
					break;
				case UTILITY:
					colour = 29210;
					break;
				case RETURN:
				default:
					colour = 16777215;
					break;
			}

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = rand.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = rand.nextFloat() * 2.0F * (float)randomMod;
			}

			world.addParticle(new PortalFloaterParticleType.Data(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), colour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	public enum Activity implements StringRepresentable {
		PARKOUR(0, 202, 0, pl -> {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			Inventory inventory = pl.getInventory();

			plData.storeItems(inventory.items);
			plData.storeItems(inventory.armor);
			plData.storeItems(inventory.offhand);

			pl.getInventory().clearContent();
		}),
		BOSSES(0, 202, 0),
		DUNGEON(0, 202, 0),
		UTILITY(0, 202, 0),
		RETURN(0, 202, 0, pl -> {
			PlayerUtil.getAdventPlayer(pl).returnItemStorage();
		});

		private final BlockPos teleportPos;
		private final Consumer<ServerPlayer> useFunction;

		Activity(int x, int y, int z, @Nullable Consumer<ServerPlayer> useFunction) {
			this.teleportPos = BlockPos.containing(x, y, z);
			this.useFunction = useFunction;
		}

		Activity(int x, int y, int z) {
			this(x, y, z, null);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}

		public void onUse(ServerPlayer pl) {
			if (useFunction != null)
				useFunction.accept(pl);
		}
	}
}
