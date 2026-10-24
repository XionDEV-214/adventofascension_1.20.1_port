package com.deveisia.aoa3.util;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.AoABlocks;
import com.deveisia.aoa3.common.registration.AoAItems;
import com.deveisia.aoa3.library.object.MutableSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class FluidUtil {
	public static class Builder {
		private final String id;

		private final MutableSupplier<ForgeFlowingFluid.Source> sourceFluid = new MutableSupplier<ForgeFlowingFluid.Source>(null);
		private final MutableSupplier<ForgeFlowingFluid.Flowing> flowingFluid = new MutableSupplier<ForgeFlowingFluid.Flowing>(null);
		private final MutableSupplier<FluidType> fluidType = new MutableSupplier<FluidType>(null);

		private BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, BlockBehaviour.Properties, Supplier<LiquidBlock>> blockCreationFunction = (flowingFluid, blockProperties) -> () -> new LiquidBlock(flowingFluid, blockProperties);
		private BiFunction<MutableSupplier<ForgeFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction = (sourceFluid, itemProperties) -> () -> new BucketItem(sourceFluid, itemProperties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluidFunction = properties -> () -> new ForgeFlowingFluid.Source(properties);
		private Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluidFunction = properties -> () -> new ForgeFlowingFluid.Flowing(properties);
		private Consumer<ForgeFlowingFluid.Properties> propertiesModifier = properties -> {};

		private boolean molten = false;
		private boolean isGas = false;
		private int colour = 0xFFFFFFFF;

		private int luminosity = 0;
		private int density = 1000;
		private int temperature = 300;
		private int viscosity = 1000;

		private Rarity rarity = Rarity.COMMON;

		private ResourceLocation stillTexture = ResourceLocation.parse("block/water_still");
		private ResourceLocation flowingTexture = ResourceLocation.parse("block/water_flow");
		private ResourceLocation overlayTexture = ResourceLocation.parse("block/water_overlay");

		private String localeKey;

		private SoundEvent fillSound = SoundEvents.BUCKET_FILL;
		private SoundEvent emptySound = SoundEvents.BUCKET_EMPTY;

		private ForgeFlowingFluid.Properties fluidProperties = null;

		public Builder(String id) {
			this.id = id;
			this.localeKey = "block." + AdventOfAscension.MOD_ID + "." + id;
		}

		public Builder isGas() {
			this.isGas = true;

			return this;
		}

		public Builder colour(int colour) {
			this.colour = colour;

			return this;
		}

		public Builder colour(int red, int green, int blue, int alpha) {
			return colour(ColourUtil.RGBA(red, green, blue, alpha));
		}

		public Builder luminosity(int luminosity) {
			this.luminosity = luminosity;

			return this;
		}

		public Builder density(int density) {
			this.density = density;

			return this;
		}

		public Builder temperature(int temperature) {
			this.temperature = temperature;

			return this;
		}

		public Builder viscosity(int viscosity) {
			this.viscosity = viscosity;

			return this;
		}

		public Builder rarity(Rarity rarity) {
			this.rarity = rarity;

			return this;
		}

		public Builder stillTexture(ResourceLocation stillTexture) {
			this.stillTexture = stillTexture;

			return this;
		}

		public Builder flowingTexture(ResourceLocation flowingTexture) {
			this.flowingTexture = flowingTexture;

			return this;
		}

		public Builder submergedOverlay(ResourceLocation overlayTexture) {
			this.overlayTexture = overlayTexture;

			return this;
		}

		public Builder localeKey(String key) {
			this.localeKey = key;

			return this;
		}

		public Builder bucketFillSound(SoundEvent sound) {
			this.fillSound = sound;

			return this;
		}

		public Builder bucketEmptySound(SoundEvent sound) {
			this.emptySound = sound;

			return this;
		}

		public Builder isMolten() {
			this.molten = true;
			luminosity(15);
			density(3000);
			viscosity(6000);
			temperature(1300);
			bucketFillSound(SoundEvents.BUCKET_FILL_LAVA);
			bucketEmptySound(SoundEvents.BUCKET_EMPTY_LAVA);
			stillTexture(ResourceLocation.parse("block/lava_still"));
			flowingTexture(ResourceLocation.parse("block/lava_flow"));

			return this;
		}

		public Builder tickRate(int ticks) {
			this.propertiesModifier = this.propertiesModifier.andThen(properties -> properties.tickRate(ticks));

			return this;
		}

		public Builder noBucket() {
			this.bucketCreationFunction = null;

			return this;
		}

		public Builder noBlock() {
			this.blockCreationFunction = null;

			return this;
		}

		public Builder customBlock(BiFunction<MutableSupplier<ForgeFlowingFluid.Flowing>, BlockBehaviour.Properties, Supplier<LiquidBlock>> blockCreationFunction) {
			this.blockCreationFunction = blockCreationFunction;

			return this;
		}

		public Builder customBucket(BiFunction<MutableSupplier<ForgeFlowingFluid.Source>, Item.Properties, Supplier<BucketItem>> bucketCreationFunction) {
			this.bucketCreationFunction = bucketCreationFunction;

			return this;
		}

		public Builder customSourceFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Source>> sourceFluidCreationFunction) {
			this.sourceFluidFunction = sourceFluidCreationFunction;

			return this;
		}

		public Builder customFlowingFluid(Function<ForgeFlowingFluid.Properties, Supplier<ForgeFlowingFluid.Flowing>> flowingFluidCreationFunction) {
			this.flowingFluidFunction = flowingFluidCreationFunction;

			return this;
		}

		public RegistryObject<LiquidBlock> defaultRegisterAll() {
			return registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS, AoABlocks.FLUID_TYPES).getFluidBlock();
		}

		public RegisteredFluidHolder registerAll(DeferredRegister<Item> itemRegistry, DeferredRegister<Block> blockRegistry, DeferredRegister<Fluid> fluidRegistry, DeferredRegister<FluidType> fluidTypeRegistry) {
			registerFluidType(fluidTypeRegistry);

			final RegistryObject<BucketItem> bucket = registerBucket(itemRegistry);
			final RegistryObject<LiquidBlock> fluidBlock = registerBlock(blockRegistry);
			final RegistryObject<ForgeFlowingFluid.Source> fluid = registerFluid(fluidRegistry);

			return new RegisteredFluidHolder(bucket, fluidBlock, fluid);
		}

		public RegistryObject<FluidType> registerFluidType(DeferredRegister<FluidType> fluidTypeRegistry) {
			final ResourceLocation still = this.stillTexture;
			final ResourceLocation flowing = this.flowingTexture;
			final ResourceLocation overlay = this.overlayTexture;
			final int tint = this.colour;
			FluidType.Properties properties = FluidType.Properties.create()
					.descriptionId(this.localeKey)
					.rarity(this.rarity)
					.temperature(this.temperature)
					.viscosity(this.viscosity)
					.density(this.isGas ? -this.density : this.density)
					.lightLevel(this.luminosity)
					.sound(SoundActions.BUCKET_FILL, this.fillSound)
					.sound(SoundActions.BUCKET_EMPTY, this.emptySound);

			if (this.molten)
				properties.canSwim(false).canDrown(false).pathType(net.minecraft.world.level.pathfinder.BlockPathTypes.LAVA).adjacentPathType(null);

			RegistryObject<FluidType> type = fluidTypeRegistry.register(this.id, () -> new FluidType(properties) {
				@Override
				public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
					consumer.accept(new IClientFluidTypeExtensions() {
						@Override
						public ResourceLocation getStillTexture() {
							return still;
						}

						@Override
						public ResourceLocation getFlowingTexture() {
							return flowing;
						}

						@Nullable
						@Override
						public ResourceLocation getOverlayTexture() {
							return overlay;
						}

						@Override
						public int getTintColor() {
							return tint;
						}
					});
				}
			});

			this.fluidType.update(type);

			return type;
		}

		@Nullable
		public RegistryObject<LiquidBlock> registerBlock(DeferredRegister<Block> blockRegistry) {
			if (this.blockCreationFunction == null)
				return null;

			makeFluidProperties();

			BlockBehaviour.Properties blockProperties = BlockBehaviour.Properties.of().mapColor(this.molten ? MapColor.FIRE : MapColor.WATER).replaceable().noCollission().strength(100).noLootTable().liquid().pushReaction(PushReaction.DESTROY).lightLevel(state -> luminosity);
			RegistryObject<LiquidBlock> block = blockRegistry.register(id, blockCreationFunction.apply(flowingFluid, blockProperties));

			this.fluidProperties.block(block);

			if (!this.molten)
				AoABlocks.customRender(block, AoABlocks.CustomRenderType.TRANSLUCENT);

			return block;
		}

		@Nullable
		public RegistryObject<BucketItem> registerBucket(DeferredRegister<Item> itemRegistry) {
			if (this.bucketCreationFunction == null)
				return null;

			makeFluidProperties();

			RegistryObject<BucketItem> bucket = itemRegistry.register(id + "_bucket", this.bucketCreationFunction.apply(sourceFluid, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

			this.fluidProperties.bucket(bucket);

			return bucket;
		}

		public RegistryObject<ForgeFlowingFluid.Source> registerFluid(DeferredRegister<Fluid> fluidRegistry) {
			makeFluidProperties();

			RegistryObject<ForgeFlowingFluid.Source> fluid = fluidRegistry.register(this.id, this.sourceFluidFunction.apply(this.fluidProperties));

			sourceFluid.update(fluid);
			flowingFluid.update(fluidRegistry.register(id + "_flowing", this.flowingFluidFunction.apply(this.fluidProperties)));

			return fluid;
		}

		private void makeFluidProperties() {
			if (this.fluidProperties == null) {
				this.fluidProperties = new ForgeFlowingFluid.Properties(this.fluidType, this.sourceFluid, this.flowingFluid);

				this.propertiesModifier.accept(this.fluidProperties);
			}
		}
	}

	public static final class RegisteredFluidHolder {
		private final RegistryObject<BucketItem> bucket;
		private final RegistryObject<LiquidBlock> fluidBlock;
		private final RegistryObject<ForgeFlowingFluid.Source> fluid;

		public RegisteredFluidHolder(RegistryObject<BucketItem> bucket, RegistryObject<LiquidBlock> fluidBlock, RegistryObject<ForgeFlowingFluid.Source> fluid) {
			this.bucket = bucket;
			this.fluidBlock = fluidBlock;
			this.fluid = fluid;
		}

		public RegistryObject<BucketItem> getBucket() {
			return bucket;
		}

		public RegistryObject<ForgeFlowingFluid.Source> getFluid() {
			return this.fluid;
		}

		public RegistryObject<LiquidBlock> getFluidBlock() {
			return this.fluidBlock;
		}
	}
}
