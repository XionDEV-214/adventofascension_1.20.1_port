package com.deveisia.aoa3.content.item.tool.misc;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.common.registration.AoASounds;
import com.deveisia.aoa3.content.entity.misc.pixon.PixonEntity;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class InfusionBowl extends Item {
	private final int harvestAmount;
	private final int harvestLevelModifier;

	public InfusionBowl(int durability, int harvestAmount, int harvestLevelModifier) {
		super(AoAItemGroups.TOOLS.properties().durability(durability));

		this.harvestAmount = harvestAmount;
		this.harvestLevelModifier = harvestLevelModifier;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	public int getHarvestAmount() {
		return harvestAmount;
	}

	public int getHarvestReqModifier() {
		return harvestLevelModifier;
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (!(target instanceof PixonEntity))
			return InteractionResult.FAIL;

		if (player instanceof ServerPlayer) {
			PixonEntity pixon = (PixonEntity)target;

			if (!pixon.canHarvest((ServerPlayer)player, stack))
				return InteractionResult.PASS;

			LootTable harvestTable = player.getServer().getLootData().getLootTable(pixon.getLootTable());

			int harvestCount = 0;
			List<ItemStack> harvestStacks = new ArrayList<ItemStack>();
			LootParams lootContext = new LootParams.Builder((ServerLevel)player.level()).withParameter(LootContextParams.KILLER_ENTITY, player).withParameter(LootContextParams.THIS_ENTITY, pixon).withParameter(LootContextParams.ORIGIN, pixon.position()).withParameter(LootContextParams.DAMAGE_SOURCE, player.damageSources().generic()).create(LootContextParamSets.ENTITY);

			while (harvestCount < getHarvestAmount() && pixon.getHealth() > 0) {
				if (!player.isCreative())
					ItemUtil.damageItem(stack, player, 1, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

				harvestStacks.addAll(harvestTable.getRandomItems(lootContext));

				pixon.setHealth(pixon.getHealth() - 7 + RandomUtil.randomNumberUpTo(6));
				pixon.setLastHurtByPlayer(player);

				harvestCount++;
			}

			if (!harvestStacks.isEmpty())
				ItemUtil.givePlayerMultipleItems(player, harvestStacks);

			if (pixon.isAlive()) {
				pixon.nextHarvestTick = pixon.level().getGameTime() + 8 + pixon.getRandom().nextInt(32);
			}
			else {
				player.awardStat(Stats.ENTITY_KILLED.get(pixon.getType()));
			}

			player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			player.level().playSound(null, pixon.blockPosition().getX(), pixon.blockPosition().getY(), pixon.blockPosition().getZ(), AoASounds.ENTITY_PIXON_HARVEST.get(), SoundSource.MASTER, 1.0f, 1.0f);
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.infusionBowl.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
	}
}
