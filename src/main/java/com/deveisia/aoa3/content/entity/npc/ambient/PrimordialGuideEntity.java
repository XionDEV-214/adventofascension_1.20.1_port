package com.deveisia.aoa3.content.entity.npc.ambient;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.content.entity.base.AoAAmbientNPC;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PrimordialGuideEntity extends AoAAmbientNPC {
	public PrimordialGuideEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.DUSTOPIA.key);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.primordial_guide." + random.nextInt(5);
	}
}
