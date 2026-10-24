package com.deveisia.aoa3.content.entity.projectile.thrown;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
public class HellfireEntity extends BaseBullet implements HardProjectile, ItemSupplier {
	private float explosionStrength = 1.5f;
	private LivingEntity shooter;
	private BaseGun gun;

	public HellfireEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HellfireEntity(Level world) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), world);
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), shooter, gun, 1.0f, 0, 1.5f);
		this.shooter = shooter;
		this.gun = gun;
	}

	public HellfireEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HellfireEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HELLFIRE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.075f;
	}

	public void setExplosionStrength(float strength) {
		explosionStrength = strength;
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doImpactEffect() {
		int count = 0;

		for (LivingEntity e : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(7.0D), EntityUtil.Predicates.HOSTILE_MOB)) {
			level().addFreshEntity(new HellfireProjectileEntity(this, e.getX(), e.getY(), e.getZ()));
			e.setSecondsOnFire(10);
			count++;
		}

		if (shooter instanceof Player) {
			level().playSound(null, getX(), getY(), getZ(), AoASounds.HELLFIRE_IMPACT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);

			if (count >= 20 && shooter instanceof ServerPlayer)
				AdvancementUtil.completeAdvancement((ServerPlayer)shooter, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "overworld/heckfire"), "20_target_hellfire");
		}
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(AoAWeapons.HELLFIRE.get());
	}
}
