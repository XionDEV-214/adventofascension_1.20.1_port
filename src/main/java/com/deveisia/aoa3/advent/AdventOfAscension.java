package com.deveisia.aoa3.advent;

import com.deveisia.aoa3.client.AoAKeybinds;
import com.deveisia.aoa3.client.ClientOperations;
import com.deveisia.aoa3.client.event.ClientEventHandler;
import com.deveisia.aoa3.client.gui.hud.BossBarRenderer;
import com.deveisia.aoa3.client.gui.hud.HealthStatusRenderer;
import com.deveisia.aoa3.client.gui.hud.RecoilRenderer;
import com.deveisia.aoa3.client.gui.hud.XpParticlesRenderer;
import com.deveisia.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import com.deveisia.aoa3.client.gui.overlay.ScreenOverlayRenderer;
import com.deveisia.aoa3.client.model.ModelProperties;
import com.deveisia.aoa3.client.render.AoAGuiElementRenderers;
import com.deveisia.aoa3.client.render.CustomDimensionRenders;
import com.deveisia.aoa3.client.render.entity.EntityRenders;
import com.deveisia.aoa3.client.render.entity.misc.OccultBlockRenderer;
import com.deveisia.aoa3.command.AoACommand;
import com.deveisia.aoa3.command.StructuresCommand;
import com.deveisia.aoa3.command.argument.AoAResourceArgument;
import com.deveisia.aoa3.command.argument.AoASkillArgument;
import com.deveisia.aoa3.common.misc.NativePatching;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.registration.*;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.common.registration.worldgen.AoAFeatures;
import com.deveisia.aoa3.common.registration.worldgen.AoAStructureProcessors;
import com.deveisia.aoa3.common.registration.worldgen.AoAStructures;
import com.deveisia.aoa3.common.registration.worldgen.AoADensityFunctions;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.content.capability.CapabilitiesManager;
import com.deveisia.aoa3.data.server.AoAHaulingFishReloadListener;
import com.deveisia.aoa3.data.server.AoAResourcesReloadListener;
import com.deveisia.aoa3.data.server.AoASkillReqReloadListener;
import com.deveisia.aoa3.data.server.AoASkillsReloadListener;
import com.deveisia.aoa3.event.AoAPlayerEvents;
import com.deveisia.aoa3.event.EntityEvents;
import com.deveisia.aoa3.event.GlobalEvents;
import com.deveisia.aoa3.event.PlayerEvents;
import com.deveisia.aoa3.event.RestrictionEventHandler;
import com.deveisia.aoa3.integration.IntegrationManager;
import com.deveisia.aoa3.integration.gregtech.AoAGTMaterials;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.WebUtil;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import org.apache.logging.log4j.Level;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod(AdventOfAscension.MOD_ID)
public class AdventOfAscension {
	public static final String VERSION = "1.20.1-3.6.11";
	public static final String MOD_ID = "aoa3";

	public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES = DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, MOD_ID);
	public static final RegistryObject<ArgumentTypeInfo<?, ?>> STRUCTURE_ARGUMENT = ARGUMENT_TYPES.register("structure", () -> ArgumentTypeInfos.registerByClass(StructuresCommand.StructureIdArgument.class, SingletonArgumentInfo.contextFree(StructuresCommand.StructureIdArgument::instance)));
	public static final RegistryObject<ArgumentTypeInfo<?, ?>> SKILL_ARGUMENT = ARGUMENT_TYPES.register("skill", () -> ArgumentTypeInfos.registerByClass(AoASkillArgument.class, SingletonArgumentInfo.contextFree(AoASkillArgument::skill)));
	public static final RegistryObject<ArgumentTypeInfo<?, ?>> RESOURCE_ARGUMENT = ARGUMENT_TYPES.register("resource", () -> ArgumentTypeInfos.registerByClass(AoAResourceArgument.class, SingletonArgumentInfo.contextFree(AoAResourceArgument::resource)));

	public AdventOfAscension(FMLJavaModLoadingContext context) {
		IEventBus modBus = context.getModEventBus();

		Logging.logStatusMessage("!~ Advent of Ascension v" + VERSION + " ~!");
		initEventHandlers(modBus);
		NativePatching.doEarlyPatches();
		IntegrationManager.preInit();
		initRegistries(modBus);
		AoAGameRules.registerGameRules();
		AoAAdvancementTriggers.registerTriggers();
		AoALootOperations.init();
		try {
			Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(MOD_ID));
		}
		catch (IOException ex) {
			Logging.logMessage(Level.ERROR, "Unable to create config directory", ex);
		}

		context.registerConfig(ModConfig.Type.SERVER, AoAConfig.SERVER_CONFIG_SPEC, "aoa3_server_config.toml");
		context.registerConfig(ModConfig.Type.COMMON, AoAConfig.COMMON_CONFIG_SPEC, MOD_ID + File.separator + "common_config.toml");
		context.registerConfig(ModConfig.Type.CLIENT, AoAConfig.CLIENT_CONFIG_SPEC, MOD_ID + File.separator + "client_config.toml");
		context.registerConfig(ModConfig.Type.COMMON, AoAConfig.INTEGRATIONS_CONFIG_SPEC, MOD_ID + File.separator + "integrations_config.toml");
	}

	private static void commonSetup(final FMLCommonSetupEvent ev) {
		AoAPackets.registerPackets();
		ev.enqueueWork(AoAEntitySpawns::registerEntitySpawns);
		ev.enqueueWork(AoADispensables::registerDispenseBehaviours);
		ev.enqueueWork(AoAEntityData::init);
		PlayerUtil.init();
		ev.enqueueWork(NativePatching::postInit);
		ev.enqueueWork(IntegrationManager::postInit);
	}

	private static void clientSetup(final FMLClientSetupEvent ev) {
		ev.enqueueWork(() -> {
			AoABlocks.setCustomRenderTypes();
			AoATileEntities.registerRenderers();
			AoAContainers.registerContainerScreens();
			ModelProperties.init();
		});
		ClientEventHandler.init();
		AoAKeybinds.init();
		BossBarRenderer.init();
		RecoilRenderer.init();
		XpParticlesRenderer.init();
		ScreenOverlayRenderer.init();
		HealthStatusRenderer.init();
		ScopeOverlayRenderer.init();
		AoAGuiElementRenderers.init();
		OccultBlockRenderer.init();
	}

	private static void loadFinished(final FMLLoadCompleteEvent ev) {
		WebUtil.doHTTPTasks();
	}

	private static void registerCommands(final RegisterCommandsEvent ev) {
		AoACommand.registerSubCommands(ev.getDispatcher(), ev.getBuildContext());
	}

	private static void initRegistries(IEventBus modEventBus) {

		AoABlocks.BLOCKS.register(modEventBus);
		AoAItems.ITEMS.register(modEventBus);
		AoABlocks.BLOCK_ITEMS.register(modEventBus);
		AoABlocks.FLUIDS.register(modEventBus);
		AoABlocks.FLUID_TYPES.register(modEventBus);
		AoATileEntities.TILES.register(modEventBus);
		AoAWeapons.WEAPONS.register(modEventBus);
		AoASounds.SOUNDS.register(modEventBus);
		AoATools.TOOLS.register(modEventBus);
		AoAArmour.ARMOUR.register(modEventBus);
		AoAEnchantments.ENCHANTMENTS.register(modEventBus);
		AoAParticleTypes.PARTICLES.register(modEventBus);
		AoAContainers.CONTAINERS.register(modEventBus);
		AoARecipes.RECIPES.register(modEventBus);
		AoARecipes.RECIPE_TYPES.register(modEventBus);
		AoALootModifiers.LOOT_MODIFIERS.register(modEventBus);
		AoALootOperations.LOOT_FUNCTIONS.register(modEventBus);
		AoALootOperations.LOOT_CONDITIONS.register(modEventBus);
		AoAAttributes.ATTRIBUTES.register(modEventBus);
		AoAEntities.SPAWN_EGGS.register(modEventBus);
		AoAEntities.Mobs.MOBS.register(modEventBus);
		AoAEntities.Animals.ANIMALS.register(modEventBus);
		AoAEntities.NPCs.NPCS.register(modEventBus);
		AoAEntities.Misc.MISC.register(modEventBus);
		AoAEntities.Projectiles.PROJECTILES.register(modEventBus);
		AoAFeatures.Features.FEATURES.register(modEventBus);
		AoAFeatures.Carvers.CARVERS.register(modEventBus);
		AoAFeatures.Placements.PLACEMENTS.register(modEventBus);
		AoAStructures.STRUCTURE_TYPES.register(modEventBus);
		AoADensityFunctions.DENSITY_FUNCTIONS.register(modEventBus);
		AoAStructureProcessors.PROCESSORS.register(modEventBus);
		AoAWorldgenRegistries.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
		AoAProfessions.PROFESSIONS.register(modEventBus);
		AoASkills.SKILLS.register(modEventBus);
		AoAResources.RESOURCES.register(modEventBus);
		AoAAbilities.ABILITIES.register(modEventBus);
		AoABrainSensors.SENSORS.register(modEventBus);
		AoABrainMemories.MEMORIES.register(modEventBus);
		AoABannerPatterns.BANNER_PATTERNS.register(modEventBus);
		AoAItemGroups.CREATIVE_TABS.register(modEventBus);
		ARGUMENT_TYPES.register(modEventBus);
	}

	private static void initEventHandlers(IEventBus modBus) {
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		modBus.addListener(EventPriority.NORMAL, false, FMLCommonSetupEvent.class, AdventOfAscension::commonSetup);
		modBus.addListener(EventPriority.NORMAL, false, FMLClientSetupEvent.class, AdventOfAscension::clientSetup);
		modBus.addListener(EventPriority.NORMAL, false, FMLLoadCompleteEvent.class, AdventOfAscension::loadFinished);
		forgeBus.addListener(EventPriority.NORMAL, false, RegisterCommandsEvent.class, AdventOfAscension::registerCommands);
		forgeBus.addListener(EventPriority.NORMAL, false, AddReloadListenerEvent.class, AdventOfAscension::customDatapackLoaders);

		AoAGTMaterials.init(modBus);
		CapabilitiesManager.init(modBus);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			AoAKeybinds.preInit(modBus);
			EntityRenders.init(modBus);
			CustomDimensionRenders.init(modBus);
			modBus.addListener(ClientOperations::registerParticleFactories);
			modBus.addListener(ClientOperations::registerResourceListeners);
		});
		RestrictionEventHandler.preInit();
		PlayerEvents.preInit();
		GlobalEvents.preInit();
		EntityEvents.preInit();
		AoAPlayerEvents.preInit();
		AoAEntityData.preInit(modBus);
	}

	private static void customDatapackLoaders(final AddReloadListenerEvent ev) {
		ev.addListener(new AoAResourcesReloadListener());
		ev.addListener(new AoASkillReqReloadListener());
		ev.addListener(new AoASkillsReloadListener());
		ev.addListener(new AoAHaulingFishReloadListener());
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
