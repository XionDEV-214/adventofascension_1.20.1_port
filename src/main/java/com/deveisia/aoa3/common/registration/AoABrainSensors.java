package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.content.entity.brain.sensor.IncomingProjectilesSensor;
import java.util.function.Supplier;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AoABrainSensors {
	public static final DeferredRegister<SensorType<?>> SENSORS = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<SensorType<IncomingProjectilesSensor>> INCOMING_PROJECTILES = registerSensor("incoming_projectiles", IncomingProjectilesSensor::new);

	private static <T extends Sensor<?>> RegistryObject<SensorType<T>> registerSensor(String id, Supplier<T> sensor) {
		return SENSORS.register(id, () -> new SensorType<T>(sensor));
	}
}
