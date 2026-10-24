package com.deveisia.aoa3.client;

import net.minecraft.util.RandomSource;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import com.deveisia.aoa3.mixin.client.accessor.SoundManagerAccessor;
import com.deveisia.aoa3.mixin.client.accessor.SoundEngineAccessor;
import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.advent.Logging;
import com.deveisia.aoa3.client.gui.adventgui.AdventGuiTabLore;
import com.deveisia.aoa3.client.gui.hud.RecoilRenderer;
import com.deveisia.aoa3.client.gui.hud.toasts.AbilityUnlockToast;
import com.deveisia.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import com.deveisia.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import com.deveisia.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import com.deveisia.aoa3.client.particle.*;
import com.deveisia.aoa3.client.render.entity.misc.OccultBlockRenderer;
import com.deveisia.aoa3.common.packet.packets.ToastPopupPacket;
import com.deveisia.aoa3.common.packet.packets.UpdateClientMovementPacket;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.common.registration.AoAParticleTypes;
import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.config.AoAConfig;
import com.deveisia.aoa3.content.entity.mob.greckon.SilencerEntity;
import com.deveisia.aoa3.content.item.misc.WornBook;
import com.deveisia.aoa3.data.client.AdventGuiThemeReloadListener;
import com.deveisia.aoa3.data.client.BestiaryReloadListener;
import com.deveisia.aoa3.data.client.MiscellaneousReloadListener;
import com.deveisia.aoa3.data.client.RealmstoneInsertsReloadListener;
import com.deveisia.aoa3.integration.IntegrationManager;
import com.deveisia.aoa3.integration.patchouli.PatchouliIntegration;
import com.deveisia.aoa3.library.builder.SoundBuilder;
import com.deveisia.aoa3.player.ClientPlayerDataManager;
import com.deveisia.aoa3.player.ability.AoAAbility;
import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.LocaleUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public final class ClientOperations {
	public static final ClientPlayerDataManager CLIENT_PLAYER_DATA = new ClientPlayerDataManager();

	public static Level getWorld() {
		return Minecraft.getInstance().level;
	}

	public static Player getPlayer() {
		return Minecraft.getInstance().player;
	}

	public static void displayWornBookGui() {
		Player player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getMainHandItem().getItem() == AoAItems.WORN_BOOK.get() ? player.getMainHandItem() : player.getOffhandItem();

		if (IntegrationManager.isPatchouliActive()) {
			PatchouliIntegration.openBook(AdventOfAscension.id("worn_book"));
		}
		else {
			Minecraft.getInstance().setScreen(new BookViewScreen(new BookViewScreen.WrittenBookAccess(WornBook.getBook(bookStack))));
		}
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().setScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float recoil, final int firingTime) {
		RecoilRenderer.addRecoil(recoil);
	}

	public static void addOccultBlocks(int renderUntil, ArrayList<Pair<BlockPos, BlockState>> blocks) {
		OccultBlockRenderer.addOccultBlocks(renderUntil, blocks);
	}

	public static void registerResourceListeners(RegisterClientReloadListenersEvent ev) {
		ev.registerReloadListener(new BestiaryReloadListener());
		ev.registerReloadListener(new MiscellaneousReloadListener());
		ev.registerReloadListener(new RealmstoneInsertsReloadListener());
		ev.registerReloadListener(new AdventGuiThemeReloadListener());
	}

	public static void registerParticleFactories(RegisterParticleProvidersEvent ev) {
		ev.registerSpriteSet(AoAParticleTypes.PORTAL_FLOATER.get(), PortalFloaterParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.SPARKLER.get(), SparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.FLICKERING_SPARKLER.get(), FlickeringSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.LINGERING_SPARKLER.get(), LingeringSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.RAINBOW_SPARKLER.get(), RainbowSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.SWIRLY.get(), SwirlyParticle.Factory::new);
		ev.registerSpecial(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), new FloatingItemFragmentParticle.Factory());
	}

	public static void addToast(ToastPopupPacket.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT:
				AoASkill skill = AoASkills.getSkill((ResourceLocation)subject);

				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new LevelRequirementToast(skill, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientLevels", ChatFormatting.RED, skill.getName(), LocaleUtil.numToComponent((Integer)value)));
				}
				break;
			case RESOURCE_REQUIREMENT:
				AoAResource resource = AoAResources.getResource((ResourceLocation)subject);

				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new ResourceRequirementToast(resource, (Float)value));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientResource", ChatFormatting.RED, resource.getName(), Component.literal(NumberUtil.roundToNthDecimalPlace((Float)value, 2))));
				}
				break;
			case ABILITY_UNLOCK:
				AoASkill skill2 = AoASkills.getSkill((ResourceLocation)subject);
				AoAAbility ability = AoAAbilities.getAbility((ResourceLocation)value);

				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new AbilityUnlockToast(skill2, ability));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.abilityUnlocked", ChatFormatting.GREEN, skill2.getName(), ability.getName()));
				}
				break;
		}
	}

	public static void doSilencerSilence(SilencerEntity silencer) {
		if (!SilencerEntity.isClientNearby) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.options.getSoundSourceVolume(SoundSource.MASTER) > 0) {
				if (silencer.distanceToSqr(mc.player) < 8 * 8) {
					SilencerEntity.isClientNearby = true;
					SilencerEntity.previousGain = ((SoundEngineAccessor)((SoundManagerAccessor)mc.getSoundManager()).getSoundEngine()).getListener().getGain();

					((SoundEngineAccessor)((SoundManagerAccessor)mc.getSoundManager()).getSoundEngine()).getListener().setGain(0);
				}
			}
		}
	}

	public static void syncPatchouliBooks(ArrayList<ResourceLocation> books) {
		AdventGuiTabLore.syncBooks(books);
	}

	public static void adjustPlayerMovement(@Nullable Float x, @Nullable Float y, @Nullable Float z, UpdateClientMovementPacket.Operation operation) {
		Player player = Minecraft.getInstance().player;
		Vec3 velocity = player.getDeltaMovement();

		switch (operation) {
			case SET:
				player.setDeltaMovement(x != null ? x : velocity.x(), y != null ? y : velocity.y(), z != null ? z : velocity.z());
				break;
			case ADD:
				player.setDeltaMovement(velocity.add(x != null ? x : 0, y != null ? y : 0, z != null ? z : 0));
				break;
			case MULTIPLY:
				player.setDeltaMovement(velocity.multiply(x != null ? x : 1, y != null ? y : 1, z != null ? z : 1));
				break;
			case MAX:
				player.setDeltaMovement(x != null ? Math.min(x, velocity.x()) : velocity.x(), y != null ? Math.min(y, velocity.y()) : velocity.y(), z != null ? Math.min(z, velocity.z()) : velocity.z());
				break;
			case MIN:
				player.setDeltaMovement(x != null ? Math.max(x, velocity.x()) : velocity.x(), y != null ? Math.max(y, velocity.y()) : velocity.y(), z != null ? Math.max(z, velocity.z()) : velocity.z());
				break;
		}
	}

	public static boolean harvestAdditionalBlock(BlockPos breakPos) {
		if (Minecraft.getInstance().gameMode == null)
			return false;

		return Minecraft.getInstance().gameMode.destroyBlock(breakPos);
	}

	public static Component getPlayerName() {
		return Minecraft.getInstance().player.getDisplayName();
	}

	public static void addParticle(ParticleOptions particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, int amount) {
		ClientLevel level = Minecraft.getInstance().level;

		for (int i = 0; i < amount; i++) {
			try {
				level.addParticle(particle, false, x, y, z, velocityX, velocityY, velocityZ);
			} catch (Exception ex) {
				Logging.logMessage(org.apache.logging.log4j.Level.WARN, "Unable to spawn particle " + particle, ex);
			}
		}
	}

	public static GameType getGameMode() {
		return Minecraft.getInstance().gameMode.getPlayerMode();
	}

	public static boolean isPressingCrouchKey() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), Minecraft.getInstance().options.keyShift.getKey().getValue());
	}

	public static void playSoundFromBuilder(SoundBuilder soundBuilder) {
		SoundInstance sound;
		double delay = soundBuilder.getScheduledDelay() + (soundBuilder.getApplyTimeDilation() ? Math.sqrt(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().distanceToSqr(soundBuilder.getLocation())) * 0.5d : 0);

		if (soundBuilder.getFollowingEntity() != null) {
			sound = new EntityBoundSoundInstance(soundBuilder.getSound(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), soundBuilder.getFollowingEntity(), RandomSource.create().nextLong()) {
				@Override
				public boolean isLooping() {
					return soundBuilder.getIsLooping();
				}

				@Override
				public int getDelay() {
					return soundBuilder.getLoopDelay();
				}
			};
		}
		else {
			if (soundBuilder.getLocation() != null) {
				sound = new SimpleSoundInstance(soundBuilder.getSound().getLocation(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), RandomSource.create(), soundBuilder.getIsLooping(), (int)delay, soundBuilder.getIsInWorld() ? SoundInstance.Attenuation.LINEAR : SoundInstance.Attenuation.NONE, soundBuilder.getLocation().x(), soundBuilder.getLocation().y(), soundBuilder.getLocation().z(), false);
			}
			else {
				sound = new SimpleSoundInstance(soundBuilder.getSound().getLocation(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), RandomSource.create(), soundBuilder.getIsLooping(), (int)delay, soundBuilder.getIsInWorld() ? SoundInstance.Attenuation.LINEAR : SoundInstance.Attenuation.NONE, 0, 0, 0, false);
			}
		}

		if (delay > 0) {
			Minecraft.getInstance().getSoundManager().playDelayed(sound, (int)delay);
		}
		else {
			Minecraft.getInstance().getSoundManager().play(sound);
		}
	}
}
