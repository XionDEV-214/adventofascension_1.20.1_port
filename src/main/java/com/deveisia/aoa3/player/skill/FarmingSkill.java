package com.deveisia.aoa3.player.skill;

import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.BlockUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.level.BlockEvent;

public class FarmingSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK, ListenerType.ANIMAL_BREED};

	public FarmingSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.FARMING.get(), plData, jsonData);
	}

	public FarmingSkill(CompoundTag nbtData) {
		super(AoASkills.FARMING.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (canGainXp(true) && BlockUtil.canPlayerHarvest(ev.getState(), ev.getPlayer(), ev.getLevel(), ev.getPos())) {
			Block block = ev.getState().getBlock();
			int xpTime = 0;

			if (block instanceof CropBlock) {
				xpTime = ((CropBlock)block).isMaxAge(ev.getState()) ? 7 * ((CropBlock)block).getMaxAge() : 0;
			}
			else if (block instanceof StemGrownBlock) {
				xpTime = 12;
			}

			if (xpTime > 0)
				adjustXp(PlayerUtil.getTimeBasedXpForLevel(getLevel(true), xpTime), false, false);
		}
	}

	@Override
	public void handleAnimalBreed(BabyEntitySpawnEvent ev) {
		if (!canGainXp(true))
			return;

		adjustXp(PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 600), false, false);
	}
}
