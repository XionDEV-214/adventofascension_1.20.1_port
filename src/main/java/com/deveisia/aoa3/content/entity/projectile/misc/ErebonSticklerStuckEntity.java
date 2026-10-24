package com.deveisia.aoa3.content.entity.projectile.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class ErebonSticklerStuckEntity extends ThrowableProjectile {
	private LivingEntity target;
	private LivingEntity shooter;
	private int age;
	private AreaEffectCloud cloud = null;

	public ErebonSticklerStuckEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ErebonSticklerStuckEntity(Level world) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), world);
	}

	public ErebonSticklerStuckEntity(LivingEntity shooter, BaseGun gun, LivingEntity target, float bulletDmgMultiplier) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), shooter.level());
		this.target = target;
		this.shooter = shooter;

		moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 0);
		shoot(0, 0, 0, 0, 0);
	}

	public ErebonSticklerStuckEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.EREBON_STICKLER_STUCK.get(), x, y, z, world);
	}

	@Override
	protected float getGravity() {
		return 0.0f;
	}

	@Override
	protected void onHit(HitResult result) {}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide)
			return;

		age++;

		if (target != null && target.isAlive()) {
			moveTo(target.getX(), target.getY() + target.getEyeHeight(), target.getZ(), 0, 360);
		}
		else {
			WorldUtil.createExplosion(shooter, level(), this, 2.0f);
			discard();

			if (cloud != null)
				cloud.discard();

			return;
		}

		if (age >= 100) {
			WorldUtil.createExplosion(getOwner(), level(), getX(), getY() + 1, getZ(), 2.0f);
			discard();

			if (cloud != null)
				cloud.discard();

			return;
		}

		if (cloud == null) {
			cloud = new AreaEffectCloud(level(), target.getX(), target.getY(), target.getZ());

			cloud.setDuration(100 - age);
			cloud.setRadius(2);
			cloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, true));
			level().addFreshEntity(cloud);
		}
		else {
			cloud.teleportTo(target.getX(), target.getY(), target.getZ());
		}
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
