package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ReturnCrystal extends Item {
	public ReturnCrystal() {
		super(AoAItemGroups.MISC_ITEMS.properties());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			player.startUsingItem(hand);

			return InteractionResultHolder.consume(player.getItemInHand(hand));
		}
		else {
			return InteractionResultHolder.pass(player.getItemInHand(hand));
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (entity instanceof ServerPlayer) {
			if (!WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
				PlayerUtil.notifyPlayer((ServerPlayer)entity, Component.translatable("message.feedback.item.returnCrystal.wrongDim"));

				return stack;
			}

			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);

			if (!((ServerPlayer)entity).isCreative())
				stack.shrink(1);

			ItemUtil.clearInventoryOfItems((ServerPlayer)entity, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
			entity.teleportTo(0, 212, 0);
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
