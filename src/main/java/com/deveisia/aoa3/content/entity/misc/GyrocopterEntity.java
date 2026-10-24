package com.deveisia.aoa3.content.entity.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.GyroEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class GyrocopterEntity extends Entity {
    public GyrocopterEntity(Player player) {
        this(AoAEntities.Misc.GYROCOPTER.get(), player.level());

        double offsetX = -Mth.sin(player.getYRot() * (float)Math.PI / 180f);
        double offsetZ = Mth.cos(player.getYRot() * (float)Math.PI / 180f);

        moveTo(player.getX() + offsetX * 1.5, player.getY(), player.getZ() + offsetZ * 1.5, player.getYRot(), 0);
    }

    public GyrocopterEntity(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);

        setSilent(true);
    }

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions size) {
        return 0.8125f;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {}

    @Override
    public void tick() {
        super.tick();

        if (tickCount >= 200) {
            if (!level().isClientSide)
                level().addFreshEntity(new GyroEntity(this));

            discard();

            return;
        }

        move(MoverType.SELF, new Vec3(0, 0.1d, 0));
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
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
    protected void playStepSound(BlockPos pos, BlockState block) {}
}
