package com.deveisia.aoa3.client.render.tileentity;

import com.deveisia.aoa3.content.block.tileentity.LunarCreationTableTileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class LunarCreationTableRenderer implements BlockEntityRenderer<LunarCreationTableTileEntity> {
	public LunarCreationTableRenderer(BlockEntityRendererProvider.Context context) {}

	@Override
	public void render(LunarCreationTableTileEntity tileEntity, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		NonNullList<ItemStack> contents = tileEntity.getContents();
		ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
		Level world = tileEntity.getLevel();
		BlockPos abovePos = tileEntity.getBlockPos().above();

		if (world != null) {
			if (world.getBlockState(abovePos).isSolidRender(world, abovePos))
				return;

			combinedLight = LevelRenderer.getLightColor(world, abovePos);
		}
		else {
			combinedLight = 15728880;
		}

		matrix.pushPose();

		matrix.scale(0.25f, 0.25f, 0.25f);
		matrix.translate(1.25f, 4.25f, 1.25f);
		matrix.mulPose(Axis.YP.rotationDegrees(180f));

		for (int i = 0; i < 9; i++) {
			ItemStack stack = contents.get(i);

			if (stack.isEmpty())
				continue;

			matrix.pushPose();
			matrix.translate(-0.75f * (i % 3), 0, -0.75f * (i / 3));

			if (!(stack.getItem() instanceof BlockItem))
				matrix.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, matrix, buffer, tileEntity.getLevel(), 0);
			matrix.popPose();
		}

		matrix.popPose();
	}
}
