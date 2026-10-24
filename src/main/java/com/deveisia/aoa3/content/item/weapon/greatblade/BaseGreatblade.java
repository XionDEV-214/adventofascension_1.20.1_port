package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.common.registration.AoAEnchantments;
import com.deveisia.aoa3.common.registration.AoAItemGroups;
import com.deveisia.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import com.deveisia.aoa3.content.item.weapon.sword.BaseSword;
import com.deveisia.aoa3.library.compat.Material;
import com.deveisia.aoa3.library.compat.ToolType;
import com.deveisia.aoa3.util.ItemUtil;
import com.google.common.collect.ImmutableSetMultimap;
import java.util.UUID;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class BaseGreatblade extends BaseSword {
	public BaseGreatblade(final double baseDmg, final double attackSpeed, final int durability) {
		this(baseDmg, attackSpeed, durability, Rarity.COMMON);
	}

	public BaseGreatblade(final double baseDmg, final double attackSpeed, final int durability, Rarity rarity) {
		super(ItemUtil.customItemTier(durability, (float)attackSpeed, (float)baseDmg, 4, 8, null),
				AoAItemGroups.GREATBLADES.properties().durability(durability).rarity(rarity));
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		if (attacker.fallDistance > 0 && !attacker.onGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.isPassenger() && !attacker.hasEffect(MobEffects.BLINDNESS) && VolatileStackCapabilityProvider.getOrDefault(swordStack, Direction.NORTH).getValue() >= 1)
			baseDamage += 1.15f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SEVER.get(), swordStack);

		return baseDamage;
	}

	@Override
	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				ForgeMod.ENTITY_REACH.get(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 1.5f, AttributeModifier.Operation.ADDITION)));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = Material.of(state);

		if (material == Material.WEB) {
			return 25.0f;
		}
		else if (material == Material.PLANT || material == Material.REPLACEABLE_PLANT || state.is(BlockTags.CORALS) || state.is(BlockTags.WALL_CORALS) || state.is(BlockTags.CORAL_BLOCKS) || material == Material.LEAVES || material == Material.VEGETABLE) {
			return 2.0f;
		}
		else {
			return 1.0f;
		}
	}
}
