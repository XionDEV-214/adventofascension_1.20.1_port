package com.deveisia.aoa3.integration.gregtech;

import com.gregtechceu.gtceu.GTCEu;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public final class AoAGTMaterials {
	private static final Set<Material> AOA_ORE_MATERIALS = Collections.newSetFromMap(new IdentityHashMap<>());
	private static final List<Material> STONE_MATERIALS = new ArrayList<>();

	public static Material SERENADIUM;
	public static Material ROSITE;
	public static Material BARONYTE;
	public static Material BLAZIUM;
	public static Material ELECANIUM;
	public static Material EMBERSTONE;
	public static Material GHASTLY;
	public static Material GHOULISH;
	public static Material LYON;
	public static Material MYSTITE;
	public static Material SHYRESTONE;
	public static Material VARSIUM;

	public static Material AMETHYST;
	public static Material SAPPHIRE;
	public static Material BLOODSTONE;
	public static Material CRYSTALLITE;
	public static Material GEMENYTE;
	public static Material JADE;
	public static Material JEWELYTE;
	public static Material ORNAMYTE;
	public static Material SHYREGEM;

	public static Material ABYSSAL_STONE;
	public static Material BARON_STONE;
	public static Material HELLSTONE;
	public static Material DENSE_STONE;
	public static Material CREEP_STONE;
	public static Material CRYSTALLISED_ROCK;
	public static Material FUNGAL_ROCK;
	public static Material GRECKON_STONE;
	public static Material WEIGHTLESS_STONE;
	public static Material IROSTONE;
	public static Material PRECASIAN_LOWER_ROCK;
	public static Material RUNIC_STONE;
	public static Material BRIGHT_ROCK;

	public static void init(IEventBus modBus) {
		modBus.addListener(AoAGTMaterials::onMaterialRegistration);
		modBus.addListener(AoAGTMaterials::onPostMaterial);
	}

	private static void onMaterialRegistration(final MaterialEvent ev) {
		SERENADIUM = metal("serenadium", 0xff9c5d, 0xff5174, 1, METALLIC, ToolProperty.Builder.of(2.5F, 2.0F, 224, 2).enchantability(10).addTypes(GTToolType.MORTAR).build()).element(AoAGTElements.SERENADIUM).addOreByproducts(GTMaterials.Iron, GTMaterials.Nickel).buildAndRegister();
		ROSITE = metal("rosite", 0xff7f75, 0xaa3958, 2, SHINY, ToolProperty.Builder.of(4.0F, 2.5F, 384, 2).enchantability(14).build()).element(AoAGTElements.ROSITE).addOreByproducts(GTMaterials.Copper, GTMaterials.Redstone).buildAndRegister();
		EMBERSTONE = metal("emberstone", 0xd4886b, 0x9b6c78, 3, METALLIC, ToolProperty.Builder.of(5.0F, 3.5F, 512, 3).enchantability(10).enchantment(Enchantments.FIRE_ASPECT, 1).build()).element(AoAGTElements.EMBERSTONE).addOreByproducts(GTMaterials.Sulfur, GTMaterials.Blaze).buildAndRegister();
		BARONYTE = metal("baronyte", 0xff7b54, 0x9d1a34, 3, SHINY, ToolProperty.Builder.of(6.0F, 4.0F, 768, 3).enchantability(21).build()).element(AoAGTElements.BARONYTE).addOreByproducts(GTMaterials.Iron, GTMaterials.Copper).buildAndRegister();
		BLAZIUM = metal("blazium", 0xffb966, 0xbf363b, 3, BRIGHT, ToolProperty.Builder.of(7.0F, 4.5F, 640, 3).enchantability(18).enchantment(Enchantments.FIRE_ASPECT, 2).build()).element(AoAGTElements.BLAZIUM).addOreByproducts(GTMaterials.Blaze, GTMaterials.Sulfur).buildAndRegister();
		VARSIUM = metal("varsium", 0xffa874, 0xb14b6a, 3, METALLIC, ToolProperty.Builder.of(5.0F, 3.0F, 640, 3).enchantability(12).addTypes(GTToolType.MORTAR).build()).element(AoAGTElements.VARSIUM).addOreByproducts(GTMaterials.Iron, GTMaterials.Tin).buildAndRegister();
		ELECANIUM = metal("elecanium", 0x57debc, 0x4c788f, 3, SHINY, ToolProperty.Builder.of(8.0F, 6.0F, 1536, 3).enchantability(21).magnetic().build()).element(AoAGTElements.ELECANIUM).addOreByproducts(GTMaterials.Redstone, GTMaterials.Lapis).buildAndRegister();
		GHASTLY = metal("ghastly", 0xd3ff8e, 0x4aac5d, 3, DULL, ToolProperty.Builder.of(7.0F, 5.5F, 1152, 3).attackSpeed(0.1F).enchantability(21).enchantment(Enchantments.SMITE, 2).build()).element(AoAGTElements.GHASTLY).addOreByproducts(GTMaterials.Bone, GTMaterials.Saltpeter).buildAndRegister();
		GHOULISH = metal("ghoulish", 0x93a8ff, 0x8c57ff, 3, DULL, ToolProperty.Builder.of(6.5F, 6.0F, 1152, 3).attackSpeed(0.1F).enchantability(21).enchantment(Enchantments.SMITE, 2).build()).element(AoAGTElements.GHOULISH).addOreByproducts(GTMaterials.Bone, GTMaterials.Phosphorus).buildAndRegister();
		LYON = metal("lyon", 0xffda8d, 0xae877d, 3, METALLIC, ToolProperty.Builder.of(7.0F, 5.0F, 1024, 3).enchantability(14).build()).element(AoAGTElements.LYON).addOreByproducts(GTMaterials.Iron, GTMaterials.Gold).buildAndRegister();
		MYSTITE = metal("mystite", 0xacffd5, 0x468d84, 3, SHINY, ToolProperty.Builder.of(6.0F, 4.0F, 896, 3).enchantability(25).build()).element(AoAGTElements.MYSTITE).addOreByproducts(GTMaterials.Phosphorus, GTMaterials.Glowstone).buildAndRegister();
		SHYRESTONE = metal("shyrestone", 0x8acbff, 0x5569e5, 3, BRIGHT, ToolProperty.Builder.of(9.0F, 6.5F, 1792, 3).attackSpeed(0.1F).enchantability(25).build()).element(AoAGTElements.SHYRESTONE).addOreByproducts(GTMaterials.Glowstone, GTMaterials.Silver).buildAndRegister();

		AMETHYST = GTMaterials.Amethyst;
		SAPPHIRE = GTMaterials.Sapphire;
		BLOODSTONE = gem("bloodstone", 0xff7253, 0xa73b5f, 3, RUBY, ToolProperty.Builder.of(6.0F, 5.0F, 640, 3).enchantability(18).build()).element(AoAGTElements.BLOODSTONE).addOreByproducts(GTMaterials.Iron, GTMaterials.Redstone).buildAndRegister();
		CRYSTALLITE = gem("crystallite", 0xffb961, 0xff5841, 3, DIAMOND, ToolProperty.Builder.of(7.0F, 5.5F, 896, 3).enchantability(18).build()).element(AoAGTElements.CRYSTALLITE).addOreByproducts(GTMaterials.Quartzite, GTMaterials.Glowstone).buildAndRegister();
		GEMENYTE = gem("gemenyte", 0x33ff80, 0x00ff54, 3, EMERALD, ToolProperty.Builder.of(6.0F, 4.5F, 704, 3).enchantability(25).build()).element(AoAGTElements.GEMENYTE).addOreByproducts(GTMaterials.Emerald, GTMaterials.Saltpeter).buildAndRegister();
		JADE = gem("jade", 0x96ff85, 0x5da39f, 2, GEM_VERTICAL, ToolProperty.Builder.of(5.0F, 3.0F, 448, 2).enchantability(14).build()).element(AoAGTElements.JADE).addOreByproducts(GTMaterials.Olivine, GTMaterials.Calcite).buildAndRegister();
		JEWELYTE = gem("jewelyte", 0x2ef657, 0x007722, 3, GEM_HORIZONTAL, ToolProperty.Builder.of(6.5F, 4.0F, 704, 3).enchantability(25).build()).element(AoAGTElements.JEWELYTE).addOreByproducts(GTMaterials.Emerald, GTMaterials.Sulfur).buildAndRegister();
		ORNAMYTE = gem("ornamyte", 0xc9c9c9, 0x9c9c9c, 3, GEM_HORIZONTAL, ToolProperty.Builder.of(5.5F, 5.0F, 832, 3).enchantability(20).build()).element(AoAGTElements.ORNAMYTE).addOreByproducts(GTMaterials.Carbon, GTMaterials.Saltpeter).buildAndRegister();
		SHYREGEM = gem("shyregem", 0x43fffb, 0x03fffd, 3, DIAMOND, ToolProperty.Builder.of(8.5F, 6.5F, 1280, 3).attackSpeed(0.1F).enchantability(25).enchantment(Enchantments.BLOCK_FORTUNE, 1).build()).element(AoAGTElements.SHYREGEM).addOreByproducts(GTMaterials.Diamond, GTMaterials.Glowstone).buildAndRegister();

		ABYSSAL_STONE = stone("abyssal_stone", 0x331316, AoAGTElements.ABYSSAL_STONE, GTMaterials.SiliconDioxide, 2, GTMaterials.Obsidian, 1, GTMaterials.Carbon, 1);
		BARON_STONE = stone("baron_stone", 0x754848, AoAGTElements.BARON_STONE, GTMaterials.SiliconDioxide, 2, GTMaterials.Iron, 1, GTMaterials.Copper, 1);
		HELLSTONE = stone("hellstone", 0x7d2c34, AoAGTElements.HELLSTONE, GTMaterials.Netherrack, 2, GTMaterials.Sulfur, 1, GTMaterials.Redstone, 1);
		DENSE_STONE = stone("dense_stone", 0x7b7977, AoAGTElements.DENSE_STONE, GTMaterials.Basalt, 2, GTMaterials.Magnesium, 1, GTMaterials.Iron, 1);
		CREEP_STONE = stone("creep_stone", 0x234b22, AoAGTElements.CREEP_STONE, GTMaterials.SiliconDioxide, 1, GTMaterials.Saltpeter, 1, GTMaterials.Sulfur, 1, GTMaterials.Carbon, 1);
		CRYSTALLISED_ROCK = stone("crystallised_rock", 0x342145, AoAGTElements.CRYSTALLISED_ROCK, GTMaterials.SiliconDioxide, 3, GTMaterials.Quartzite, 1);
		FUNGAL_ROCK = stone("fungal_rock", 0x37274a, AoAGTElements.FUNGAL_ROCK, GTMaterials.SiliconDioxide, 2, GTMaterials.Phosphorus, 1, GTMaterials.Carbon, 1);
		GRECKON_STONE = stone("greckon_stone", 0x4b3044, AoAGTElements.GRECKON_STONE, GTMaterials.Stone, 2, GTMaterials.Bone, 1, GTMaterials.Calcite, 1);
		WEIGHTLESS_STONE = stone("weightless_stone", 0x725fae, AoAGTElements.WEIGHTLESS_STONE, GTMaterials.SiliconDioxide, 2, GTMaterials.Aluminium, 1, GTMaterials.Magnesium, 1);
		IROSTONE = stone("irostone", 0x201b19, AoAGTElements.IROSTONE, GTMaterials.Iron, 2, GTMaterials.Carbon, 1, GTMaterials.SiliconDioxide, 1);
		PRECASIAN_LOWER_ROCK = stone("precasian_lower_rock", 0x2b2423, AoAGTElements.PRECASIAN_LOWER_ROCK, GTMaterials.Stone, 2, GTMaterials.Calcite, 1, GTMaterials.Bone, 1);
		RUNIC_STONE = stone("runic_stone", 0x263e68, AoAGTElements.RUNIC_STONE, GTMaterials.SiliconDioxide, 2, GTMaterials.Lapis, 1, GTMaterials.Redstone, 1);
		BRIGHT_ROCK = stone("bright_rock", 0xc1783a, AoAGTElements.BRIGHT_ROCK, GTMaterials.SiliconDioxide, 2, GTMaterials.Glowstone, 1, GTMaterials.Calcite, 1);

		Collections.addAll(AOA_ORE_MATERIALS, SERENADIUM, ROSITE, EMBERSTONE, BARONYTE, BLAZIUM, VARSIUM, ELECANIUM, GHASTLY, GHOULISH, LYON, MYSTITE, SHYRESTONE,
				AMETHYST, SAPPHIRE, BLOODSTONE, CRYSTALLITE, GEMENYTE, JADE, JEWELYTE, ORNAMYTE, SHYREGEM);
	}

	private static void onPostMaterial(final PostMaterialEvent ev) {
		for (TagPrefix prefix : AoAOrePrefixes.ALL) {
			for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
				for (Material material : registry.getAllMaterials()) {
					if (!AOA_ORE_MATERIALS.contains(material))
						prefix.setIgnored(material);
				}
			}
		}
	}

	private static Material.Builder metal(String id, int colour, int secondaryColour, int harvestLevel, MaterialIconSet iconSet, ToolProperty tools) {
		return new Material.Builder(GTCEu.id(id))
				.ingot(harvestLevel).ore()
				.color(colour).secondaryColor(secondaryColour).iconSet(iconSet)
				.toolStats(tools)
				.flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, GENERATE_BOLT_SCREW);
	}

	private static Material.Builder gem(String id, int colour, int secondaryColour, int harvestLevel, MaterialIconSet iconSet, ToolProperty tools) {
		return new Material.Builder(GTCEu.id(id))
				.gem(harvestLevel).ore()
				.color(colour).secondaryColor(secondaryColour).iconSet(iconSet)
				.toolStats(tools)
				.flags(NO_SMASHING, GENERATE_PLATE, GENERATE_LENS);
	}

	private static Material stone(String id, int colour, Element element, Object... components) {
		Material material = new Material.Builder(GTCEu.id(id))
				.dust()
				.element(element)
				.color(colour).iconSet(ROUGH)
				.flags(NO_SMASHING, DECOMPOSITION_BY_CENTRIFUGING)
				.components(components)
				.buildAndRegister();

		STONE_MATERIALS.add(material);

		return material;
	}

	public static boolean isAoAOreMaterial(Material material) {
		return AOA_ORE_MATERIALS.contains(material);
	}

	public static List<Material> getStoneMaterials() {
		return Collections.unmodifiableList(STONE_MATERIALS);
	}
}
