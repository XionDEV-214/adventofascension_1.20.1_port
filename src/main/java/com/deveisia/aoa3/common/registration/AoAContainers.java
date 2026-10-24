package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.gui.container.*;
import com.deveisia.aoa3.common.container.*;
import com.deveisia.aoa3.content.entity.npc.banker.AoABanker;
import com.deveisia.aoa3.content.entity.npc.trader.CorruptedTravellerEntity;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoAContainers {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<MenuType<MendingTableContainer>> MENDING_TABLE = registerContainer("mending_table", (screenId, inventory, buffer) -> new MendingTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final RegistryObject<MenuType<WhitewashingTableContainer>> WHITEWASHING_TABLE = registerContainer("whitewashing_table", (screenId, inventory, buffer) -> new WhitewashingTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final RegistryObject<MenuType<DivineStationContainer>> DIVINE_STATION = registerContainer("divine_station", (screenId, inventory, buffer) -> new DivineStationContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final RegistryObject<MenuType<FrameBenchContainer>> FRAME_BENCH = registerContainer("frame_bench", (screenId, inventory, buffer) -> new FrameBenchContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));
	public static final RegistryObject<MenuType<InfusionTableContainer>> INFUSION_TABLE = registerContainer("infusion_table", (screenId, inventory, buffer) -> new InfusionTableContainer(screenId, inventory, ContainerLevelAccess.create(inventory.player.level(), buffer.readBlockPos())));

	public static final RegistryObject<MenuType<MerchantMenu>> TRADER = registerContainer("trader", (screenId, inventory, buffer) -> new MerchantMenu(screenId, inventory, (Merchant)inventory.player.level().getEntity(buffer.readInt())));
	public static final RegistryObject<MenuType<CorruptedTravellerContainer>> CORRUPTED_TRAVELLER = registerContainer("corrupted_traveller", (screenId, inventory, buffer) -> new CorruptedTravellerContainer(screenId, inventory, (CorruptedTravellerEntity)inventory.player.level().getEntity(buffer.readInt())));
	public static final RegistryObject<MenuType<BankerContainer>> BANKER = registerContainer("banker", (screenId, inventory, buffer) -> new BankerContainer(screenId, inventory, (AoABanker)inventory.player.level().getEntity(buffer.readInt())));

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerContainer(String id, IContainerFactory<T> factory) {
		return CONTAINERS.register(id, () -> IForgeMenuType.create(factory));
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerContainerScreens() {
		MenuScreens.register(DIVINE_STATION.get(), DivineStationScreen::new);
		MenuScreens.register(MENDING_TABLE.get(), UtilityBlockScreen::new);
		MenuScreens.register(WHITEWASHING_TABLE.get(), UtilityBlockScreen::new);
		MenuScreens.register(FRAME_BENCH.get(), FrameBenchScreen::new);
		MenuScreens.register(INFUSION_TABLE.get(), InfusionTableScreen::new);
		MenuScreens.register(TRADER.get(), MerchantScreen::new);
		MenuScreens.register(BANKER.get(), BankerScreen::new);
		MenuScreens.register(CORRUPTED_TRAVELLER.get(), CorruptedTravellerScreen::new);
	}
}
