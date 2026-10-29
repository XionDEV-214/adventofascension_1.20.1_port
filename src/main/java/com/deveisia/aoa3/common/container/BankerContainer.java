package com.deveisia.aoa3.common.container;

import com.deveisia.aoa3.common.registration.AoAContainers;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.content.entity.npc.banker.AoABanker;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BankerContainer extends AbstractContainerMenu {
	/** Amount of a lower-tier coin that is worth one coin of the next tier up. */
	public static final int EXCHANGE_RATE = 20;
	/** Number of exchange rows (one per adjacent pair of coin tiers). */
	public static final int ROWS = 7;
	/** Input slots: rows [0, ROWS) combine coins (left column), rows [ROWS, ROWS * 2) split coins (right column). */
	public static final int INPUT_SLOTS = ROWS * 2;
	public static final int OUTPUT_SLOTS = ROWS * 2;
	public static final int COIN_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
	private static final int PLAYER_INV_START = COIN_SLOTS;
	private static final int HOTBAR_START = PLAYER_INV_START + 27;
	private static final int PLAYER_SLOTS_END = HOTBAR_START + 9;

	public static final int FIRST_ROW_Y = 28;
	public static final int ROW_SPACING = 18;
	public static final int PLAYER_INV_Y = 167;

	private final SimpleContainer inputs;
	private final SimpleContainer outputs;

	public final AoABanker banker;
	private final Player player;

	public BankerContainer(int screenId, Inventory playerInventory) {
		this(screenId, playerInventory, null);
	}

	public BankerContainer(int screenId, Inventory playerInventory, AoABanker banker) {
		super(AoAContainers.BANKER.get(), screenId);

		this.player = playerInventory.player;
		this.banker = banker;

		inputs = new SimpleContainer(INPUT_SLOTS) {
			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == getInputCoin(index);
			}
		};

		outputs = new SimpleContainer(OUTPUT_SLOTS) {
			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == getOutputCoin(index);
			}
		};

		for (int i = 0; i < INPUT_SLOTS; i++) {
			addSlot(new Slot(inputs, i, isCombineRow(i) ? 15 : 101, FIRST_ROW_Y + (i % ROWS) * ROW_SPACING) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return stack.getItem() == getInputCoin(getSlotIndex());
				}

				@Override
				public void setChanged() {
					super.setChanged();

					updateOutput(getSlotIndex());
				}
			});
		}

		for (int i = 0; i < OUTPUT_SLOTS; i++) {
			addSlot(new Slot(outputs, i, isCombineRow(i) ? 58 : 144, FIRST_ROW_Y + (i % ROWS) * ROW_SPACING) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return false;
				}

				@Override
				public boolean mayPickup(Player playerIn) {
					return hasItem();
				}

				@Override
				public void onTake(Player thePlayer, ItemStack stack) {
					inputs.getItem(getSlotIndex()).shrink(getInputCount(getSlotIndex()));
					updateOutput(getSlotIndex());
				}
			});
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(player.getInventory(), inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, PLAYER_INV_Y + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(player.getInventory(), hotbarSlot, 8 + hotbarSlot * 18, PLAYER_INV_Y + 58));
		}
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		if (!player.level().isClientSide)
			clearContainer(player, inputs);
	}

	protected void updateOutput(int index) {
		ItemStack slotStack = inputs.getItem(index);

		if (!slotStack.isEmpty() && slotStack.getCount() >= getInputCount(index)) {
			outputs.setItem(index, new ItemStack(getOutputCoin(index), getOutputCount(index)));
		}
		else {
			outputs.setItem(index, ItemStack.EMPTY);
		}

		broadcastChanges();
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index < COIN_SLOTS) {
				if (!moveItemStackTo(slotStack, PLAYER_INV_START, PLAYER_SLOTS_END, false))
					return ItemStack.EMPTY;
			}
			else {
				for (int i = 0; i < INPUT_SLOTS && !slotStack.isEmpty(); i++) {
					Slot slot2 = slots.get(i);

					if (slot2.hasItem() && slot2.getItem().getItem() == slotStack.getItem())
						moveItemStackTo(slotStack, i, i + 1, false);
				}

				if (!slotStack.isEmpty()) {
					if (index < HOTBAR_START) {
						if (!moveItemStackTo(slotStack, HOTBAR_START, PLAYER_SLOTS_END, true))
							return ItemStack.EMPTY;
					}
					else {
						if (!moveItemStackTo(slotStack, PLAYER_INV_START, HOTBAR_START, false))
							return ItemStack.EMPTY;
					}
				}
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, slotStack);
		}

		return stack;
	}

	@Override
	public boolean stillValid(Player player) {
		return banker != null && banker.isAlive() && this.player.distanceToSqr(banker) <= 64;
	}

	/**
	 * Coin tiers in ascending order of value. Each tier is worth {@link #EXCHANGE_RATE} of the previous one.
	 */
	public static Item getCoinTier(int tier) {
		return switch (tier) {
			case 0 -> AoAItems.COPPER_COIN.get();
			case 1 -> AoAItems.SILVER_COIN.get();
			case 2 -> AoAItems.GOLD_COIN.get();
			case 3 -> AoAItems.LUNAVER_COIN.get();
			case 4 -> AoAItems.RANAX_COIN.get();
			case 5 -> AoAItems.SOLDENA_COIN.get();
			case 6 -> AoAItems.ECLIPSIA_COIN.get();
			case 7 -> AoAItems.CRAEADAE_COIN.get();
			default -> Items.AIR;
		};
	}

	/** Whether the given input/output index is in the left-hand (lower tier -> higher tier) column. */
	public static boolean isCombineRow(int index) {
		return index < ROWS;
	}

	public static Item getInputCoin(int index) {
		int row = index % ROWS;

		return isCombineRow(index) ? getCoinTier(row) : getCoinTier(row + 1);
	}

	public static Item getOutputCoin(int index) {
		int row = index % ROWS;

		return isCombineRow(index) ? getCoinTier(row + 1) : getCoinTier(row);
	}

	public static int getInputCount(int index) {
		return isCombineRow(index) ? EXCHANGE_RATE : 1;
	}

	public static int getOutputCount(int index) {
		return isCombineRow(index) ? 1 : EXCHANGE_RATE;
	}

	/**
	 * Gets the coin for a menu slot index (inputs first, then outputs).
	 */
	public static Item getCoinForSlot(int index) {
		if (index < 0 || index >= COIN_SLOTS)
			return Items.AIR;

		return index < INPUT_SLOTS ? getInputCoin(index) : getOutputCoin(index - INPUT_SLOTS);
	}

	/**
	 * Gets the displayed stack size for a menu slot index (inputs first, then outputs).
	 */
	public static int getCountForSlot(int index) {
		if (index < 0 || index >= COIN_SLOTS)
			return 0;

		return index < INPUT_SLOTS ? getInputCount(index) : getOutputCount(index - INPUT_SLOTS);
	}
}
