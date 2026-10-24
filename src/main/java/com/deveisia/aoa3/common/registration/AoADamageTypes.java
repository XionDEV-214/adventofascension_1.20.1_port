package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public final class AoADamageTypes {
	public static final ResourceKey<DamageType> MAGIC_AOE = create("magic_aoe");
	public static final ResourceKey<DamageType> MAGIC_AOE_ABSOLUTE = create("magic_aoe_absolute");
	public static final ResourceKey<DamageType> MAGIC = create("magic");
	public static final ResourceKey<DamageType> MAGIC_PIERCING = create("magic_piercing");
	public static final ResourceKey<DamageType> MAGIC_ABSOLUTE = create("magic_absolute");
	public static final ResourceKey<DamageType> BLASTER = create("blaster");
	public static final ResourceKey<DamageType> BLASTER_PIERCING = create("blaster_piercing");
	public static final ResourceKey<DamageType> BLASTER_ABSOLUTE = create("blaster_absolute");
	public static final ResourceKey<DamageType> MELEE_ABSOLUTE = create("melee_absolute");
	public static final ResourceKey<DamageType> SELF_HARM = create("self_harm");
	public static final ResourceKey<DamageType> GUN = create("gun");
	public static final ResourceKey<DamageType> VULCANE = create("vulcane");
	public static final ResourceKey<DamageType> HAULING = create("hauling");
	public static final ResourceKey<DamageType> RECOIL = create("recoil");
	public static final ResourceKey<DamageType> ACID = create("acid");
	public static final ResourceKey<DamageType> MOB_FIRE_MELEE = create("mob_fire_melee");
	public static final ResourceKey<DamageType> MOB_FIRE_RANGED = create("mob_fire_ranged");
	public static final ResourceKey<DamageType> MOB_MAGIC_RANGED = create("mob_magic_ranged");
	public static final ResourceKey<DamageType> MOB_RANGED = create("mob_ranged");
	public static final ResourceKey<DamageType> SPECIAL_ABSOLUTE = create("special_absolute");
	public static final ResourceKey<DamageType> SUFFOCATION = create("suffocation");
	public static final ResourceKey<DamageType> VOX_PONDS = create("vox_ponds");

	public static final TagKey<DamageType> IS_MAGIC = tag("is_magic");
	public static final TagKey<DamageType> IS_BLASTER = tag("is_blaster");
	public static final TagKey<DamageType> IS_GUN = tag("is_gun");
	public static final TagKey<DamageType> IS_VULCANE = tag("is_vulcane");
	public static final TagKey<DamageType> IS_AOE = tag("is_aoe");

	private static ResourceKey<DamageType> create(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, AdventOfAscension.id(id));
	}

	private static TagKey<DamageType> tag(String id) {
		return TagKey.create(Registries.DAMAGE_TYPE, AdventOfAscension.id(id));
	}

	public static Holder<DamageType> holder(Level level, ResourceKey<DamageType> key) {
		return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key) {
		return new DamageSource(holder(level, key));
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key, @Nullable Entity causingEntity) {
		return new DamageSource(holder(level, key), causingEntity);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
		return new DamageSource(holder(level, key), directEntity, causingEntity);
	}
}
