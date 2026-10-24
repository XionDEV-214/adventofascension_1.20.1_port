package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class RunicStaff extends BaseStaff<PlayerEnderChestContainer> {
	public RunicStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_RUNIC_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 5);
	}

	@Override
	public PlayerEnderChestContainer checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster instanceof Player ? ((Player)caster).getEnderChestInventory() : null;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, PlayerEnderChestContainer args) {
		((Player)caster).openMenu(new SimpleMenuProvider((id, player, inventory) -> ChestMenu.threeRows(id, player, args), Component.translatable("container.enderchest")));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
