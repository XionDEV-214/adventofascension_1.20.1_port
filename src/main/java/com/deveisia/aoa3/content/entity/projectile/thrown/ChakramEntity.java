package com.deveisia.aoa3.content.entity.projectile.thrown;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(
		value = Dist.CLIENT,
		_interface = ItemSupplier.class
)
public class ChakramEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	public ChakramEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ChakramEntity(Level world) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), world);
	}

	public ChakramEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), shooter, gun, 1.0f, 0, 3.0f);
	}

	public ChakramEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public ChakramEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CHAKRAM.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}

	@Override
	public void doEntityImpact(Entity target) {
		target.hurt(damageSources().thrown(this, null), (float)AoAWeapons.CHAKRAM.get().getDamage());

		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.POISON, 60).level(2));
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.CHAKRAM.get());
	}
}
