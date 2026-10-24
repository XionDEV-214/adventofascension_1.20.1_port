package com.deveisia.aoa3.content.world.spawner;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.VisualentEntity;
import com.deveisia.aoa3.util.RandomUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.ForgeEventFactory;

public class VisualentSpawner implements CustomSpawner {
	private int spawnCooldown = 18000;

	@Override
	public int tick(ServerLevel world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 6000 || !spawnHostiles || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING) || world.getDifficulty() == Difficulty.PEACEFUL)
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = 6000;

			if (world.getEntities(AoAEntities.Mobs.VISUALENT.get(), Entity::isAlive).isEmpty()) {
				Player pl = world.getRandomPlayer();

				if (pl == null)
					return 0;

				BlockPos spawnPos = findNearbySpawnPosition(world, pl.blockPosition(), 64, 10);

				if (spawnPos == null)
					return 0;

				VisualentEntity visualent = (VisualentEntity)AoAEntities.Mobs.VISUALENT.get().create(world, null, null, spawnPos, MobSpawnType.NATURAL, false, false);

				if (visualent == null)
					return 0;

				if (ForgeEventFactory.checkSpawnPosition(visualent, world, MobSpawnType.NATURAL)) {
					world.addFreshEntity(visualent);

					return 1;
				}
			}
		}

		return 0;
	}

	@Nullable
	private BlockPos findNearbySpawnPosition(ServerLevel world, BlockPos centerPos, int radius, int maxTries) {
		for (int i = 0; i < maxTries; i++) {
			BlockPos pos = RandomUtil.getRandomPositionWithinRange(centerPos, radius, radius, radius);

			if (!world.getWorldBorder().isWithinBounds(pos))
				continue;

			if (centerPos.distSqr(pos) < 20 * 20)
				continue;

			if (world.noCollision(AoAEntities.Mobs.VISUALENT.get().getAABB(pos.getX(), pos.getY(), pos.getZ())))
				return pos;
		}

		return null;
	}
}
