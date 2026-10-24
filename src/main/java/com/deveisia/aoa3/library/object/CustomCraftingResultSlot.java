package com.deveisia.aoa3.library.object;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class CustomCraftingResultSlot<C extends Container, T extends Recipe<C>> extends Slot {
	private final C craftSlots;
	private final Player player;
	private final RecipeType<T> recipeType;
	private int removeCount;

	public CustomCraftingResultSlot(Player player, C craftSlots, Container container, RecipeType<T> recipeType, int slot, int posX, int posY) {
		super(container, slot, posX, posY);

		this.player = player;
		this.craftSlots = craftSlots;
		this.recipeType = recipeType;
	}

	@Override
	public boolean mayPlace(ItemStack pStack) {
		return false;
	}

	public ItemStack remove(int amount) {
		if (hasItem())
			this.removeCount += Math.min(amount, getItem().getCount());

		return super.remove(amount);
	}

	protected void onQuickCraft(ItemStack stack, int amount) {
		this.removeCount += amount;

		checkTakeAchievements(stack);
	}

	protected void onSwapCraft(int craftCount) {
		this.removeCount += craftCount;
	}

	protected void checkTakeAchievements(ItemStack stack) {
		if (this.removeCount > 0) {
			stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
			ForgeEventFactory.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
		}

		if (this.container instanceof RecipeHolder recipeHolder)
			recipeHolder.awardUsedRecipes(this.player, getCraftingItems());

		this.removeCount = 0;
	}

	private List<ItemStack> getCraftingItems() {
		int size = this.craftSlots.getContainerSize();
		List<ItemStack> items = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			items.add(this.craftSlots.getItem(i));
		}

		return items;
	}

	public void onTake(Player player, ItemStack stack) {
		checkTakeAchievements(stack);
		ForgeHooks.setCraftingPlayer(player);

		NonNullList<ItemStack> remainingItems = player.level().getRecipeManager().getRemainingItemsFor(recipeType, this.craftSlots, player.level());

		ForgeHooks.setCraftingPlayer(null);

		for(int i = 0; i < remainingItems.size(); ++i) {
			ItemStack currentStack = this.craftSlots.getItem(i);
			ItemStack remainderStack = remainingItems.get(i);

			if (!currentStack.isEmpty()) {
				this.craftSlots.removeItem(i, 1);
				currentStack = this.craftSlots.getItem(i);
			}

			if (!remainderStack.isEmpty()) {
				if (currentStack.isEmpty()) {
					this.craftSlots.setItem(i, remainderStack);
				}
				else if (ItemStack.isSameItemSameTags(currentStack, remainderStack)) {
					remainderStack.grow(currentStack.getCount());
					this.craftSlots.setItem(i, remainderStack);
				}
				else if (!this.player.getInventory().add(remainderStack)) {
					this.player.drop(remainderStack, false);
				}
			}
		}
	}
}
