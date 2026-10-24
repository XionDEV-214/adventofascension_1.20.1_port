package com.deveisia.aoa3.content.item.food;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Chilli extends Item {
	public Chilli() {
		super(AoAItemGroups.FOOD.properties().food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide()) {
			entity.setSecondsOnFire(3);

			if (entity instanceof ServerPlayer && WorldUtil.isWorld(world, AoADimensions.NETHER.key) && entity.isInLava())
				AdvancementUtil.completeAdvancement((ServerPlayer)entity, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "nether/overheat"), "lava_chilli_consume");
		}

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
