package com.deveisia.aoa3.content.item.lootbox;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.LootUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CrystalBox extends Item {
	public CrystalBox() {
		super(AoAItemGroups.MISC_ITEMS.properties());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (player instanceof ServerPlayer) {
			ServerPlayer pl = (ServerPlayer)player;

			ItemUtil.givePlayerMultipleItems(pl, LootUtil.generateLoot((ServerLevel)pl.level(), ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "items/crystal_box"), LootUtil.getGiftContext((ServerLevel)pl.level(), pl.position(), pl)));

			if (!pl.isCreative())
				heldStack.shrink(1);

			pl.inventoryMenu.broadcastChanges();

			return InteractionResultHolder.success(heldStack);
		}

		return InteractionResultHolder.pass(heldStack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
