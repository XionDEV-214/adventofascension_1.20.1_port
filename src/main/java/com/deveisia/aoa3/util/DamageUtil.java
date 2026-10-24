package com.deveisia.aoa3.util;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import com.deveisia.aoa3.content.item.armour.AdventArmour;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

import javax.annotation.Nullable;

public final class DamageUtil {
	public static void doScaledKnockback(LivingEntity target, Entity attacker, float strength, double xRatio, double zRatio) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, strength, xRatio, zRatio);

		if (event.isCanceled())
			return;

		strength = event.getStrength() * (float)(1 - target.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
		xRatio = event.getRatioX();
		zRatio = event.getRatioZ();
		target.hasImpulse = true;
		target.hurtMarked = true;
		float vec = (float)Math.sqrt(xRatio * xRatio + zRatio * zRatio);
		Vec3 targetMotion = target.getDeltaMovement();
		double motionX = targetMotion.x() / 2d - xRatio / (double)vec * (double)strength;
		double motionZ = targetMotion.z() / 2d - zRatio / (double)vec * (double)strength;
		double motionY = targetMotion.y();

		if (target.onGround()) {
			motionY /= 2.0D;
			motionY += strength;

			if (motionY > 0.4000000059604645D)
				motionY = 0.4000000059604645D;
		}

		target.setDeltaMovement(new Vec3(motionX, motionY, motionZ));
		target.hurtMarked = true;
	}

	public static void doBodySlamKnockback(LivingEntity target, Entity attacker, float xModifier, float yModifier, float zModifier) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		Vec3 attackerVelocity = attacker.getDeltaMovement();
		double xVelocity = attackerVelocity.x() * xModifier;
		double yVelocity = attackerVelocity.y() * yModifier;
		double zVelocity = attackerVelocity.z() * zModifier;
		LivingKnockBackEvent event = ForgeHooks.onLivingKnockBack(target, (float)NumberUtil.average(xVelocity, yVelocity, zVelocity), xVelocity, zVelocity);

		if (event.isCanceled())
			return;

		double resist = 1;
		AttributeInstance attrib = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE);

		if (attrib != null)
			resist -= attrib.getValue();

		target.push(event.getRatioX() * resist, yVelocity * resist, event.getRatioZ() * resist);
		target.hurtMarked = true;
	}

	public static void killEntityCleanly(Entity entity) {
		if (!(entity instanceof LivingEntity target)) {
			entity.hurt(entity.damageSources().fellOutOfWorld(), Float.MAX_VALUE);
			entity.discard();

			return;
		}

		target.hurt(AoADamageTypes.source(target.level(), AoADamageTypes.SPECIAL_ABSOLUTE), target.getHealth());

		if (target.getHealth() > 0)
			target.setHealth(0);
	}

	private static DamageSource attackSource(LivingEntity attacker) {
		return attacker instanceof Player player ? attacker.damageSources().playerAttack(player) : attacker.damageSources().mobAttack(attacker);
	}

	public static boolean dealAoeDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource;

		if (indirectSource != null) {
			damageSource = AoADamageTypes.source(attacker.level(), bypassProtections ? AoADamageTypes.MAGIC_AOE_ABSOLUTE : AoADamageTypes.MAGIC_AOE, indirectSource, attacker);
		}
		else if (bypassProtections) {
			damageSource = AoADamageTypes.source(attacker.level(), AoADamageTypes.MELEE_ABSOLUTE, attacker);
		}
		else {
			damageSource = attackSource(attacker);
		}

		if (target.isInvulnerableTo(damageSource))
			return false;

		target.invulnerableTime = 0;

		return target.hurt(damageSource, dmg);
	}

	public static boolean dealBlasterDamage(LivingEntity attacker, Entity target, Entity shot, float dmg, boolean bypassProtections) {
		DamageSource damageSource = AoADamageTypes.source(attacker.level(), bypassProtections ? AoADamageTypes.BLASTER_ABSOLUTE : target instanceof Player ? AoADamageTypes.BLASTER : AoADamageTypes.BLASTER_PIERCING, shot, attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		Vec3 targetMotion = target.getDeltaMovement();
		boolean success = target.hurt(damageSource, dmg);
		target.setDeltaMovement(targetMotion);
		target.hurtMarked = false;

		return success;
	}

	public static boolean dealMeleeDamage(LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = bypassProtections ? AoADamageTypes.source(attacker.level(), AoADamageTypes.MELEE_ABSOLUTE, attacker) : attackSource(attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		target.invulnerableTime = 0;

		boolean success = target.hurt(damageSource, dmg);

		target.invulnerableTime = 0;

		return success;
	}

	public static boolean dealMagicDamage(@Nullable Entity indirectSource, LivingEntity attacker, Entity target, float dmg, boolean bypassProtections) {
		DamageSource damageSource = AoADamageTypes.source(attacker.level(), bypassProtections ? AoADamageTypes.MAGIC_ABSOLUTE : target instanceof Player ? AoADamageTypes.MAGIC : AoADamageTypes.MAGIC_PIERCING, indirectSource != null ? indirectSource : attacker, attacker);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;

		return target.hurt(damageSource, dmg);
	}

	public static void dealSelfHarmDamage(LivingEntity target, float dmg) {
		if (target instanceof Player && !PlayerUtil.shouldPlayerBeAffected((Player)target))
			return;

		if (target.getHealth() - dmg > 0.0f) {
			target.setHealth(target.getHealth() - dmg);
		}
		else {
			target.invulnerableTime = 0;
			target.hurt(AoADamageTypes.source(target.level(), AoADamageTypes.SELF_HARM), dmg);
		}
	}

	public static boolean dealGunDamage(Entity target, LivingEntity attacker, ThrowableProjectile bullet, float dmg) {
		DamageSource source = AoADamageTypes.source(attacker.level(), AoADamageTypes.GUN, bullet, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		boolean success;

		if (RandomUtil.percentChance(0.6f)) {
			Vec3 targetMotion = target.getDeltaMovement();
			success = target.hurt(source, dmg);
			target.setDeltaMovement(targetMotion);
			target.hurtMarked = false;
		}
		else {
			success = target.hurt(source, dmg);
			target.hurtMarked = true;
		}

		return success;
	}

	public static boolean dealVulcaneDamage(LivingEntity target, Player attacker, float dmg) {
		DamageSource source = AoADamageTypes.source(attacker.level(), AoADamageTypes.VULCANE, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		target.invulnerableTime = 0;

		return target.hurt(source, dmg);
	}

	public static boolean dealRangedDamage(Entity target, LivingEntity attacker, Entity projectile, float dmg) {
		DamageSource source = attacker.damageSources().thrown(projectile, attacker);

		if (target.isInvulnerableTo(source))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(source, dmg);

		if (!(target instanceof LivingEntity))
			return false;

		target.invulnerableTime = 0;
		target.hurtMarked = true;

		return target.hurt(source, dmg);
	}

	public static boolean dealHaulingDamage(Player player, HaulingFishingBobberEntity bobber, Entity target, float dmg) {
		DamageSource damageSource = AoADamageTypes.source(player.level(), AoADamageTypes.HAULING, bobber, player);

		if (target.isInvulnerableTo(damageSource))
			return false;

		if (target instanceof EnderDragonPart || target instanceof EndCrystal)
			return target.hurt(damageSource, dmg);

		return target instanceof LivingEntity && target.hurt(damageSource, dmg);
	}

	public static boolean isMagic(DamageSource source) {
		return source.is(AoADamageTypes.IS_MAGIC);
	}

	public static boolean isMeleeDamage(DamageSource source) {
		return source.getEntity() != null && !source.is(DamageTypeTags.IS_PROJECTILE) && !source.is(DamageTypes.THROWN) && !isMagic(source) && !source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypeTags.IS_FIRE) && source.getDirectEntity() == source.getEntity();
	}

	public static boolean isBlasterDamage(DamageSource source) {
		return source.is(AoADamageTypes.IS_BLASTER);
	}

	public static boolean isMagicDamage(DamageSource source, Entity target, float dmg) {
		return isMagic(source) && !isBlasterDamage(source) && !source.is(DamageTypes.THORNS) && !(target instanceof LivingEntity && isPoisonDamage(source, target, dmg));
	}

	public static boolean isRangedDamage(DamageSource source, Entity target, float dmg) {
		return ((source.is(DamageTypeTags.IS_PROJECTILE) && !isGunDamage(source)) || source.is(DamageTypes.THROWN)) && !isMagicDamage(source, target, dmg);
	}

	public static boolean isGunDamage(DamageSource source) {
		return source.is(AoADamageTypes.IS_GUN);
	}

	public static boolean isPoisonDamage(DamageSource source, Entity target, float dmg) {
		return source.is(DamageTypes.MAGIC) && source.getEntity() == null && target instanceof LivingEntity && ((LivingEntity)target).hasEffect(MobEffects.POISON) && dmg == 1.0f;
	}

	public static boolean isPhysicalDamage(DamageSource source, Entity target, float dmg) {
		return !isPoisonDamage(source, target, dmg) && !isMagic(source) && !source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypes.WITHER) && !source.is(DamageTypes.FELL_OUT_OF_WORLD) && !source.is(DamageTypeTags.IS_FIRE) && !source.is(DamageTypes.STARVE);
	}

	public static boolean isVulcaneDamage(DamageSource source) {
		return source.is(AoADamageTypes.IS_VULCANE);
	}

	public static boolean isEnvironmentalDamage(DamageSource source) {
		if (source.getEntity() != null || source.is(DamageTypeTags.IS_EXPLOSION))
			return false;

		return source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.CACTUS) || source.is(AoADamageTypes.ACID) || source.is(DamageTypes.LIGHTNING_BOLT) || source.is(DamageTypes.LAVA) || source.is(DamageTypes.CRAMMING) || source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.FALLING_BLOCK) || source.is(DamageTypes.STARVE) || source.is(DamageTypes.FALLING_ANVIL) || source.is(DamageTypes.FELL_OUT_OF_WORLD);
	}

	public static boolean isPlayerEnvironmentallyProtected(ServerPlayer player) {
		Item helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

		return helmet instanceof AdventArmour && ((AdventArmour)helmet).isHelmetAirTight(player);
	}

	public static float clampDamage(float damage) {
		return Mth.clamp(damage, 0, Float.MAX_VALUE);
	}
}
