package com.deveisia.aoa3.player.ability;

import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.client.ClientOperations;
import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.SyncAoAAbilityDataPacket;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.event.custom.events.PlayerChangeXpEvent;
import com.deveisia.aoa3.library.object.DynamicTextComponent;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.ItemUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class XpRestrictedFreeItem extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_SKILL_XP};

	private final float xpPerItem;
	private final Item item;
	private final int stackSize;
	private float xpRemaining = 50000;

	public XpRestrictedFreeItem(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.XP_RESTRICTED_FREE_ITEM.get(), skill, data);

		this.xpPerItem = GsonHelper.getAsFloat(data, "xp_per_item", 50000);
		this.item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(GsonHelper.getAsString(data, "item")));
		this.stackSize = GsonHelper.getAsInt(data, "amount", 1);
	}

	public XpRestrictedFreeItem(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.XP_RESTRICTED_FREE_ITEM.get(), skill, data);

		this.xpPerItem = data.getFloat("xp_per_item");
		this.item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(data.getString("item")));
		this.xpRemaining = data.getFloat("xp_remaining");
		this.stackSize = data.getInt("amount");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		if (this.xpRemaining <= 0) {
			super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription) + ".ready", this.stackSize, item.getDefaultInstance().getHoverName(), NumberUtil.roundToNthDecimalPlace(this.xpPerItem, 1), DynamicTextComponent.create(() -> NumberUtil.roundToNthDecimalPlace(this.xpRemaining, 1))));
		}
		else {
			super.updateDescription(Component.translatable(LocaleUtil.getKey(defaultDescription), this.stackSize, item.getDefaultInstance().getHoverName(), NumberUtil.roundToNthDecimalPlace(this.xpPerItem, 1), DynamicTextComponent.create(() -> NumberUtil.roundToNthDecimalPlace(this.xpRemaining, 1))));
		}
	}

	@Override
	public void handleSkillXpGain(PlayerChangeXpEvent ev) {
		if (this.xpRemaining > 0) {
			this.xpRemaining = Math.max(0, this.xpRemaining - ev.getNewXpGain());

			if (xpRemaining == 0)
				updateDescription(Component.translatable(Util.makeDescriptionId("ability", RegistryUtil.getId(type())) + ".description"));

			markForClientSync();
		}
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("xp_per_item", this.xpPerItem);
			data.putString("item", RegistryUtil.getId(this.item).toString());
			data.putInt("amount", this.stackSize);
		}

		data.putFloat("xp_remaining", this.xpRemaining);

		return data;
	}

	@Override
	public void receiveSyncData(CompoundTag data) {
		super.receiveSyncData(data);

		if (data.contains("xp_remaining")) {
			float xpRemaining = this.xpRemaining;
			this.xpRemaining = Math.max(0, data.getFloat("xp_remaining"));

			if (xpRemaining != this.xpRemaining && (xpRemaining == 0 || this.xpRemaining == this.xpPerItem))
				updateDescription(Component.translatable(Util.makeDescriptionId("ability", RegistryUtil.getId(type())) + ".description"));
		}
	}

	@Override
	public CompoundTag saveToNbt() {
		CompoundTag data = super.saveToNbt();

		data.putFloat("xp_remaining", this.xpRemaining);

		return data;
	}

	@Override
	public void loadFromNbt(CompoundTag data) {
		super.loadFromNbt(data);

		if (data.contains("xp_remaining"))
			this.xpRemaining = Math.max(0, data.getFloat("xp_remaining"));
	}

	@Override
	public void receiveInteractionDataFromClient(String data) {
		if (this.xpRemaining <= 0) {
			ItemUtil.givePlayerItemOrDrop(getPlayer(), new ItemStack(this.item, this.stackSize));

			this.xpRemaining = this.xpPerItem;
			updateDescription(Component.translatable(Util.makeDescriptionId("ability", RegistryUtil.getId(type())) + ".description"));
		}

		markForClientSync();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		if (ClientOperations.isPressingCrouchKey()) {
			if (this.xpRemaining <= 0)
				AoAPackets.messageServer(new SyncAoAAbilityDataPacket(this, ""));

			return false;
		}

		return super.onGuiClick(mouseX, mouseY);
	}
}
