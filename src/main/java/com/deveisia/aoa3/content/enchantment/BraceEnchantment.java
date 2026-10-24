package com.deveisia.aoa3.content.enchantment;

import com.deveisia.aoa3.common.registration.AoAEnchantments;
import com.deveisia.aoa3.content.item.weapon.cannon.BaseCannon;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.content.item.weapon.sniper.BaseSniper;
import java.util.UUID;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class BraceEnchantment extends Enchantment {
	public static final AttributeModifier BRACE_DEBUFF = new AttributeModifier(UUID.fromString("a1371c64-c09e-4ed6-adfd-5afbaea79369"), "AoABraceDebuff", -0.35, AttributeModifier.Operation.MULTIPLY_BASE);

	public BraceEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.LIGHT_GUN, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon);
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 29;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 80;
	}
}
