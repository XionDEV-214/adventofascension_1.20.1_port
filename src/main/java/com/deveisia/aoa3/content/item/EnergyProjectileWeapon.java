package com.deveisia.aoa3.content.item;

import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public interface EnergyProjectileWeapon {
	InteractionHand getWeaponHand(LivingEntity holder);

	void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter);

	boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter);
}
