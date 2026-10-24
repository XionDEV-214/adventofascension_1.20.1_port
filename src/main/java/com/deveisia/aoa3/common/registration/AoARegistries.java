package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.common.registration.custom.AoAAbilities;
import com.deveisia.aoa3.common.registration.custom.AoAResources;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.ability.AoAAbility;
import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.player.skill.AoASkill;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public final class AoARegistries {
	public static final Supplier<IForgeRegistry<AoASkill>> SKILLS = () -> AoASkills.REGISTRY.get();
	public static final Supplier<IForgeRegistry<AoAResource>> RESOURCES = () -> AoAResources.REGISTRY.get();
	public static final Supplier<IForgeRegistry<AoAAbility>> ABILITIES = () -> AoAAbilities.REGISTRY.get();
}
