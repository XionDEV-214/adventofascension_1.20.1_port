package com.deveisia.aoa3.common.registration.custom;

import com.deveisia.aoa3.advent.AdventOfAscension;
import java.util.function.Supplier;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import com.deveisia.aoa3.player.skill.*;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AoASkills {
	public static final ResourceKey<Registry<AoASkill>> REGISTRY_KEY = ResourceKey.createRegistryKey(AdventOfAscension.id("skills"));
	public static final DeferredRegister<AoASkill> SKILLS = DeferredRegister.create(REGISTRY_KEY, AdventOfAscension.MOD_ID);
	public static final Supplier<IForgeRegistry<AoASkill>> REGISTRY = SKILLS.makeRegistry(() -> new RegistryBuilder<AoASkill>().disableSaving());

	public static final RegistryObject<AoASkill> DEXTERITY = SKILLS.register("dexterity", () -> new AoASkill(DexteritySkill::new, DexteritySkill::new));

	public static final RegistryObject<AoASkill> EXTRACTION = SKILLS.register("extraction", () -> new AoASkill(ExtractionSkill::new, ExtractionSkill::new));
	public static final RegistryObject<AoASkill> FARMING = SKILLS.register("farming", () -> new AoASkill(FarmingSkill::new, FarmingSkill::new));

	public static final RegistryObject<AoASkill> HAULING = SKILLS.register("hauling", () -> new AoASkill(HaulingSkill::new, HaulingSkill::new));

	public static final RegistryObject<AoASkill> INNERVATION = SKILLS.register("innervation", () -> new AoASkill(InnervationSkill::new, InnervationSkill::new));

	public static final AoASkill.Instance DEFAULT = new AoASkill.Instance(null, null, null) {
		@Override
		public int getLevel(boolean includeVanityLevels) {
			return 0;
		}

		@Override
		public boolean hasLevel(int levelReq) {
			return false;
		}

		@Override
		public void adjustXp(float xp, boolean isUnnaturalSource, boolean ignoreXpBuffs) {}

		@Override
		public boolean addCycle() {
			return false;
		}

		@Override
		public CompoundTag saveToNbt() {
			return new CompoundTag();
		}

		@Override
		public void loadFromNbt(CompoundTag skillData) {}

		@Override
		public CompoundTag getSyncData(boolean forClientSetup) {
			return new CompoundTag();
		}

		@Override
		public void receiveSyncData(CompoundTag data) {}
	};

	@Nullable
	public static AoASkill getSkill(ResourceLocation id) {
		return getRegistry().getValue(id);
	}

	public static IForgeRegistry<AoASkill> getRegistry() {
		return REGISTRY.get();
	}
}
