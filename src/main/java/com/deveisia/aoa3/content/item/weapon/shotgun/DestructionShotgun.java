package com.deveisia.aoa3.content.item.weapon.shotgun;

import com.deveisia.aoa3.common.registration.AoAEnchantments;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.gun.SerenadiumBulletEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class DestructionShotgun extends BaseShotgun {
	public DestructionShotgun(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SHOTGUN_HEAVY_FIRE_LONG.get();
	}

	@Override
	protected boolean fireGun(LivingEntity shooter, ItemStack stack, InteractionHand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		int pellets = getPelletCount();
		float spreadFactor = 0.1f * pellets * (1 - 0.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.FORM.get(), stack));
		boolean charged = RandomUtil.oneInNChance(5);

		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new SerenadiumBulletEntity(shooter, this, hand, 4, charged ? 1.5f : 1.0f, 0, (RandomUtil.RANDOM.source().nextFloat() - 0.5f) * spreadFactor, (RandomUtil.RANDOM.source().nextFloat() - 0.5f) * spreadFactor, (RandomUtil.RANDOM.source().nextFloat() - 0.5f) * spreadFactor);

			shooter.level().addFreshEntity(pellet);
		}

		if (!shooter.level().isClientSide())
			doFiringEffects(shooter, bullet, stack, hand);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
