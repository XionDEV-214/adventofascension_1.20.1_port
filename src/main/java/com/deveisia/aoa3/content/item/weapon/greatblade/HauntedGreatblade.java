package com.deveisia.aoa3.content.item.weapon.greatblade;

import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class HauntedGreatblade extends BaseGreatblade {
	public HauntedGreatblade() {
		super(26, AttackSpeed.GREATBLADE, 1875);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (!world.isClientSide && isSelected && entity instanceof Player && RandomUtil.oneInNChance(12000)) {
			MobEffect effect = MobEffect.byId(RandomUtil.RANDOM.source().nextInt(ForgeRegistries.MOB_EFFECTS.getValues().size()));

			while (effect == null) {
				effect = MobEffect.byId(RandomUtil.RANDOM.source().nextInt(ForgeRegistries.MOB_EFFECTS.getValues().size()));
			}

			((Player)entity).addEffect(new MobEffectInstance(effect, 600, 0, false, true));

			MutableComponent component = LocaleUtil.getLocaleMessage("item.aoa3.haunted_greatblade.message." + (1 + RandomUtil.RANDOM.source().nextInt(16)), ChatFormatting.DARK_PURPLE);

			component.getStyle().withItalic(true);
			entity.sendSystemMessage(component);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
