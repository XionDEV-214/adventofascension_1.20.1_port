package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.boss.KingShroomusEntity;
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

public class ShroomStone extends BossSpawningItem {
	public ShroomStone() {
		super(AoASounds.ENTITY_KING_SHROOMUS_HEAL, ParticleTypes.CLOUD, ParticleTypes.ENTITY_EFFECT);
	}

	@Override
	public void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ) {
		KingShroomusEntity kingShroomus = new KingShroomusEntity(AoAEntities.Mobs.KING_SHROOMUS.get(), world);

		kingShroomus.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(kingShroomus);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.KING_SHROOMUS.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, BlockPos.containing(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.MYSTERIUM.key)) {
			PlayerUtil.notifyPlayer(player, Component.translatable(AoAEntities.Mobs.KING_SHROOMUS.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!world.noCollision(new AABB(posX - 0.75d, posY, posZ - 0.75d, posX + 0.75d, posY + 4d, posZ + 0.75d))) {
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
