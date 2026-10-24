package com.deveisia.aoa3.content.item.tool.pickaxe;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class EmberstonePickaxe extends BasePickaxe implements LootModifyingItem {
	public EmberstonePickaxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.MINEABLE_WITH_PICKAXE))
			return;

		ServerLevel world = lootContext.getLevel();
		BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));
		ItemStack blockDrop = ItemStack.EMPTY;
		int blockDropIndex = -1;
		Item blockItem = byBlock(block);

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);

			if (stack.getItem() == blockItem)  {
				blockDrop = stack;
				blockDropIndex = i;

				break;
			}
		}

		if (blockDrop == ItemStack.EMPTY) {
			if (existingLoot.get(0).getItem() instanceof BlockItem) {
				blockDrop = existingLoot.get(0);
				blockDropIndex = 0;
			}
		}

		if (blockDrop != ItemStack.EMPTY) {
			Optional<SmeltingRecipe> smeltRecipe = world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(blockDrop), world);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem(world.registryAccess());
				int xp = (int)smeltRecipe.get().getExperience();

				existingLoot.set(blockDropIndex, smeltedStack.copy());
				block.popExperience(world, pos, xp);

				for (int i = 0; i < 5; i++) {
					world.sendParticles(ParticleTypes.FLAME, pos.getX() + RandomUtil.RANDOM.source().nextFloat(), pos.getY() + RandomUtil.RANDOM.source().nextFloat(), pos.getZ() + RandomUtil.RANDOM.source().nextFloat(), 1, 0, 0, 0, 0);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
