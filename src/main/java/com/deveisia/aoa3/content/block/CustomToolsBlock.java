package com.deveisia.aoa3.content.block;

import com.deveisia.aoa3.library.compat.ToolType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nonnull;

public class CustomToolsBlock extends Block {
	@Nonnull
	private final ToolType[] validToolTypes;

	public CustomToolsBlock(BlockBehaviour.Properties properties, @Nonnull String primaryType, String... secondaryTypes) {
		super(properties);

		validToolTypes = new ToolType[secondaryTypes.length + 1];
		validToolTypes[0] = ToolType.get(primaryType);

		for (int i = 0; i < secondaryTypes.length; i++) {
			validToolTypes[i + 1] = ToolType.get(secondaryTypes[i]);
		}
	}

	public ToolType[] getValidToolTypes() {
		return this.validToolTypes;
	}
}
