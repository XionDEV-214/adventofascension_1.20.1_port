package com.deveisia.aoa3.content.item.weapon.sword;

import net.minecraftforge.registries.ForgeRegistries;
import com.deveisia.aoa3.common.registration.AoATags;
import com.deveisia.aoa3.library.constant.AttackSpeed;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SweetSword extends BaseSword {
	private static final ArrayList<ItemStack> candyList = new ArrayList<ItemStack>();
	private static boolean populated = false;

	public SweetSword() {
		super(ItemUtil.customItemTier(1850, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (RandomUtil.percentChance(0.2f * attackCooldown)) {
			if (!populated)
				populateCandyList();

			target.spawnAtLocation(candyList.get(RandomUtil.RANDOM.source().nextInt(candyList.size())), target.getBbHeight() / 2f);
		}
	}

	private static void populateCandyList() {
		candyList.add(new ItemStack(Items.SUGAR, 3));

		ForgeRegistries.ITEMS.tags().getTag(AoATags.Items.CANDY).forEach(item -> candyList.add(new ItemStack(item)));

		populated = true;
	}

	public static void addCandyDrop(ItemStack stack) {
		if (!populated)
			populateCandyList();

		candyList.add(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
