package com.deveisia.aoa3.content.item.tool.shovel;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.ServerParticlePacket;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.item.LootModifyingItem;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.SimpleContainer;
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
import net.minecraft.world.phys.Vec3;

public class EmberstoneShovel extends BaseShovel implements LootModifyingItem {
	public EmberstoneShovel() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		BlockState harvestedBlock = getHarvestedBlock(lootContext);
		Block block = harvestedBlock.getBlock();

		if (block == Blocks.AIR || existingLoot.isEmpty() || !harvestedBlock.is(BlockTags.MINEABLE_WITH_SHOVEL))
			return;

		ServerLevel world = lootContext.getLevel();
		BlockPos pos = BlockPos.containing(lootContext.getParamOrNull(LootContextParams.ORIGIN));

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack stack = existingLoot.get(i);
			Optional<SmeltingRecipe> smeltRecipe = world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), world);

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getResultItem(world.registryAccess()).copy();

				smeltedStack.setCount(smeltedStack.getCount() * stack.getCount());
				existingLoot.set(i, smeltedStack);
				block.popExperience(world, pos, (int)smeltRecipe.get().getExperience());

				ServerParticlePacket particlePacket = new ServerParticlePacket();

				for (int x = 0; x < 5; x++) {
					particlePacket.particle(ParticleTypes.FLAME, pos.getX() + RandomUtil.randomValueUpTo(1), pos.getY() + RandomUtil.randomValueUpTo(1), pos.getZ() + RandomUtil.randomValueUpTo(1));
				}

				AoAPackets.messageNearbyPlayers(particlePacket, world, Vec3.atCenterOf(pos), 32);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
