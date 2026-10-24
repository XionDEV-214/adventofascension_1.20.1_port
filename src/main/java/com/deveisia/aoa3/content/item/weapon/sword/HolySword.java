package com.deveisia.aoa3.content.item.weapon.sword;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HolySword extends BaseSword {
	public HolySword() {
		super(ItemUtil.customItemTier(1000, AttackSpeed.TRIPLE, 0.0f, 4, 10, null),
				AoAItemGroups.SWORDS.properties().durability(1000).rarity(Rarity.RARE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!world.isClientSide)
			WorldUtil.spawnLightning((ServerLevel)world, (ServerPlayer)player, player.getX(), player.getY(), player.getZ(), false);

		player.getCooldowns().addCooldown(this, 100);

		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
