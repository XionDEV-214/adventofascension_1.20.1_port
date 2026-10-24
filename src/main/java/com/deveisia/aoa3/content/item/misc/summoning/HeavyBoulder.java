package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.boss.RockRiderEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class HeavyBoulder extends BossSpawningItem {
	public HeavyBoulder() {
		super(AoASounds.ENTITY_ROCK_RIDER_SWITCH, ParticleTypes.UNDERWATER, ParticleTypes.LARGE_SMOKE);
	}

	@Override
	public void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ) {
		RockRiderEntity rockrider = new RockRiderEntity(AoAEntities.Mobs.ROCK_RIDER.get(), world);

		rockrider.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(rockrider);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.ROCK_RIDER.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, BlockPos.containing(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.HAVEN.key)) {
			PlayerUtil.notifyPlayer(player, Component.translatable(AoAEntities.Mobs.ROCK_RIDER.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!world.noCollision(new AABB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 4d, posZ + 1d))) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.noSpace").withStyle(ChatFormatting.RED));

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", ChatFormatting.AQUA));
	}
}
