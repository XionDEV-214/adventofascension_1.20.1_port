package com.deveisia.aoa3.content.entity.mob.overworld;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.util.AdvancementUtil;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class DeadTreeEntity extends AoAMeleeMob {
	public DeadTreeEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		absMoveTo(((int)getX()) + 0.5d, getY(), ((int)getZ()) + 0.5d, 0, 0);

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.4f;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
			discard();

			return true;
		}

		if (!level().isClientSide && isAlive() && source.getDirectEntity() instanceof Player) {
			TreeSpiritEntity treeSpirit = new TreeSpiritEntity(AoAEntities.Mobs.TREE_SPIRIT.get(), level());

			treeSpirit.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
			level().addFreshEntity(treeSpirit);
			treeSpirit.hurt(source, amount);

			if (treeSpirit.getHealth() <= 0)
				AdvancementUtil.completeAdvancement((ServerPlayer)source.getDirectEntity(), ResourceLocation.fromNamespaceAndPath("aoa3", "overworld/mightiest_tree_in_the_forest"), "tree_spirit_instakill");

			level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_TREE_SPIRIT_AMBIENT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
			discard();

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void playerTouch(Player entityIn) {}

	@Override
	protected void doPush(Entity entityIn) {}

}
