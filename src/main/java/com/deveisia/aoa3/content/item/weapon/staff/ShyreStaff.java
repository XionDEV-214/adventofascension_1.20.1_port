package com.deveisia.aoa3.content.item.weapon.staff;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoADimensions;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import com.deveisia.aoa3.content.entity.projectile.staff.ShyreShotEntity;
import com.deveisia.aoa3.util.AdvancementUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class ShyreStaff extends BaseStaff<Object> {
	public ShyreStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SHYRE_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 3);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 3);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new ShyreShotEntity(caster, this, 120));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {
		Level world = shooter.level();
		BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(hitPos.x(), hitPos.y(), hitPos.z());
		BlockState state = world.getBlockState(testPos);
		Vec3 shotMotion = shot.getDeltaMovement();
		Vec3 testVec = hitPos;
		int tests = 0;

		while (tests <= 10 && !(state = world.getBlockState(testPos)).isAir()) {
			testVec = testVec.subtract(shotMotion.x() * 0.15f, shotMotion.y() * 0.15f, shotMotion.z() * 0.15f);
			testPos.set(testVec.x() + shooter.getBbWidth(), testVec.y(), testVec.z() + shooter.getBbWidth());
			tests++;
		}

		if (state.isAir() && !ForgeEventFactory.onEntityTeleportCommand(shooter, testVec.x(), testVec.y(), testVec.z()).isCanceled()) {
			shooter.teleportTo(testVec.x(), testVec.y(), testVec.z());

			if (shooter instanceof ServerPlayer && WorldUtil.isWorld(shooter.level(), AoADimensions.LUNALUS.key))
				AdvancementUtil.completeAdvancement((ServerPlayer)shooter, ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "lunalus/200_iq"), "lunalus_shyre_staff_travel");
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		Vec3 teleportPos = new Vec3((target.getX() + shot.getX()) / 2d, (target.getY() + shot.getY()) / 2d, (target.getZ() + shot.getZ()) / 2d);

		if (!ForgeEventFactory.onEntityTeleportCommand(shooter, teleportPos.x(), teleportPos.y(), teleportPos.z()).isCanceled())
			shooter.teleportTo(teleportPos.x(), teleportPos.y(), teleportPos.z());

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
