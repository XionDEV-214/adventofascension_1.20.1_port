package com.deveisia.aoa3.content.entity.npc.ambient;

import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.base.AoAAmbientNPC;
import com.deveisia.aoa3.content.item.misc.ReservedItem;
import com.deveisia.aoa3.util.DamageUtil;
import com.deveisia.aoa3.util.WorldUtil;
import javax.annotation.Nullable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ZalChildEntity extends AoAAmbientNPC {
	public ZalChildEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.20625f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level(), AoADimensions.LUNALUS.key);
	}

	@Nullable
	@Override
	protected String getInteractMessage(ItemStack heldItem) {
		return "message.dialogue.zal_child." + random.nextInt(5);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.ALIEN_ORB.get()) {
			if (!level().isClientSide) {
				player.setItemInHand(hand, ((ReservedItem)AoAItems.FLESHY_BONES.get()).newValidStack());
				DamageUtil.killEntityCleanly(this);
			}

			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}
}
