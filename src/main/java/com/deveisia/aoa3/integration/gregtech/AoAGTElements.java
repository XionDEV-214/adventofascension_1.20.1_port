package com.deveisia.aoa3.integration.gregtech;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.common.data.GTElements;

public final class AoAGTElements {
	private static final String MARK = "\uA82A";

	public static Element SERENADIUM;
	public static Element ROSITE;
	public static Element EMBERSTONE;
	public static Element VARSIUM;
	public static Element BARONYTE;
	public static Element BLAZIUM;
	public static Element MYSTITE;
	public static Element LYON;
	public static Element GHASTLY;
	public static Element GHOULISH;
	public static Element ELECANIUM;
	public static Element SHYRESTONE;
	public static Element JADE;
	public static Element BLOODSTONE;
	public static Element GEMENYTE;
	public static Element JEWELYTE;
	public static Element ORNAMYTE;
	public static Element CRYSTALLITE;
	public static Element SHYREGEM;
	public static Element ABYSSAL_STONE;
	public static Element BARON_STONE;
	public static Element HELLSTONE;
	public static Element DENSE_STONE;
	public static Element CREEP_STONE;
	public static Element CRYSTALLISED_ROCK;
	public static Element FUNGAL_ROCK;
	public static Element GRECKON_STONE;
	public static Element WEIGHTLESS_STONE;
	public static Element IROSTONE;
	public static Element PRECASIAN_LOWER_ROCK;
	public static Element RUNIC_STONE;
	public static Element BRIGHT_ROCK;

	public static void init() {
		SERENADIUM = create(25, 31, "Serenadium", "Sd");
		ROSITE = create(28, 35, "Rosite", "Rs");
		EMBERSTONE = create(31, 39, "Emberstone", "Em");
		VARSIUM = create(35, 44, "Varsium", "Vs");
		BARONYTE = create(39, 49, "Baronyte", "Bn");
		BLAZIUM = create(37, 47, "Blazium", "Bz");
		MYSTITE = create(42, 52, "Mystite", "My");
		LYON = create(45, 56, "Lyon", "Ly");
		GHASTLY = create(48, 60, "Ghastly", "Gh");
		GHOULISH = create(48, 61, "Ghoulish", "Gl");
		ELECANIUM = create(52, 66, "Elecanium", "El");
		SHYRESTONE = create(57, 72, "Shyrestone", "Sy");
		JADE = create(27, 33, "Jade", "Jd");
		BLOODSTONE = create(33, 41, "Bloodstone", "Bd");
		GEMENYTE = create(38, 47, "Gemenyte", "Gm");
		JEWELYTE = create(38, 48, "Jewelyte", "Jw");
		ORNAMYTE = create(40, 50, "Ornamyte", "Om");
		CRYSTALLITE = create(44, 55, "Crystallite", "Cy");
		SHYREGEM = create(55, 70, "Shyregem", "Sg");
		ABYSSAL_STONE = create(40, 42, "Abyssal Stone", "Ab");
		BARON_STONE = create(38, 42, "Baron Stone", "Bs");
		HELLSTONE = create(40, 44, "Hellstone", "Hs");
		DENSE_STONE = create(36, 40, "Dense Stone", "Ds");
		CREEP_STONE = create(26, 28, "Creep Stone", "Cp");
		CRYSTALLISED_ROCK = create(30, 30, "Crystallised Rock", "Cx");
		FUNGAL_ROCK = create(28, 29, "Fungal Rock", "Fg");
		GRECKON_STONE = create(35, 38, "Greckon Stone", "Gk");
		WEIGHTLESS_STONE = create(27, 28, "Weightless Stone", "Wl");
		IROSTONE = create(36, 42, "Irostone", "Is");
		PRECASIAN_LOWER_ROCK = create(35, 38, "Precasian Lower Rock", "Pc");
		RUNIC_STONE = create(33, 36, "Runic Stone", "Rn");
		BRIGHT_ROCK = create(30, 31, "Bright Rock", "Br");
	}

	private static Element create(long protons, long neutrons, String name, String symbol) {
		return GTElements.createAndRegister(protons, neutrons, -1, null, name, symbol + MARK, false);
	}
}
