package com.deveisia.aoa3.content.entity.mob.deeplands;

import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.ItemUtil;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CaseConstructEntity extends AoAMeleeMob {
    public CaseConstructEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.96875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!level().isClientSide && player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.DEEP_CRYSTAL.get())) {
            if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1)) {
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.CRYSTEVIA_REALMSTONE.get()));

                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }
}
