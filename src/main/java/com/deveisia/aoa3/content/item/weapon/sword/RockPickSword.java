package com.deveisia.aoa3.content.item.weapon.sword;

import net.minecraftforge.common.TierSortingRegistry;
import net.minecraft.world.item.Tiers;
import net.minecraft.tags.BlockTags;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RockPickSword extends BaseSword {
	public RockPickSword() {
		super(ItemUtil.customItemTier(1950, AttackSpeed.NORMAL, 12.0f, 3, 10, null));
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) && TierSortingRegistry.isCorrectTierForDrops(Tiers.DIAMOND, state)) || super.isCorrectToolForDrops(stack, state);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = Material.of(state);

		return material != Material.METAL && material != Material.HEAVY_METAL && material != Material.STONE ? super.getDestroySpeed(stack, state) : 4.0f;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.BLOCK_EFFICIENCY || enchantment == Enchantments.BLOCK_FORTUNE || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
