package com.deveisia.aoa3.util;

import com.deveisia.aoa3.common.registration.AoARegistries;
import com.deveisia.aoa3.player.ability.AoAAbility;
import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.player.skill.AoASkill;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public final class RegistryUtil {
	@Nullable
	public static ResourceLocation getId(Object object) {
		if (object == null)
			return null;

		if (object instanceof Item item)
			return ForgeRegistries.ITEMS.getKey(item);

		if (object instanceof Block block)
			return ForgeRegistries.BLOCKS.getKey(block);

		if (object instanceof ItemLike itemLike)
			return ForgeRegistries.ITEMS.getKey(itemLike.asItem());

		if (object instanceof EntityType<?> entityType)
			return ForgeRegistries.ENTITY_TYPES.getKey(entityType);

		if (object instanceof MobEffect effect)
			return ForgeRegistries.MOB_EFFECTS.getKey(effect);

		if (object instanceof Enchantment enchantment)
			return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);

		if (object instanceof SoundEvent sound)
			return sound.getLocation();

		if (object instanceof Fluid fluid)
			return ForgeRegistries.FLUIDS.getKey(fluid);

		if (object instanceof BlockEntityType<?> blockEntityType)
			return ForgeRegistries.BLOCK_ENTITY_TYPES.getKey(blockEntityType);

		if (object instanceof Potion potion)
			return ForgeRegistries.POTIONS.getKey(potion);

		if (object instanceof ParticleType<?> particleType)
			return ForgeRegistries.PARTICLE_TYPES.getKey(particleType);

		if (object instanceof MenuType<?> menuType)
			return ForgeRegistries.MENU_TYPES.getKey(menuType);

		if (object instanceof Attribute attribute)
			return ForgeRegistries.ATTRIBUTES.getKey(attribute);

		if (object instanceof VillagerProfession profession)
			return ForgeRegistries.VILLAGER_PROFESSIONS.getKey(profession);

		if (object instanceof AoASkill skill)
			return AoARegistries.SKILLS.get().getKey(skill);

		if (object instanceof AoAResource resource)
			return AoARegistries.RESOURCES.get().getKey(resource);

		if (object instanceof AoAAbility ability)
			return AoARegistries.ABILITIES.get().getKey(ability);

		return null;
	}

	public static String getIdString(Object object) {
		ResourceLocation id = getId(object);

		return id == null ? "" : id.toString();
	}
}
