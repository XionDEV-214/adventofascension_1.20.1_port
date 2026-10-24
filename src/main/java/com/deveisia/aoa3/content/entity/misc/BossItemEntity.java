package com.deveisia.aoa3.content.entity.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.misc.summoning.BossSpawningItem;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class BossItemEntity extends ItemEntity {
	public static final int lifetime = 200;
	private final Player player;

	public BossItemEntity(Level world, double x, double y, double z, ItemStack stack, Player player) {
		super(AoAEntities.Misc.BOSS_ITEM.get(), world);

		this.lifespan = lifetime;
		this.player = player;
		setYRot(random.nextFloat() * 360f);

		setPos(x, y, z);
		setItem(stack);
		setDeltaMovement(random.nextDouble() * 0.2d - 0.1d, 0.2d, random.nextDouble() * 0.2d - 0.1d);
	}

	public BossItemEntity(EntityType<? extends ItemEntity> entityType, Level world) {
		super(entityType, world);

		lifespan = lifetime;
		player = null;
	}

	@Override
	public void playerTouch(Player player) {
		if (!level().isClientSide && !hasPickUpDelay() && player.getUUID().equals(getOwner())) {
			ItemStack stack = this.getItem();
			int stackSize = stack.getCount();

			int hookResult = ForgeEventFactory.onItemPickup(this, player);

			if (hookResult < 0)
				return;

			ItemStack stackCopy = stack.copy();

			if (hookResult == 1 || stackSize <= 0 || player.getInventory().add(stack) || stackCopy.getCount() > getItem().getCount()) {
				stackCopy.setCount(stackCopy.getCount() - getItem().getCount());
				ForgeEventFactory.firePlayerItemPickupEvent(player, this, stackCopy);

				if (stack.isEmpty()) {
					player.take(this, stackSize);
					discard();
					stack.setCount(stackSize);
				}

				player.awardStat(Stats.ITEM_USED.get(getItem().getItem()), stackSize);
			}
		}
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (lifespan == 6000)
			return;

		if (!(getItem().getItem() instanceof BossSpawningItem)) {
			discard();

			return;
		}

		if (!level().isClientSide && player == null) {
			discard();

			return;
		}

		BossSpawningItem bossItem = (BossSpawningItem)getItem().getItem();

		if (player instanceof ServerPlayer) {
			if (tickCount == lifespan - 1) {
				if (bossItem.canSpawnHere(level(), (ServerPlayer)player, getX(), getY(), getZ())) {
					bossItem.spawnBoss(level(), (ServerPlayer)player, getX(), getY(), getZ());

					discard();
				}
				else {
					lifespan = 6000;
				}
			}

			return;
		}

		if (tickCount < lifespan)
			bossItem.handleTimerParticles(this, getX(), getY(), getZ(), lifespan, tickCount);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
