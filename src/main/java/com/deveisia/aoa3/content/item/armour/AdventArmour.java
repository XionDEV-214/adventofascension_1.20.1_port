package com.deveisia.aoa3.content.item.armour;

import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.player.ServerPlayerDataManager;
import com.deveisia.aoa3.util.LocaleUtil;
import java.util.HashSet;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class AdventArmour extends ArmorItem {
	public AdventArmour(ArmorMaterial material, EquipmentSlot slot) {
		this(material, slot, Rarity.COMMON);
	}

	public AdventArmour(ArmorMaterial material, EquipmentSlot slot, Rarity rarity) {
		this(material, slot, AoAItemGroups.ARMOUR.properties().durability(material.getDurabilityForType(typeForSlot(slot))).rarity(rarity));
	}

	public AdventArmour(ArmorMaterial material, EquipmentSlot slot, Item.Properties properties) {
		super(material, typeForSlot(slot), properties);
	}

	public static ArmorItem.Type typeForSlot(EquipmentSlot slot) {
		return switch (slot) {
			case HEAD -> ArmorItem.Type.HELMET;
			case CHEST -> ArmorItem.Type.CHESTPLATE;
			case LEGS -> ArmorItem.Type.LEGGINGS;
			default -> ArmorItem.Type.BOOTS;
		};
	}

	public EquipmentSlot getSlot() {
		return getEquipmentSlot();
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return null;
	}

	public abstract Type setType();

	public void onEquip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {}

	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {}

	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {}

	public void onDamageDealt(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {}

	public void onPreAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingAttackEvent event) {}

	public void onAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {}

	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {}

	public void onPlayerLandingFall(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingFallEvent event) {}

	public void onPlayerDeath(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDeathEvent event) {}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	public boolean isSetHelmet(AdventArmour helmet) {
		return this.setType() == helmet.setType() || helmet.setType() == Type.ALL;
	}

	protected MutableComponent setEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_SET_HEADER, ChatFormatting.GOLD);
	}

	protected MutableComponent pieceEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_PIECE_HEADER, ChatFormatting.GRAY);
	}

	protected MutableComponent anySetEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_ANY_SET_HEADER, ChatFormatting.DARK_AQUA);
	}

	public boolean isHelmetAirTight(ServerPlayer player) {
		return false;
	}

	public enum Type {
		ALACRITY,
		ALL,
		AMETHIND,
		ARCHAIC,
		BARON,
		BATTLEBORN,
		BIOGENIC,
		BOREIC,
		CANDY,
		COMMANDER,
		CRYSTALLIS,
		ELECANYTE,
		EMBRODIUM,
		EXOPLATE,
		EXPLOSIVE,
		FUNGAL,
		GHASTLY,
		GHOULISH,
		HAZMAT,
		HYDRANGIC,
		HYDROPLATE,
		ICE,
		INFERNAL,
		INNERVATION,
		KNIGHT,
		LUNAR,
		LYNDAMYTE,
		LYONIC,
		MERCURIAL,
		NECRO,
		NETHENGEIC,
		NIGHTMARE,
		NONE,
		OMNI,
		PHANTASM,
		POISON,
		PREDATIOUS,
		PRIMORDIAL,
		PURITY,
		ROCKBONE,
		ROSIDIAN,
		RUNIC,
		SHARPSHOT,
		SKELETAL,
		SPACEKING,
		SPEED,
		SUBTERRANEAN,
		UTOPIAN,
		VOID,
		WEAKEN,
		WITHER,
		ZARGONITE;
	}
}
