package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;

public final class AoAGameRules {
	private static GameRules.Key<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = null;
	private static GameRules.Key<GameRules.BooleanValue> STRONGER_MOB_GRIEFING = null;

	public static void registerGameRules() {
		DESTRUCTIVE_WEAPON_PHYSICS = GameRules.register("destructiveWeaponPhysics", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
		STRONGER_MOB_GRIEFING = GameRules.register("doStrongerMobGriefing", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
	}

	public static boolean checkStrongerMobGriefing(Level world, Entity entity) {
		EntityMobGriefingEvent event = new EntityMobGriefingEvent(entity);
		MinecraftForge.EVENT_BUS.post(event);

		Event.Result result = event.getResult();

		return result == Event.Result.DEFAULT ? world.getGameRules().getBoolean(STRONGER_MOB_GRIEFING) : result == Event.Result.ALLOW;
	}

	public static boolean checkDestructiveWeaponPhysics(Level world) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return false;

		return world.getGameRules().getBoolean(DESTRUCTIVE_WEAPON_PHYSICS);
	}
}
