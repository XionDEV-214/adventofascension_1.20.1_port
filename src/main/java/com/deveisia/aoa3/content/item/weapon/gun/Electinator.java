package com.deveisia.aoa3.content.item.weapon.gun;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.gun.YellowBulletEntity;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Electinator extends BaseGun {
	public Electinator(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GOLEM_GUN_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new YellowBulletEntity(shooter, this, hand, 120, 0);
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		for (LivingEntity mob : target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3), entity -> entity != target && entity instanceof Enemy)) {
			DamageUtil.dealMagicDamage(null, shooter, mob, 0.25f, false);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
