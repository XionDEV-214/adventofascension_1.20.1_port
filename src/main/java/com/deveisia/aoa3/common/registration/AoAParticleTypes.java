package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.particletype.CustomisableParticleType;
import com.deveisia.aoa3.common.particletype.FloatingItemFragmentParticleType;
import com.deveisia.aoa3.common.particletype.PortalFloaterParticleType;
import com.mojang.serialization.Codec;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SPARKLER = registerParticle("sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> FLICKERING_SPARKLER = registerParticle("flickering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> RAINBOW_SPARKLER = registerParticle("rainbow_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> LINGERING_SPARKLER = registerParticle("lingering_sparkler", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<PortalFloaterParticleType.Data>> PORTAL_FLOATER = registerParticle("portal_floater", () -> new PortalFloaterParticleType(false));
	public static final RegistryObject<ParticleType<CustomisableParticleType.Data>> SWIRLY = registerParticle("swirly", () -> new CustomisableParticleType(true));
	public static final RegistryObject<ParticleType<ItemParticleOption>> FLOATING_ITEM_FRAGMENT = registerParticle("floating_item_fragment", () -> new FloatingItemFragmentParticleType(false));

	private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerParticle(String id, Supplier<? extends ParticleType<T>> particle) {
		return PARTICLES.register(id, particle);
	}

	private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> registerParticle(String id, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codecFunction) {
		return PARTICLES.register(id, () -> new ParticleType<T>(false, deserializer) {
			@Override
			public Codec<T> codec() {
				return codecFunction.apply(this);
			}
		});
	}
}
