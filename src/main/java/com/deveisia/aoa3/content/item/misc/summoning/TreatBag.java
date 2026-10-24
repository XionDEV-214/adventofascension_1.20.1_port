package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.content.entity.boss.CottonCandorEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class TreatBag extends Item {
	public TreatBag() {
		super(AoAItemGroups.MISC_ITEMS.properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEat().build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 120;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity eater) {
		if (eater instanceof ServerPlayer) {
			ServerPlayer pl = (ServerPlayer)eater;

			if (world.getDifficulty() == Difficulty.PEACEFUL) {
				PlayerUtil.notifyPlayer(pl, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));
			}
			else {
				if (!WorldUtil.isWorld(world, AoADimensions.CANDYLAND.key)) {
					PlayerUtil.notifyPlayer(pl, Component.translatable(AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));
				}
				else {
					CottonCandorEntity cottonCandor = new CottonCandorEntity(AoAEntities.Mobs.COTTON_CANDOR.get(), world);

					cottonCandor.setPos(eater.getX(), eater.getY() + 15, eater.getZ());
					world.addFreshEntity(cottonCandor);

					if (!((ServerPlayer)eater).isCreative())
						stack.shrink(1);

					PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".spawn", pl.getDisplayName()), eater.level(), eater.blockPosition(), 50);
				}
			}
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
