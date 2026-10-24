package com.deveisia.aoa3.content.block.functional.misc;

import com.deveisia.aoa3.common.registration.AoADamageTypes;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GiantSnailAcid extends AcidBlock {
	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
		if (EntityUtil.isVulnerableEntity(entity, AoADamageTypes.source(worldIn, AoADamageTypes.ACID))) {
			entity.hurt(AoADamageTypes.source(worldIn, AoADamageTypes.ACID), 4);
			EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40));
		}
	}
}
