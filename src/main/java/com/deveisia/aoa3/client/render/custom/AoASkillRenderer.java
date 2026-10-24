package com.deveisia.aoa3.client.render.custom;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.NumberUtil;
import com.deveisia.aoa3.util.PlayerUtil;
import com.deveisia.aoa3.util.RegistryUtil;
import com.deveisia.aoa3.util.RenderUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

public interface AoASkillRenderer {
	ResourceLocation SKILL_ICON_BASE = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/aoaskill/skill_icon_base.png");

	void renderInHud(PoseStack matrix, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel);
	void renderInGui(PoseStack matrix, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel);

	default int hudRenderWidth(AoASkill.Instance skill) {
		return 25;
	}

	default int hudRenderHeight(AoASkill.Instance skill) {
		return 25;
	}

	default int guiRenderWidth(AoASkill.Instance skill) {
		return 25;
	}

	default int guiRenderHeight(AoASkill.Instance skill) {
		return 25;
	}

	AoASkillRenderer DEFAULT = new AoASkillRenderer() {
		@Override
		public void renderInHud(PoseStack matrix, AoASkill.Instance skill, float partialTicks, ProgressRenderType progressRenderType, boolean renderLevel) {
			if (skill == AoASkills.DEFAULT)
				return;

			matrix.pushPose();
			matrix.scale(0.5f, 0.5f, 0);
			RenderSystem.setShaderColor(1, 1, 1, 1);

			TextureManager textureManager = Minecraft.getInstance().getTextureManager();
			Font fontRenderer = Minecraft.getInstance().font;
			int renderWidth = hudRenderWidth(skill) * 2;
			int renderHeight = hudRenderHeight(skill) * 2;
			int level = skill.getLevel(true);

			RenderSystem.setShaderTexture(0, SKILL_ICON_BASE);
			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath(RegistryUtil.getId(skill.type()).getNamespace(), "textures/gui/aoaskill/" + RegistryUtil.getId(skill.type()).getPath() + ".png"));
			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth, renderHeight);
			RenderSystem.setShaderTexture(0, SKILL_ICON_BASE);

			if (level == 1000) {
				RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, renderWidth * 3, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
			}
			else {
				float percentComplete = PlayerUtil.getLevelProgressPercentage(skill.getLevel(true), skill.getXp()) / 100f;

				RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, renderWidth, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);

				switch (progressRenderType) {
					case Ring:
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - percentComplete * renderHeight, renderWidth * 2, renderHeight - percentComplete * renderHeight, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth * 4, renderHeight * 2);
						break;
					case Percent:
						String percentMessage = NumberUtil.roundToNthDecimalPlace(percentComplete * 100, 0) + "%";
						RenderUtil.drawScaledString(matrix, fontRenderer, percentMessage, (int)(renderWidth - fontRenderer.width(percentMessage) * 1.5f), (int)(renderHeight - fontRenderer.lineHeight * 1.5f), 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
						break;
					case Bar:
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - 10, 0, 50, 100, 20, renderWidth, 10, renderWidth * 4, renderHeight * 2);
						RenderUtil.renderScaledCustomSizedTexture(matrix, 0, renderHeight - 10, 0, 70, percentComplete * 100, 20, percentComplete * renderWidth, 10, renderWidth * 4, renderHeight * 2);
						break;
					case None:
					default:
						break;
				}
			}

			if (renderLevel) {
				RenderUtil.drawScaledString(matrix, fontRenderer, String.valueOf(level), (int)(renderWidth - fontRenderer.width(String.valueOf(level)) * 1.5f), 1, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

				if (skill.getCycles() > 0)
					RenderUtil.drawScaledString(matrix, fontRenderer, String.valueOf(skill.getCycles()), 0, 1, 1.5f, ColourUtil.RGB(252, 170, 0), RenderUtil.StringRenderType.OUTLINED);
			}

			matrix.popPose();
		}

		@Override
		public void renderInGui(PoseStack matrix, AoASkill.Instance skill, float partialTicks, int mouseX, int mouseY, ProgressRenderType progressRenderType, boolean renderLevel) {
			renderInHud(matrix, skill, partialTicks, progressRenderType, renderLevel);
		}
	};

	enum ProgressRenderType {
		Ring,
		Bar,
		Percent,
		None
	}
}
