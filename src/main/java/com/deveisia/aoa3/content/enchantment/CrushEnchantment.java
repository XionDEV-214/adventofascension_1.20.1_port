package com.deveisia.aoa3.content.enchantment;

import com.deveisia.aoa3.common.registration.AoAEnchantments;
import com.deveisia.aoa3.content.item.weapon.maul.BaseMaul;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class CrushEnchantment extends Enchantment {
	public CrushEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, AoAEnchantments.MAUL, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseMaul;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 + 10 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {
		super.doPostAttack(user, target, level);
	}
}
