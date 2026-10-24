package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.boss.NethengeicWitherEntity;
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

public class NethengeicCallstone extends BossSpawningItem {
	public NethengeicCallstone() {
		super(AoASounds.ENTITY_NETHENGEIC_WITHER_AMBIENT, ParticleTypes.ENTITY_EFFECT, ParticleTypes.INSTANT_EFFECT);
	}

	@Override
	public void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ) {
		NethengeicWitherEntity nethengeicWither = new NethengeicWitherEntity(AoAEntities.Mobs.NETHENGEIC_WITHER.get(), world);

		nethengeicWither.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(nethengeicWither);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.NETHENGEIC_WITHER.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, BlockPos.containing(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.NETHER.key)) {
			PlayerUtil.notifyPlayer(player, Component.translatable(AoAEntities.Mobs.NETHENGEIC_WITHER.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!world.noCollision(new AABB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 2.5d, posZ + 1d))) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.noSpace").withStyle(ChatFormatting.RED));

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", ChatFormatting.AQUA));
	}
}
