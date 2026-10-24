package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.content.entity.misc.GyrocopterEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ToyGyrocopter extends Item {
	public ToyGyrocopter() {
		super(AoAItemGroups.MISC_ITEMS.properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			if (!world.isClientSide) {
				world.addFreshEntity(new GyrocopterEntity(player));
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.GYRO.get().getDescriptionId() + ".spawn", player.getDisplayName()), world, player.blockPosition(), 50);

				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);
			}

			return InteractionResultHolder.success(heldItem);
		}
		else if (player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));
		}

		return InteractionResultHolder.fail(heldItem);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
