package com.deveisia.aoa3.content.item.tool.axe;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.block.generation.log.LogBlock;
import com.deveisia.aoa3.util.ItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class Chainsaw extends AxeItem {
	public Chainsaw() {
		super(ItemUtil.customItemTier(2500, 18.0f, 4.0f, 2, 0, null), 4.0f, -2F, new Properties().durability(2500));
	}

	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
		if (!player.level().isClientSide) {
			player.level().playSound(null, player.getX(), player.getY(), player.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);

			Block block = player.level().getBlockState(pos).getBlock();

			if (block instanceof LogBlock || block.defaultBlockState().is(BlockTags.LOGS))
				player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 30, true, false));
		}

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide) {
			attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), AoASounds.ITEM_CHAINSAW_USE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(stack, attacker, 1, EquipmentSlot.MAINHAND);
		}

		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return false;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.NONE;
	}
}
