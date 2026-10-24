package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.projectile.thrown.GrenadeEntity;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BaronGreatblade extends BaseGreatblade {
	public BaronGreatblade() {
		super(19.5f, AttackSpeed.GREATBLADE, 1200);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && attackCooldown > 0.85f) {
			if (!(attacker instanceof Player) || ((Player)attacker).isCreative() || ItemUtil.findInventoryItem((Player)attacker, new ItemStack(AoAWeapons.GRENADE.get()), true, 1)) {
				attacker.level().addFreshEntity(new GrenadeEntity(attacker, null));
				ItemUtil.damageItem(stack, attacker, 1, EquipmentSlot.MAINHAND);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
