package com.deveisia.aoa3.client.model.entity.npc;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.entity.EntityGeoModel;
import com.deveisia.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.minecraft.resources.ResourceLocation;

public class DryadSpriteModel extends EntityGeoModel<DryadSpriteEntity> {
	private static final ResourceLocation WOOD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_wood.png");
	private static final ResourceLocation STONE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_stone.png");
	private static final ResourceLocation IRON_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_iron.png");
	private static final ResourceLocation GOLD_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_gold.png");
	private static final ResourceLocation DIAMOND_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_diamond.png");
	private static final ResourceLocation NETHERITE_TEXTURE = AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite_netherite.png");

	public DryadSpriteModel() {
		super("npc/ambient/dryad_sprite", "npc/ambient/dryad_sprite_wood");
	}

	@Override
	public ResourceLocation getTextureResource(DryadSpriteEntity entity) {
		switch (entity.getVariant()) {
			case STONE:
				return STONE_TEXTURE;
			case IRON:
				return IRON_TEXTURE;
			case GOLD:
				return GOLD_TEXTURE;
			case DIAMOND:
				return DIAMOND_TEXTURE;
			case NETHERITE:
				return NETHERITE_TEXTURE;
			case WOOD:
			default:
				return WOOD_TEXTURE;
		}
	}
}
