package com.deveisia.aoa3.content.entity.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAGameRules;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.boss.BaronessEntity;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BaronBombEntity extends Entity {
	private int timer = 150;
	private UUID ownerUUID = null;
	private Player ownerPlayer = null;
	private BaronessEntity baroness = null;

	public BaronBombEntity(EntityType<? extends Entity> entityType, Level world) {
		super(entityType, world);
	}

	public BaronBombEntity(Player pl) {
		this(AoAEntities.Misc.BARON_BOMB.get(), pl.level());

		ownerUUID = pl.getUUID();
		teleportTo(pl.getX(), pl.getY(), pl.getZ());
	}

	public BaronBombEntity(BaronessEntity baroness) {
		this(AoAEntities.Misc.BARON_BOMB.get(), baroness.level());

		this.baroness = baroness;

		teleportTo(baroness.getX(), baroness.getY(), baroness.getZ());
	}

	@Override
	protected float getEyeHeight(Pose pose, EntityDimensions size) {
		return 0.25f;
	}

	@Override
	public void push(double x, double y, double z) {}

	@Override
	public boolean isPushable() {
		return isAlive();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void playerTouch(Player entity) {
		if (!level().isClientSide && checkEntityCollision(entity)) {
			if (ownerUUID == null) {
				WorldUtil.createExplosion(baroness, level(), getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level(), this) ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE, false);
			}
			else {
				WorldUtil.createExplosion(ownerPlayer, level(), getX(), getY() + 1, getZ(), 2.3f);
			}

			discard();
		}
	}

	@Override
	public boolean isPickable() {
		return isAlive();
	}

	@Override
	public void push(Entity entity) {
		if (!level().isClientSide && checkEntityCollision(entity)) {
			if (ownerPlayer != null) {
				WorldUtil.createExplosion(ownerPlayer, level(), getX(), getY() + 1, getZ(), 2.3f);
			}
			else {
				WorldUtil.createExplosion(baroness, level(), getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level(), this) ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE, false);
			}

			discard();
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!level().isClientSide) {
			discard();

			if (!source.is(DamageTypeTags.IS_EXPLOSION) && source.getEntity() != baroness && source.getEntity() != ownerPlayer) {
				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, level(), getX(), getY() + 1, getZ(), 2.3f);
				}
				else {
					WorldUtil.createExplosion(baroness, level(), getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level(), this) ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE, false);
				}
			}

			return false;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean skipAttackInteraction(Entity attacker) {
		if (!level().isClientSide && checkEntityCollision(attacker))
			discard();

		return true;
	}

	private boolean checkEntityCollision(Entity target) {
		if (ownerUUID == null) {
			return !(target instanceof BaronessEntity);
		}
		else {
			if (target instanceof Player) {
				updateOwner();

				if (ownerPlayer != null && !EntityUtil.canPvp(ownerPlayer, (Player)target))
					return false;
			}
			else if (target instanceof TamableAnimal) {
				UUID ownerUUID = ((TamableAnimal)target).getOwnerUUID();

				if (ownerUUID != null && ownerUUID.equals(this.ownerUUID))
					return false;
			}
		}

		return true;
	}

	private void updateOwner() {
		ownerPlayer = ownerPlayer == null ? level().getServer().getPlayerList().getPlayer(ownerUUID) : ownerPlayer;
	}

	@Override
	public void tick() {
		super.tick();

		BlockPos pos = BlockPos.containing(getX(), 0, getZ());
		Vec3 motion = getDeltaMovement();
		double motionY = motion.y();

		if (!level().isClientSide || level().hasChunkAt(pos) && level().isAreaLoaded(pos, 1)) {
			if (!isNoGravity())
				motionY -= 0.08D;
		}
		else if (this.getY() > 0.0D) {
			motionY = -0.1D;
		}
		else {
			motionY = 0.0D;
		}

		move(MoverType.SELF, new Vec3(0, motionY, 0));

		if (!level().isClientSide) {
			if (ownerUUID == null && level().getDifficulty() == Difficulty.PEACEFUL) {
				discard();

				return;
			}

			timer--;

			if (timer == 16) {
				level().playSound(null, getX(), getY(), getZ(), AoASounds.BARON_BOMB_PRIME.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
			}
			else if (timer <= 0) {
				updateOwner();

				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, level(), getX(), getY() + 1, getZ(), 2.3f);
				}
				else {
					WorldUtil.createExplosion(baroness, level(), getX(), getY() + 1, getZ(), 4f);
				}

				discard();
			}
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
