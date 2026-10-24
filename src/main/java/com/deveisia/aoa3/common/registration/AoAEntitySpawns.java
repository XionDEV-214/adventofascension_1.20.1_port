package com.deveisia.aoa3.common.registration;

import net.minecraft.core.registries.Registries;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.content.entity.animal.ShinySquidEntity;
import com.deveisia.aoa3.content.entity.base.AbstractLavaFishEntity;
import com.deveisia.aoa3.content.world.spawner.TraderSpawner;
import com.deveisia.aoa3.util.EntityUtil;
import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.deveisia.aoa3.util.WorldUtil;
import java.util.*;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.Tags;
import static net.minecraft.world.entity.SpawnPlacements.Type.*;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
import static net.minecraft.world.level.levelgen.Heightmap.Types.OCEAN_FLOOR;

public final class AoAEntitySpawns {
	private static final ArrayList<SpawnEntry> SPAWN_ENTRIES = new ArrayList<SpawnEntry>();

    public static void registerEntitySpawns() {
        Logging.logStatusMessage("Registering entity spawns");

        populateOverworldSpawnEntries();

        setAbyssSpawnPlacements();
        setBarathosSpawnPlacements();
        setCandylandSpawnPlacements();
        setCeleveSpawnPlacements();
        setCreeponiaSpawnPlacements();
        setCrysteviaSpawnPlacements();
        setDeeplandsSpawnPlacements();
        setDustopiaSpawnPlacements();
        setGardenciaSpawnPlacements();
        setGreckonSpawnPlacements();
        setHavenSpawnPlacements();
        setIromineSpawnPlacements();
        populateNetherSpawnEntries();
        setLboreanSpawnPlacements();
        setLelyetiaSpawnPlacements();
        populateLunalusSpawnEntries();
        setMysteriumSpawnPlacements();
        setPrecasiaSpawnPlacements();
        setRunandorSpawnPlacements();
        setShyrelandsSpawnPlacements();
        setVoxPondsSpawnPlacements();

        new SpawnEntry(AoAEntities.Animals.AMBIENT_PIXON.get(), 12).groupSize(1, 4).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "haven"), ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "runic_cliffs"), ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "candy_hills"), ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "shyre_remnants")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
    }

    public static void populateOverworldSpawnEntries() {
        new SpawnEntry(AoAEntities.Mobs.BOMB_CARRIER.get(), 10).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.EVERBEAST.get(), 25).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.GHOST.get(), 85).groupSize(1, 2).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.MOTHER_VOID_WALKER.get(), 85).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SHADOW.get(), 85).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.TRICKSTER.get(), 60).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VOID_CHARGER.get(), 85).groupSize(1, 2).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VOID_WALKER.get(), 85).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.BUGEYE.get(), 90).groupSize(1, 2).matchBiomes(BiomeType.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.NIGHT_REAPER.get(), 55).groupSize(1, 2).matchBiomes(BiomeType.OVERWORLD).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.WOOD_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeType.FOREST).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.GOALBY.get(), 80).groupSize(1, 2).matchBiomes(BiomeType.MOUNTAIN).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.STONE_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeType.MOUNTAIN).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.YETI.get(), 70).groupSize(1, 2).matchBiomes(BiomeType.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SNOW_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeType.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.ICE_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeType.SNOWY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.DESERT_CHARGER.get(), 30).groupSize(1, 2).matchBiomes(BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FURLION.get(), 30).groupSize(1, 3).matchBiomes(BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SAND_GOLEM.get(), 30).groupSize(1, 2).matchBiomes(BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SPHINX.get(), 30).groupSize(1, 2).matchBiomes(BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SAND_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeType.SANDY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.CHIMERA.get(), 75).groupSize(1, 2).matchBiomes(BiomeType.SAVANNA).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.BONEBACK.get(), 75).groupSize(1, 2).matchBiomes(BiomeType.SAVANNA).placeMonster();
        new SpawnEntry(AoAEntities.Animals.ELKANYNE.get(), 20).groupSize(2, 4).matchBiomes(BiomeType.SAVANNA).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));

        new SpawnEntry(AoAEntities.Mobs.BUSH_BABY.get(), 70).groupSize(1, 2).matchBiomes(BiomeType.JUNGLE).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.CHOMPER.get(), 70).matchBiomes(BiomeType.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FISHIX.get(), 70).groupSize(1, 2).matchBiomes(BiomeType.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SWAMP_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeType.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.HAG.get(), 70).matchBiomes(BiomeType.SWAMP).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.HIDING_FUNGI.get(), 40).matchBiomes(BiomeType.CONIFEROUS).ignoreBiomes(BiomeType.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.LEAFY_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeType.CONIFEROUS).ignoreBiomes(BiomeType.SNOWY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.HILL_CHARGER.get(), 50).groupSize(1, 3).matchBiomes(BiomeType.MESA).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.MUCKOPEDE.get(), 50).matchBiomes(BiomeType.MESA).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.PINCHER.get(), 1).matchBiomes(BiomeType.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);

        new SpawnEntry(AoAEntities.Mobs.SEA_CHARGER.get(), 20).groupSize(1, 3).matchBiomes(BiomeType.BEACH).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SEA_TROLL.get(), 20).matchBiomes(BiomeType.BEACH).placeMonster();
        new SpawnEntry(AoAEntities.NPCs.TROLL_TRADER.get(), 1).matchBiomes(BiomeType.BEACH).placeNPC().specialSpawner(TraderSpawner::addSpawn);

        new SpawnEntry(AoAEntities.Mobs.CYCLOPS.get(), 20).groupSize(1, 4).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.CHARGER.get(), 20).groupSize(1, 2).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SASQUATCH.get(), 20).groupSize(1, 3).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.KING_CHARGER.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.GOBLIN.get(), 20).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.NIGHTFLY.get(), 20).groupSize(1, 3).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.HORNDRON.get(), 5).matchBiomes(BiomeType.PLAINS).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.ANCIENT_GOLEM.get(), 5).groupSize(1, 2).matchBiomes(BiomeType.PLAINS).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.DEAD_TREE.get(), 10).matchExactBiomes(ResourceLocation.parse("forest"), ResourceLocation.parse("wooded_hills")).placeMonster();

        new SpawnEntry(AoAEntities.Animals.SHINY_SQUID.get(), 1).groupSize(0, 1).matchBiomes(BiomeType.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, ShinySquidEntity::checkShinySquidSpawnRules);
        new SpawnEntry(AoAEntities.Animals.GLISTENING_PIXON.get(), 30).groupSize(0, 4).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        new SpawnEntry(AoAEntities.NPCs.ASSASSIN.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.NATURALIST.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.REALMSHIFTER.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.LOTTOMAN.get(), 1).matchBiomes(BiomeType.PLAINS, BiomeType.HILLS, BiomeType.FOREST).ignoreBiomes(BiomeType.SNOWY, BiomeType.CONIFEROUS, BiomeType.SAVANNA, BiomeType.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);

        setSpawnPlacement(AoAEntities.Animals.BLUE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.CANDLEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAEntities.Animals.CHARRED_CHAR.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAEntities.Animals.CHOCAW.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.CRIMSON_SKIPPER.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAEntities.Animals.CRIMSON_STRIPEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAEntities.Animals.DARK_HATCHETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.GREEN_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.HYDRONE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.IRONBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.JAMFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.PARAPIRANHA.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.PEARL_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.PURPLE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.RAINBOWFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.RAZORFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.RED_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.REEFTOOTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.ROCKETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.SAILBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.SAPPHIRE_STRIDER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.SKELECANTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.WHITE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.YELLOW_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.TURQUOISE_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAEntities.Animals.VIOLET_SKIPPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
    }

    private static void setAbyssSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ANEMIA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.APPARITION.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BLOODSUCKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DISTORTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FIEND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.OCCULENT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.WEB_REAPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "abyssal_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.Animals.SHINING_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "abyssal_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
    }

    public static void setBarathosSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARKBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CRYPTID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ECHODAR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.EMPEROR_BEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KEELER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.NOSPIKE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.PARASECT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RAMRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SQUIGGLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.THARAFLY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "barren_grounds")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCandylandSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.PEPPERMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.SPEARMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.AIRHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CANDY_CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CHERRY_BLASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LOLLYPOPPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "candy_hills")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCeleveSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.BOBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CHOCKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.JUMBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KOKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KRANKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SNAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STICKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STITCHES.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TIPSY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "celevian_highlands")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCreeponiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.CREEP_COW.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.BONE_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CAVE_CREEPOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPERLOCK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPIRD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPUPLE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HOST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KING_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MAGICAL_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.WINGED_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "creepoid_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCrysteviaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_FLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_MIND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_RANGE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_RESISTANCE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_SPEED.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_STRENGTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_TERROR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "crystevian_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setDeeplandsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.SHIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(Tags.Blocks.STONE, true));
        setSpawnPlacement(AoAEntities.Mobs.CASE_CONSTRUCT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CAVE_CREEP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DOUBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DWELLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCKBITER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCK_CRAWLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCK_CRITTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "cavern_depths")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setDustopiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.URKA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.BASILISK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DEVOURER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DUSTEIVA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DUSTON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LOST_SOUL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LURKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.Animals.RADIANT_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "dustopian_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "dustopian_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setGardenciaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARCHVINE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BROCCOHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CARROTOP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FLOWERFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SQUASHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SUNNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "floral_islands")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setGreckonSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.GRILLFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HUNTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SHIFTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SILENCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SKULL_CREATURE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SUGARFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.VALKYRIE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "greckon_mountains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setHavenSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.ANGELICA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.DAWNLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.EEO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.HALYCON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.RAINICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.VOLIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "haven")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setIromineSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.MECHACHRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MECHAMATON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.POLYTOM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.VOLTRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.METALLOID.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "iromine")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "iromine")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void populateNetherSpawnEntries() {
        new SpawnEntry(AoAEntities.Mobs.EMBRAKE.get(), 60).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest"), ResourceLocation.parse("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FLAMEWALKER.get(), 60).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest"), ResourceLocation.parse("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.INFERNAL.get(), 10).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest"), ResourceLocation.parse("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.LITTLE_BAM.get(), 20).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest"), ResourceLocation.parse("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SKELETAL_COWMAN.get(), 45).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SCRUBBY.get(), 30).groupSize(1, 3).matchBiomes(BiomeType.NETHER).ignoreExactBiomes(ResourceLocation.parse("basalt_deltas"), ResourceLocation.parse("warped_forest"), ResourceLocation.parse("soul_sand_valley")).placeMonster();

        new SpawnEntry(AoAEntities.NPCs.WITHERING_LOTTOMAN.get(), 1).matchBiomes(BiomeType.NETHER).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchBiomes(BiomeType.NETHER).placeNPC().specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setLboreanSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.CORATEE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(null, true));
        setSpawnPlacement(AoAEntities.Mobs.ANGLER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MUNCHER.get(), IN_WATER, OCEAN_FLOOR, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.NEPTUNO.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SEA_VIPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);

        new SpawnEntry(AoAEntities.Animals.GLOWING_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "coral_fields")).place(IN_WATER, OCEAN_FLOOR, SpawnPredicates.animalPredicate(null, true));
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "coral_fields")).place(IN_WATER, OCEAN_FLOOR, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setLelyetiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.TROTTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.FLYE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GROBBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LELYETIAN_CASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LELYETIAN_WARRIOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TRACKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.Animals.GLARING_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "lelyetian_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "lelyetian_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void populateLunalusSpawnEntries() {
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "asteroid_belt")).placeNPC().specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setMysteriumSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.FUNGAT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGUNG.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.Animals.BLOOMING_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "fungal_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "fungal_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setPrecasiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.MEGANEUROPSIS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.DEINOTHERIUM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DYREHORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GIANT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SABRETOOTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TERRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TORTIONE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "precasian_tall_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setRunandorSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARIEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BOUNCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.PALADIN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RUNICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RUNICORN_RIDER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.Animals.GLEAMING_PIXON.get(), 12).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "runic_cliffs")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "runic_cliffs")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setShyrelandsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARCBEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ARC_FLOWER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.AXIOLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.OMNILIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SHYRE_KNIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SOULVYRE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SOULSCORNE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STIMULO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STIMULOSUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SYSKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "shyre_remnants")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setVoxPondsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.NIGHT_WATCHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.ALARMO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CENTINEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DESTRUCTOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FISCHER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GADGETOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SLIMER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TOXXULOUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).matchExactBiomes(ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "vox_wastes")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void applySpawns(Holder<Biome> biome, MobSpawnSettingsBuilder spawns) {
        ResourceKey<Biome> biomeKey = biome.unwrapKey().orElse(null);

        if (biomeKey == null)
            return;

        for (SpawnEntry entry : SPAWN_ENTRIES) {
            if (entry.shouldSpawnInBiome(biome, biomeKey)) {
                MobSpawnSettings.SpawnerData spawnData = new MobSpawnSettings.SpawnerData(entry.entityType, entry.weight, Math.max(1, entry.minGroupSize), Math.max(1, entry.maxGroupSize));

                if (entry.specialSpawnerConsumer != null) {
                    entry.specialSpawnerConsumer.accept(biomeKey, spawnData);
                }
                else {
                    spawns.addSpawn(entry.entityType.getCategory(), spawnData);
                }
            }
        }
    }

    public enum BiomeType {
        OVERWORLD(BiomeTags.IS_OVERWORLD),
        NETHER(BiomeTags.IS_NETHER),
        BEACH(BiomeTags.IS_BEACH),
        CONIFEROUS(Tags.Biomes.IS_CONIFEROUS),
        FOREST(BiomeTags.IS_FOREST),
        HILLS(BiomeTags.IS_HILL),
        JUNGLE(BiomeTags.IS_JUNGLE),
        MESA(BiomeTags.IS_BADLANDS),
        MOUNTAIN(BiomeTags.IS_MOUNTAIN),
        OCEAN(BiomeTags.IS_OCEAN),
        PLAINS(Tags.Biomes.IS_PLAINS),
        SANDY(Tags.Biomes.IS_SANDY),
        SAVANNA(BiomeTags.IS_SAVANNA),
        SNOWY(Tags.Biomes.IS_SNOWY),
        SWAMP(Tags.Biomes.IS_SWAMP);

        private final TagKey<Biome> tag;

        BiomeType(TagKey<Biome> tag) {
            this.tag = tag;
        }

        public boolean matches(Holder<Biome> biome) {
            return biome.is(this.tag);
        }
    }

    public static class SpawnEntry {
        private final EntityType<? extends Mob> entityType;
        private final int weight;
        private int minGroupSize = 1;
        private int maxGroupSize = 1;
        @Nullable
        private HashSet<BiomeType> matchingBiomeTypes = null;
        @Nullable
        private HashSet<BiomeType> blacklistedBiomeTypes = null;
        @Nullable
        private HashSet<ResourceKey<Biome>> matchingBiomes = null;
        @Nullable
        private HashSet<ResourceKey<Biome>> ignoringBiomes = null;
        @Nullable
        private BiConsumer<ResourceKey<Biome>, MobSpawnSettings.SpawnerData> specialSpawnerConsumer = null;

        public SpawnEntry(EntityType<? extends Mob> entityType, int weight) {
            this.entityType = entityType;
            this.weight = weight;

            SPAWN_ENTRIES.add(this);
        }

        public EntityType<? extends Mob> getEntityType() {
            return this.entityType;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getMinGroupSize() {
            return this.minGroupSize;
        }

        public int getMaxGroupSize() {
            return this.maxGroupSize;
        }

        public HashSet<BiomeType> getMatchingBiomeTypes() {
            return this.matchingBiomeTypes;
        }

        public HashSet<BiomeType> getBlacklistedBiomeTypes() {
            return this.blacklistedBiomeTypes;
        }

        public HashSet<ResourceKey<Biome>> getMatchingBiomes() {
            return this.matchingBiomes;
        }

        public HashSet<ResourceKey<Biome>> getIgnoredBiomes() {
            return this.ignoringBiomes;
        }

        public SpawnEntry groupSize(int minGroupSize, int maxGroupSize) {
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = Math.max(minGroupSize, maxGroupSize);

            return this;
        }

        private SpawnEntry matchBiomes(BiomeType... biomeTypes) {
            this.matchingBiomeTypes = new HashSet<BiomeType>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry ignoreBiomes(BiomeType... biomeTypes) {
            this.blacklistedBiomeTypes = new HashSet<BiomeType>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry matchExactBiomes(ResourceLocation... biomeIds) {
            matchingBiomes = new HashSet<ResourceKey<Biome>>();

            for (ResourceLocation id : biomeIds) {
                matchingBiomes.add(ResourceKey.create(Registries.BIOME, id));
            }

            return this;
        }

        private SpawnEntry ignoreExactBiomes(ResourceLocation... biomeIds) {
            ignoringBiomes = new HashSet<ResourceKey<Biome>>();

            for (ResourceLocation id : biomeIds) {
                ignoringBiomes.add(ResourceKey.create(Registries.BIOME, id));
            }

            return this;
        }

        private SpawnEntry specialSpawner(BiConsumer<ResourceKey<Biome>, MobSpawnSettings.SpawnerData> specialSpawnerConsumer) {
            this.specialSpawnerConsumer = specialSpawnerConsumer;

            return this;
        }

        private boolean shouldSpawnInBiome(Holder<Biome> biome, ResourceKey<Biome> biomeKey) {
            if (matchingBiomes != null)
                return matchingBiomes.contains(biomeKey);

            if (ignoringBiomes != null && ignoringBiomes.contains(biomeKey))
                return false;

            if (biomeKey == Biomes.MUSHROOM_FIELDS)
                return false;

            if (matchingBiomeTypes != null) {
                if (matchingBiomeTypes.contains(BiomeType.NETHER)) {
                    if (!BiomeType.NETHER.matches(biome))
                        return false;
                }
                else if (!BiomeType.OVERWORLD.matches(biome)) {
                    return false;
                }

                if (blacklistedBiomeTypes != null) {
                    for (BiomeType type : blacklistedBiomeTypes) {
                        if (type.matches(biome))
                            return false;
                    }
                }

                for (BiomeType type : matchingBiomeTypes) {
                    if (type.matches(biome))
                        return true;
                }
            }

            return false;
        }

        private SpawnEntry placeNPC() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false));
        }

        private SpawnEntry placeMonster() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        }

        private SpawnEntry place(SpawnPlacements.Type type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
            setSpawnPlacement(entityType, type, heightmap, spawnPredicate);

    	    return this;
        }
	}

	private static <T extends Mob> void setSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
        try {
            if (SpawnPlacements.getPlacementType(entityType) == SpawnPlacements.Type.NO_RESTRICTIONS)
                SpawnPlacements.register(entityType, placementType, heightmap, (SpawnPlacements.SpawnPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Caught duplicate spawn placement registration from: " + RegistryUtil.getId(entityType).toString());
        }
    }

	private static final class SpawnPredicates {
        private static final SpawnPlacements.SpawnPredicate<Mob> npcPredicate(boolean spawnsInDarkness) {
            return (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                    return false;

                return spawnsInDarkness || WorldUtil.getLightLevel(world, pos, false, false) >= 8;
            };
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
              if (world.getDifficulty() == Difficulty.PEACEFUL)
                return false;

              boolean isVanillaWorld = WorldUtil.isWorld(world, Level.OVERWORLD, Level.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && !world.getLevel().isDay() && isVanillaWorld)
                    return false;

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!world.getBiome(pos).unwrapKey().map(key -> key.location().getNamespace().equals(AdventOfAscension.MOD_ID)).orElse(false) && !isVanillaWorld)
                    return false;
            }

            if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> WATER_MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (world.getDifficulty() == Difficulty.PEACEFUL)
                    return false;

            boolean isVanillaWorld = WorldUtil.isWorld(world, Level.OVERWORLD, Level.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (world.getLevel().isDay()) {
                    if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && isVanillaWorld)
                        return false;
                }
                else if (!world.dimensionType().hasFixedTime() && !world.getLevel().dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID) && AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type)) {
                    return false;
                }

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!world.getBiome(pos).unwrapKey().map(key -> key.location().getNamespace().equals(AdventOfAscension.MOD_ID)).orElse(false) && !isVanillaWorld)
                    return false;
            }

            if (!world.getBlockState(pos).getFluidState().getType().is(FluidTags.WATER))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static <T extends Mob> SpawnPlacements.SpawnPredicate<T> animalPredicate(@Nullable TagKey<Block> blockTag, boolean spawnsInDarkness) {
            return (EntityType<T> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (blockTag != null && !world.getBlockState(pos.below()).is(blockTag))
                    return false;

                return spawnsInDarkness || world.getRawBrightness(pos, 0) >= 8;
            };
        }

        private static final SpawnPlacements.SpawnPredicate<Mob> FISH = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
    }
}
