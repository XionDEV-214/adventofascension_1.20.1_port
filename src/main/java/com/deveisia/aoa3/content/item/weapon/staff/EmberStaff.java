package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class EmberStaff extends BaseStaff<Object> {
	public EmberStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_EMBER_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.KINETIC_RUNE.get(), 1);
		runes.put(AoAItems.WIND_RUNE.get(), 1);
		runes.put(AoAItems.FIRE_RUNE.get(), 1);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
		caster.clearFire();

		for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(5), entity -> entity instanceof Player || entity instanceof TamableAnimal)) {
			entity.clearFire();
		}

		WorldUtil.operateOnMultipleBlocksInRange(world, caster.blockPosition(), 5, state -> state.getBlock() == Blocks.FIRE, pos -> world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
