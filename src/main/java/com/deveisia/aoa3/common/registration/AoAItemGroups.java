package com.deveisia.aoa3.common.registration;

import java.util.LinkedHashMap;
import com.deveisia.aoa3.advent.AdventOfAscension;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class AoAItemGroups {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdventOfAscension.MOD_ID);
	public static final ArrayList<AoAItemGroup> REGISTERED_ITEM_GROUPS = new ArrayList<AoAItemGroup>();

	private static final Map<Item.Properties, AoAItemGroup> PENDING_PROPERTIES = Collections.synchronizedMap(new IdentityHashMap<>());
	private static final Map<Item, AoAItemGroup> ITEM_GROUPS = Collections.synchronizedMap(new LinkedHashMap<>());

	public static final AoAItemGroup MISC_ITEMS = new AoAItemGroup("AoA Miscellaneous Items", "miscellaneous", () -> new ItemStack(AoAItems.RUNIUM_CHUNK.get()));
	public static final AoAItemGroup FOOD = new AoAItemGroup("AoA Food", "food", () -> new ItemStack(AoAItems.COOKED_RAINBOWFISH.get()));
	public static final AoAItemGroup TOOLS = new AoAItemGroup("AoA Tools", "tools", () -> new ItemStack(AoATools.SERENADIUM_PICKAXE.get()));
	public static final AoAItemGroup ARMOUR = new AoAItemGroup("AoA Armour", "armour", () -> new ItemStack(AoAArmour.AMETHIND_ARMOUR.chestplate.get()));
	public static final AoAItemGroup SWORDS = new AoAItemGroup("AoA Swords", "swords", () -> new ItemStack(AoAWeapons.AMETHYST_SWORD.get()));
	public static final AoAItemGroup GREATBLADES = new AoAItemGroup("AoA Greatblades", "greatblades", () -> new ItemStack(AoAWeapons.BLOODLURKER.get()));
	public static final AoAItemGroup MAULS = new AoAItemGroup("AoA Mauls", "mauls", () -> new ItemStack(AoAWeapons.HORIZON_MAUL.get()));
	public static final AoAItemGroup GUNS = new AoAItemGroup("AoA Guns", "guns", () -> new ItemStack(AoAWeapons.SQUAD_GUN.get()));
	public static final AoAItemGroup CANNONS = new AoAItemGroup("AoA Cannons", "cannons", () -> new ItemStack(AoAWeapons.BLAST_CANNON.get()));
	public static final AoAItemGroup SHOTGUNS = new AoAItemGroup("AoA Shotguns", "shotguns", () -> new ItemStack(AoAWeapons.BLAST_BARREL.get()));
	public static final AoAItemGroup SNIPERS = new AoAItemGroup("AoA Snipers", "snipers", () -> new ItemStack(AoAWeapons.BOLT_RIFLE.get()));
	public static final AoAItemGroup BLASTERS = new AoAItemGroup("AoA Blasters", "blasters", () -> new ItemStack(AoAWeapons.LASER_BLASTER.get()));
	public static final AoAItemGroup CROSSBOWS = new AoAItemGroup("AoA Crossbows", "crossbows", () -> new ItemStack(AoAWeapons.LUNAR_CROSSBOW.get()));
	public static final AoAItemGroup BOWS = new AoAItemGroup("AoA Bows", "bows", () -> new ItemStack(AoAWeapons.SPEED_BOW.get()));
	public static final AoAItemGroup THROWN_WEAPONS = new AoAItemGroup("AoA Thrown Weapons", "throwables", () -> new ItemStack(AoAWeapons.GRENADE.get()));
	public static final AoAItemGroup STAVES = new AoAItemGroup("AoA Staves", "staves", () -> new ItemStack(AoAWeapons.CELESTIAL_STAFF.get()));
	public static final AoAItemGroup VULCANES = new AoAItemGroup("AoA Vulcanes", "vulcanes", () -> new ItemStack(AoAWeapons.VULCANE.get()));
	public static final AoAItemGroup AMMUNITION = new AoAItemGroup("AoA Ammunition", "ammunition", () -> new ItemStack(AoAItems.SERENADIUM_BULLET.get()));

	public static final AoAItemGroup GENERATION_BLOCKS = new AoAItemGroup("AoA Worldgen Blocks", "generation", () -> new ItemStack(AoABlocks.LELYETIAN_GRASS.get()));
	public static final AoAItemGroup DECORATION_BLOCKS = new AoAItemGroup("AoA Decorative Blocks", "decoration", () -> new ItemStack(AoABlocks.BARON_BRICKS.get()));
	public static final AoAItemGroup FUNCTIONAL_BLOCKS = new AoAItemGroup("AoA Functional Blocks", "functional", () -> new ItemStack(AoABlocks.SHADOW_ALTAR.get()));
	public static final AoAItemGroup BANNERS = new AoAItemGroup("AoA Banners", "banners", () -> new ItemStack(AoABlocks.ANCIENT_BANNER.get()));
	public static final AoAItemGroup SPAWN_EGGS = new AoAItemGroup("AoA Spawn Eggs", "spawn_eggs", () -> new ItemStack(AoAItems.RUNIUM_CHUNK.get()));

	public static void captureItem(Item item, Item.Properties properties) {
		AoAItemGroup group = PENDING_PROPERTIES.remove(properties);

		if (group != null)
			ITEM_GROUPS.put(item, group);
	}

	public static void assignGroup(Item item, AoAItemGroup group) {
		ITEM_GROUPS.put(item, group);
	}

	@Nullable
	public static AoAItemGroup getGroup(Item item) {
		return ITEM_GROUPS.get(item);
	}

	public static class AoAItemGroup {
		private final String localeName;
		private final String id;
		private final Supplier<ItemStack> displayStack;
		private final RegistryObject<CreativeModeTab> tab;

		public AoAItemGroup(String name, String id, Supplier<ItemStack> iconStack) {
			this.localeName = name;
			this.id = id;
			this.displayStack = iconStack;
			this.tab = CREATIVE_TABS.register(id, () -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup." + AdventOfAscension.MOD_ID + "." + id))
					.icon(this.displayStack)
					.displayItems((params, output) -> {
						synchronized (ITEM_GROUPS) {
							for (Map.Entry<Item, AoAItemGroup> entry : ITEM_GROUPS.entrySet()) {
								if (entry.getValue() == this && ForgeRegistries.ITEMS.containsValue(entry.getKey()))
									output.accept(entry.getKey());
							}
						}
					})
					.build());

			REGISTERED_ITEM_GROUPS.add(this);
		}

		public Item.Properties properties() {
			Item.Properties properties = new Item.Properties();

			PENDING_PROPERTIES.put(properties, this);

			return properties;
		}

		public Item.Properties properties(Item.Properties properties) {
			PENDING_PROPERTIES.put(properties, this);

			return properties;
		}

		public String getLocaleName() {
			return this.localeName;
		}

		public String getId() {
			return this.id;
		}

		public ResourceLocation getTabId() {
			return AdventOfAscension.id(this.id);
		}

		public RegistryObject<CreativeModeTab> getTab() {
			return this.tab;
		}
	}
}
