package com.deveisia.aoa3.content.item.weapon.cannon;

import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.projectile.cannon.PlutonSticklerShotEntity;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.misc.PlutonSticklerStuckEntity;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class PlutonStickler extends BaseCannon {
	public PlutonStickler(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_CARROT_CANNON_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return AoAWeapons.GRENADE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new PlutonSticklerShotEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof LivingEntity)
			target.level().addFreshEntity(new PlutonSticklerStuckEntity(shooter, this, (LivingEntity)target, bulletDmgMultiplier));

		bullet.discard();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.stickler.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.stickler.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
