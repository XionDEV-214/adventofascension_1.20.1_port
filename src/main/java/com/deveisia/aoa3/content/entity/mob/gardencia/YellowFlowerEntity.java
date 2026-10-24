package com.deveisia.aoa3.content.entity.mob.gardencia;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.base.AoAMeleeMob;
import com.deveisia.aoa3.content.entity.boss.VinocorneEntity;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class YellowFlowerEntity extends AoAMeleeMob {
	public YellowFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.YELLOW_FLOWER.get(), vinocorne.level());

		moveTo(vinocorne.getX(), vinocorne.getY(), vinocorne.getZ(), random.nextFloat() * 360, 0);
	}

	public YellowFlowerEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5f;
	}

	@Override
	public void tick() {
		super.tick();

		if (random.nextInt(100) == 0)
			EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 35));
	}
}
