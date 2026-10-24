package com.deveisia.aoa3.content.entity.misc.pixon;

import net.minecraft.tags.BlockTags;
import com.deveisia.aoa3.common.registration.AoATags;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.item.tool.misc.InfusionBowl;
import com.deveisia.aoa3.util.EntityUtil;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PixonEntity extends PathfinderMob {
    public long nextHarvestTick = 0;

    public PixonEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new PanicGoal(this, 0.75d));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.55d));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Random spawn health bonus", this.random.nextInt(30), AttributeModifier.Operation.ADDITION));

        setHealth(getMaxHealth());

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean addEffect(MobEffectInstance effectInstanceIn, @Nullable Entity effectSource) {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {}

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    public boolean canHarvest(ServerPlayer player, ItemStack bowlStack) {
        if (level().getGameTime() >= this.nextHarvestTick && bowlStack.getItem() instanceof InfusionBowl) {
            InfusionBowl bowl = ((InfusionBowl)bowlStack.getItem());

            if (true)
                return true;

        }

        return false;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        if (!EntityUtil.isNaturalSpawnReason(reason))
            return true;

        if (!super.checkSpawnRules(world, reason))
            return false;

        BlockState groundState = world.getBlockState(blockPosition().below());

        return groundState.is(AoATags.Blocks.GRASS) || groundState.is(BlockTags.DIRT) || groundState.is(BlockTags.SAND);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.is(DamageTypes.FELL_OUT_OF_WORLD);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 0.65f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_PIXON_AMBIENT.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.6f;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}

    public abstract int getHarvestLevelReq();
}
