package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.content.entity.misc.BossItemEntity;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public abstract class BossSpawningItem extends Item {
	private final ParticleOptions[] timerParticles;
	private final Supplier<SoundEvent> throwingSound;

	public BossSpawningItem(@Nullable Supplier<SoundEvent> throwSound, @Nonnull ParticleOptions... timerParticles) {
		super(AoAItemGroups.MISC_ITEMS.properties().rarity(Rarity.UNCOMMON));

		this.timerParticles = timerParticles;
		this.throwingSound = throwSound;
	}

	public void handleTimerParticles(ItemEntity entityItem, double posX, double posY, double posZ, int lifespan, int ticksExisted) {
		int index = (int)(ticksExisted / (float)lifespan * timerParticles.length);

		if (RandomUtil.oneInNChance(1 + (lifespan - ticksExisted) / 20))
			entityItem.level().addParticle(timerParticles[index], posX, posY + 0.25d, posZ, 0, 0, 0);
	}

	public abstract void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ);

	public abstract boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ);

	@Nullable
	public SoundEvent getThrowingSound() {
		return throwingSound.get();
	}

	public static BossItemEntity newBossEntityItemFromExisting(ItemEntity item, Player player) {
		BossItemEntity bossItem = new BossItemEntity(item.level(), item.getX(), item.getY(), item.getZ(), item.getItem(), player);

		bossItem.setPickUpDelay(10);
		bossItem.setThrower(player.getUUID());
		bossItem.setTarget(player.getUUID());
		bossItem.setDeltaMovement(item.getDeltaMovement());

		return bossItem;
	}
}
