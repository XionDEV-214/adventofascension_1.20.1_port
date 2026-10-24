package com.deveisia.aoa3.content.entity.projectile.thrown;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.deveisia.aoa3.client.render.AoAAnimations;
import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.common.registration.AoAWeapons;
import com.deveisia.aoa3.content.entity.projectile.HardProjectile;
import com.deveisia.aoa3.content.entity.projectile.gun.BaseBullet;
import com.deveisia.aoa3.content.item.weapon.gun.BaseGun;
import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class HardenedParapiranhaEntity extends BaseBullet implements HardProjectile, GeoEntity {
	private final AnimatableInstanceCache animationFactory = GeckoLibUtil.createInstanceCache(this);

	public HardenedParapiranhaEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public HardenedParapiranhaEntity(Level world) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), world);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, 1.0f, 0, 1.5f);
	}

	public HardenedParapiranhaEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public HardenedParapiranhaEntity(Level world, double x, double y, double z) {
		super(AoAEntities.Projectiles.HARDENED_PARAPIRANHA.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}

	@Override
	public void tick() {
		super.tick();

		Vec3 motion = getDeltaMovement();
		float motionVector = (float)Math.sqrt(motion.x() * motion.x() + motion.z() * motion.z());
		setYRot((float)(Mth.atan2(motion.x(), motion.z()) * (180D / Math.PI)));
		setXRot((float)(Mth.atan2(motion.y(), motionVector) * (180D / Math.PI)));
		yRotO = getYRot();
		xRotO = getXRot();
	}

	@Override
	protected void onHit(HitResult result) {
		if (result instanceof BlockHitResult && tickCount <= 1 && getOwner() == null)
			return;

		super.onHit(result);
	}

	@Override
	public void doEntityImpact(Entity target) {
		if (target.hurt(damageSources().thrown(this, null), (float)AoAWeapons.HARDENED_PARAPIRANHA.get().getDamage()))
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 60).level(2));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(AoAAnimations.genericFlyController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return animationFactory;
	}
}
