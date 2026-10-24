package com.deveisia.aoa3.content.entity.projectile.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.projectile.thrown.HellfireEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class HellfireProjectileEntity extends ThrowableProjectile {
	public HellfireProjectileEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HellfireProjectileEntity(Level world) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), world);
	}

	public HellfireProjectileEntity(HellfireEntity source, double targetPosX, double targetPosY, double targetPosZ) {
		super(AoAEntities.Projectiles.HELLFIRE_TAIL.get(), source.getX(), source.getY(), source.getZ(), source.level());

		Vec3 motion = new Vec3(targetPosX - source.getX(), targetPosY - source.getY(), targetPosZ - source.getZ());

		setDeltaMovement(motion);
		shoot(motion.x(), motion.y() + 0.5, motion.z(), 1.5f, 1.0f);
	}

	@Override
	public float getGravity() {
		if (tickCount >= 5)
			return 1.0f;

		return 0.0f;
	}

	@Override
	protected void onHit(HitResult result) {
		if (!level().isClientSide)
			discard();
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
