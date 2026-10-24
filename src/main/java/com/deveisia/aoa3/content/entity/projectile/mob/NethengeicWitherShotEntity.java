package com.deveisia.aoa3.content.entity.projectile.mob;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.content.entity.boss.NethengeicWitherEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class NethengeicWitherShotEntity extends BaseMobProjectile {
	public final boolean cataclysmic;

	public NethengeicWitherShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);

		this.cataclysmic = false;
	}

	public NethengeicWitherShotEntity(NethengeicWitherEntity wither, boolean cataclysmic) {
		super(AoAEntities.Projectiles.NETHENGEIC_WITHER_SHOT.get(), wither.level(), wither, Type.PHYSICAL);

		this.cataclysmic = cataclysmic;
	}

	public NethengeicWitherShotEntity(Level world) {
		super(AoAEntities.Projectiles.NETHENGEIC_WITHER_SHOT.get(), world);

		this.cataclysmic = false;
	}
}
