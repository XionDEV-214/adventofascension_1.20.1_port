package com.deveisia.aoa3.content.entity.animal;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.base.AoAAnimal;
import com.deveisia.aoa3.util.ItemUtil;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HalyconEntity extends AoAAnimal {
	public HalyconEntity(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.isCreative())
				heldStack.shrink(1);

			player.playSound(SoundEvents.COW_MILK, 1.0f, 1.0f);

			if (heldStack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}

			return InteractionResult.SUCCESS;
		}
		else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Item.byBlock(AoABlocks.HAVEN_GRASS_PLANT.get());
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return new HalyconEntity(AoAEntities.Animals.HALYCON.get(), this.level());
	}
}
