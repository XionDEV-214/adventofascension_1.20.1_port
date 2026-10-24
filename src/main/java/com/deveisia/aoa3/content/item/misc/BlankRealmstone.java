package com.deveisia.aoa3.content.item.misc;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.client.ClientOperations;
import com.deveisia.aoa3.common.registration.*;
import com.deveisia.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import com.deveisia.aoa3.content.entity.base.AoATrader;
import com.deveisia.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;
import com.deveisia.aoa3.content.entity.mob.precasia.PrimitiveCarrotopEntity;
import com.deveisia.aoa3.content.entity.npc.banker.AoABanker;
import com.deveisia.aoa3.util.*;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.DistExecutor;

public class BlankRealmstone extends Item {
	public BlankRealmstone() {
		super(AoAItemGroups.MISC_ITEMS.properties().stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (world.isClientSide)
			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientOperations::displayBlankRealmstoneGui);

		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!entity.level().isClientSide) {
			if (WorldUtil.isWorld(entity.level(), AoADimensions.PRECASIA.key)) {
				BlockPos pos = entity.blockPosition();
				BlockState state = entity.level().getBlockState(pos);

				if (state.getBlock() == Blocks.CARROTS && ((CropBlock)state.getBlock()).isMaxAge(state)) {
					PrimitiveCarrotopEntity carrotop = new PrimitiveCarrotopEntity(AoAEntities.Mobs.PRIMITIVE_CARROTOP.get(), entity.level());

					carrotop.setPos(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
					entity.level().addFreshEntity(carrotop);
					entity.discard();
					entity.level().setBlockAndUpdate(entity.blockPosition(), Blocks.AIR.defaultBlockState());
				}
			}
		}

		return false;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();

		if (level.getBlockState(context.getClickedPos()).getBlock() == AoABlocks.CRAEXXEUS_ALTAR.get()) {
			if (level instanceof ServerLevel) {
				for (int x = -3; x <= 3; x++) {
					for (int y = 1; y <= 3; y++) {
						for (int z = -3; z <= 3; z++) {
							if (level.getBlockState(pos.offset(x, y, z)).blocksMotion())
								return InteractionResult.SUCCESS;
						}
					}
				}

				DoppelgangerEntity doppelganger = new DoppelgangerEntity(level);

				doppelganger.setPos(pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f);
				doppelganger.finalizeSpawn((ServerLevel)level, level.getCurrentDifficultyAt(pos), MobSpawnType.TRIGGERED, null, null);
				level.addFreshEntity(doppelganger);

				for (int i = 0; i < 5; i++) {
					level.addAlwaysVisibleParticle(ParticleTypes.SOUL, doppelganger.getX() + RandomUtil.RANDOM.source().nextGaussian() * 0.25f, doppelganger.getY(), doppelganger.getZ() + RandomUtil.RANDOM.source().nextGaussian() * 0.25f, 0, 0.5f, 0);
				}

				if (!context.getPlayer().getAbilities().instabuild)
					context.getItemInHand().shrink(1);
			}

			return InteractionResult.SUCCESS;
		}

		return super.useOn(context);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (WorldUtil.isWorld(player.level(), AoADimensions.CREEPONIA.key) && (target instanceof AoATrader || target instanceof AoABanker)) {
			if (player instanceof ServerPlayer && DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayer)player) && player.getItemInHand(hand).getItem() == AoAItems.BLANK_REALMSTONE.get()) {
				player.setItemInHand(hand, ItemStack.EMPTY);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.VOX_PONDS_REALMSTONE.get()));
				PlayerUtil.notifyPlayer(player, Component.translatable("message.dialogue.creeponiaBlankRealmstone." + RandomUtil.randomNumberUpTo(3)));
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level().isClientSide && target.getHealth() <= 0 && target instanceof Husk && attacker instanceof Player)
			attacker.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(AoAItems.BARATHOS_REALMSTONE.get()));

		return super.hurtEnemy(stack, target, attacker);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new VolatileStackCapabilityProvider();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
