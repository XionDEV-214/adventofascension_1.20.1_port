package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import com.deveisia.aoa3.data.server.AoAHaulingFishReloadListener;
import com.deveisia.aoa3.event.custom.events.HaulingItemFishedEvent;
import com.deveisia.aoa3.event.custom.events.PlayerChangeXpEvent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.google.gson.JsonObject;
import java.util.function.Function;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.ItemFishedEvent;

public class FishingTrapSpawn extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.FISHED_ITEM, ListenerType.GAIN_SKILL_XP};

	public FishingTrapSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);
	}

	public FishingTrapSpawn(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemFished(ItemFishedEvent ev, boolean isHauling) {
		if (ev.getEntity() instanceof ServerPlayer && testAsChance()) {
			FishingHook bobber = ev.getHookEntity();
			Player player = ev.getEntity();
			Level world = bobber.level();
			BlockPos pos = bobber.blockPosition();
			ItemStack rodStack = player.getMainHandItem().getItem() instanceof FishingRodItem ? player.getMainHandItem() : player.getOffhandItem();
			float luck = Math.max(0, EnchantmentHelper.getFishingLuckBonus(rodStack));
			boolean isLava = false;

			if (isHauling) {
				luck = ((HaulingItemFishedEvent)ev).getLuck();
				isLava = ((HaulingFishingBobberEntity)ev.getHookEntity()).getApplicableFluid() != null && Fluids.LAVA.is(((HaulingFishingBobberEntity)ev.getHookEntity()).getApplicableFluid());
			}

			Function<Level, Entity> trapEntityFunction = AoAHaulingFishReloadListener.getTrapListForBiome(world.getBiome(pos), isLava).getRandomElement((ServerPlayer)player, luck);

			if (trapEntityFunction != null) {
				Entity trapEntity = trapEntityFunction.apply(world);
				double velX = player.getX() - bobber.getX();
				double velY = player.getY() - bobber.getY();
				double velZ = player.getZ() - bobber.getZ();

				trapEntity.setDeltaMovement(velX * 0.1d, velY * 0.1d + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)) * 0.15d, velZ * 0.1d);
				trapEntity.setPos(bobber.getX(), bobber.getY(), bobber.getZ());
				world.addFreshEntity(trapEntity);
			}
		}
	}

	@Override
	public void handleSkillXpGain(PlayerChangeXpEvent ev) {
		if (ev.getSkill().type() == AoASkills.HAULING.get())
			ev.setXpGain(ev.getNewXpGain() * 1.25f);
	}
}
