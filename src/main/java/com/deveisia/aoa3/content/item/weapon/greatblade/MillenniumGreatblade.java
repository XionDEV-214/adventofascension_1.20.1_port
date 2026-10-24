package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.util.RandomUtil;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.LocaleUtil;
import com.google.common.collect.ImmutableSetMultimap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.util.Lazy;

public class MillenniumGreatblade extends BaseGreatblade {
	private final double maxDmg = 39.75f;

	public MillenniumGreatblade() {
		super(13.25f, AttackSpeed.GREATBLADE, 2050);
	}

	@Override
	public double getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, double baseDamage) {
		return super.getDamageForAttack(target, attacker, swordStack, (float)(RandomUtil.RANDOM.source().nextFloat() * (maxDmg - baseDamage) + baseDamage) - 1);
	}

	@Override
	protected Lazy<ImmutableSetMultimap<Attribute, AttributeModifier>> buildDefaultAttributes() {
		return Lazy.of(() -> ImmutableSetMultimap.of(
				Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 0, AttributeModifier.Operation.ADDITION),
				Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION),
				ForgeMod.ENTITY_REACH.get(), new AttributeModifier(UUID.fromString("93bb7485-ce86-4e78-ab50-26f53d78ad9d"), "AoAGreatbladeReach", 1.5f, AttributeModifier.Operation.ADDITION)));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getDamage())), Component.literal(Double.toString(maxDmg))));
	}
}
