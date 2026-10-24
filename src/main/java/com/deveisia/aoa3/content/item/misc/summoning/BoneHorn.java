package com.deveisia.aoa3.content.item.misc.summoning;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.boss.TyrosaurEntity;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BoneHorn extends Item {
	public BoneHorn() {
		super(AoAItemGroups.MISC_ITEMS.properties().durability(3).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			player.playSound(AoASounds.ITEM_BONE_HORN_CALL.get(), 1.0f, 1.0f);
			player.startUsingItem(hand);
			player.getCooldowns().addCooldown(this, 150);

			return InteractionResultHolder.success(heldItem);
		}
		else if (player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer((ServerPlayer)player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));
		}

		return InteractionResultHolder.fail(heldItem);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (!world.isClientSide && WorldUtil.isWorld(world, AoADimensions.PRECASIA.key)) {
			TyrosaurEntity tyrosaur = new TyrosaurEntity(AoAEntities.Mobs.TYROSAUR.get(), world);
			BlockPos spawnPos = RandomUtil.getRandomPositionWithinRange(user.blockPosition(), 20, 10, 20, true, world);
			int tries = 10;

			while (spawnPos.getY() > user.getY() + 10 && tries > 0) {
				spawnPos = RandomUtil.getRandomPositionWithinRange(user.blockPosition(), 20, 10, 20, true, world);
				tries--;
			}

			if (tries == 0)
				return stack;

			tyrosaur.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
			tyrosaur.setTarget(user);
			world.addFreshEntity(tyrosaur);
			ItemUtil.damageItem(stack, user, user.getUsedItemHand());
			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.TYROSAUR.get().getDescriptionId() + ".spawn", user.getDisplayName()), world, user.blockPosition(), 50);
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
