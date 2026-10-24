package com.deveisia.aoa3.common.registration;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.render.tileentity.LunarCreationTableRenderer;
import com.deveisia.aoa3.client.render.tileentity.TrophyRenderer;
import com.deveisia.aoa3.content.block.tileentity.LunarCreationTableTileEntity;
import com.deveisia.aoa3.content.block.tileentity.TrophyTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class AoATileEntities {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<BlockEntityType<TrophyTileEntity>> TROPHY = registerTileEntity("trophy", () -> BlockEntityType.Builder.of(TrophyTileEntity::new, AoABlocks.TROPHY.get(), AoABlocks.GOLD_TROPHY.get(), AoABlocks.ORNATE_TROPHY.get()).build(null));
	public static final RegistryObject<BlockEntityType<LunarCreationTableTileEntity>> LUNAR_CREATION_TABLE = registerTileEntity("lunar_creation_table", () -> BlockEntityType.Builder.of(LunarCreationTableTileEntity::new, AoABlocks.LUNAR_CREATION_TABLE.get()).build(null));

	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerTileEntity(String registryName, Supplier<BlockEntityType<T>> tile) {
		return TILES.register(registryName, tile);
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderers() {
		BlockEntityRenderers.register(TROPHY.get(), TrophyRenderer::new);
		BlockEntityRenderers.register(LUNAR_CREATION_TABLE.get(), LunarCreationTableRenderer::new);
	}
}
