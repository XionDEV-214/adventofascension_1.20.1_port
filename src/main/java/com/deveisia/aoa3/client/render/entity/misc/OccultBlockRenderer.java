package com.deveisia.aoa3.client.render.entity.misc;

import com.deveisia.aoa3.event.GlobalEvents;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.EventPriority;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class OccultBlockRenderer {
	private static final ConcurrentHashMap<Integer, ArrayList<Pair<BlockPos, BlockState>>> occultBlockMap = new ConcurrentHashMap<Integer, ArrayList<Pair<BlockPos, BlockState>>>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderLevelStageEvent.class, OccultBlockRenderer::onWorldRender);
	}

	public static void addOccultBlocks(int renderUntil, ArrayList<Pair<BlockPos, BlockState>> blocks) {
		occultBlockMap.put(renderUntil, blocks);
	}

	private static void onWorldRender(final RenderLevelStageEvent ev) {
		if (ev.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS || occultBlockMap.isEmpty())
			return;

		ArrayList<Integer> expiredBlocks = null;
		Minecraft mc = Minecraft.getInstance();

		for (Map.Entry<Integer, ArrayList<Pair<BlockPos, BlockState>>> entry : occultBlockMap.entrySet()) {
			if (GlobalEvents.tick >= entry.getKey()) {
				if (expiredBlocks == null)
					expiredBlocks = new ArrayList<Integer>();

				expiredBlocks.add(entry.getKey());
			}
			else {
				ArrayList<Pair<BlockPos, BlockState>> invalidBlocks = null;
				PoseStack matrix = ev.getPoseStack();
				Vec3 cameraPos = ev.getCamera().getPosition();
				Tesselator tess = Tesselator.getInstance();
				BufferBuilder buff = tess.getBuilder();

				matrix.pushPose();
				matrix.translate(-cameraPos.x(), -cameraPos.y(), -cameraPos.z());

				RenderSystem.disableDepthTest();
				RenderSystem.depthMask(false);
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();
				RenderSystem.lineWidth(4f);
				RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
				buff.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR_NORMAL);

				for (Pair<BlockPos, BlockState> block : entry.getValue()) {
					if (GlobalEvents.tick % 2 == 1 && mc.player.level().getBlockState(block.getFirst()) != block.getSecond()) {
						if (invalidBlocks == null)
							invalidBlocks = new ArrayList<Pair<BlockPos, BlockState>>();

						invalidBlocks.add(block);

						continue;
					}

					Vector4f colour = getColourForBlock(block.getSecond());

					LevelRenderer.renderLineBox(matrix, buff, new AABB(block.getFirst()), colour.x(), colour.y(), colour.z(), colour.w());
				}

				tess.end();
				RenderSystem.lineWidth(1f);
				RenderSystem.disableBlend();
				RenderSystem.enableDepthTest();
				RenderSystem.depthMask(true);
				matrix.popPose();

				if (invalidBlocks != null)
					entry.getValue().removeAll(invalidBlocks);
			}
		}

		if (expiredBlocks != null) {
			for (Integer tick : expiredBlocks) {
				occultBlockMap.remove(tick);
			}
		}
	}

	private static Vector4f getColourForBlock(BlockState block) {
		if (block.is(Tags.Blocks.NEEDS_NETHERITE_TOOL))
			return new Vector4f(0, 153 / 255f, 0, 0.5f);

		if (block.is(BlockTags.NEEDS_DIAMOND_TOOL))
			return new Vector4f(0, 147 / 255f, 66 / 255f, 0.5f);

		if (block.is(BlockTags.NEEDS_IRON_TOOL))
			return new Vector4f(1, 1, 0, 0.5f);

		if (block.is(BlockTags.NEEDS_STONE_TOOL))
			return new Vector4f(223 / 255f, 153 / 255f, 0, 0.5f);

		return new Vector4f(1, 0, 0, 0.5f);
	}
}
