package com.deveisia.aoa3.player.ability;

import net.minecraft.world.level.block.Block;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WorldUtil;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class FertiliseFarmland extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	public FertiliseFarmland(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FERTILISE_FARMLAND.get(), skill, data);
	}

	public FertiliseFarmland(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.FERTILISE_FARMLAND.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		ItemStack stack = ev.getItemStack();

		if (stack.getItem() == Items.BONE_MEAL) {
			Level world = ev.getLevel();
			BlockState state = world.getBlockState(ev.getPos());

			if (state.getBlock() == Blocks.FARMLAND && WorldUtil.canModifyBlock(world, ev.getPos(), ev.getEntity(), stack)) {
				world.setBlock(ev.getPos(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE)), Block.UPDATE_ALL);

				PlayerUtil.giveXpToPlayer((ServerPlayer)ev.getEntity(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getEntity(), AoASkills.FARMING.get()), 1), false);

				if (!ev.getEntity().isCreative())
					stack.shrink(1);
			}
		}
	}
}
