package com.deveisia.aoa3.content.item.weapon.sword;

import com.deveisia.aoa3.content.capability.volatilestack.VolatileStackCapabilityHandles;
import com.deveisia.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.google.common.collect.Multimap;
import java.util.ConcurrentModificationException;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PrimalSword extends BaseSword {
	public PrimalSword() {
		super(ItemUtil.customItemTier(1960, AttackSpeed.NORMAL, 13.0f, 4, 10, null));
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			try {
				if (isSelected) {
					float currentDamageMod = cap.getValue();
					float currentCalcBuff = getCurrentDamageBuff(entity);

					if (currentDamageMod != currentCalcBuff) {
						((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
						cap.setValue(currentCalcBuff);
						((LivingEntity)entity).getAttributes().addTransientAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					}
				}
				else if (cap.getValue() != 0 && ((LivingEntity)entity).getMainHandItem().isEmpty()) {
					((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					cap.setValue(0);
				}
			}
			catch (ConcurrentModificationException ex) {

			}
		}
	}

	private float getCurrentDamageBuff(Entity holder) {
		if (holder instanceof LivingEntity) {
			float armour = (float)((LivingEntity)holder).getAttribute(Attributes.ARMOR).getValue();

			if (armour > 15) {
				return 15 / armour;
			}
			else {
				return 1.5f - (armour / 30f);
			}
		}

		return 1;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMap =  super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlot.MAINHAND) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			ItemUtil.setAttribute(modifierMap, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, getDamage() * (cap.getValue() == 0 ? 1 : cap.getValue()));
		}

		return modifierMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
