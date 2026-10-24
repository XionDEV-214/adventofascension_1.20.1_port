package com.deveisia.aoa3.client.gui.hud.toasts;

import net.minecraft.client.gui.GuiGraphics;
import com.deveisia.aoa3.util.RenderUtil;
import com.deveisia.aoa3.client.render.AoAGuiElementRenderers;
import com.deveisia.aoa3.client.render.custom.AoASkillRenderer;
import com.deveisia.aoa3.player.ClientPlayerDataManager;
import com.deveisia.aoa3.player.ability.AoAAbility;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.deveisia.aoa3.util.ColourUtil;
import com.deveisia.aoa3.util.LocaleUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;

public class AbilityUnlockToast implements Toast {
	private final AoASkill skill;
	private final AoAAbility ability;
	private final String title;
	private final String subtitle;

	public AbilityUnlockToast(AoASkill relevantSkill, AoAAbility ability) {
		this.skill = relevantSkill;
		this.ability = ability;
		this.title = LocaleUtil.getLocaleString("gui.aoatoast.abilityUnlock.title", ChatFormatting.DARK_RED);
		this.subtitle = LocaleUtil.getLocaleString("gui.aoatoast.abilityUnlock.subtitle", ability.getName().getString());
	}

	public AoASkill getSkill() {
		return skill;
	}

	public AoAAbility getAbility() {
		return ability;
	}

	@Override
	public Visibility render(GuiGraphics guiGraphics, ToastComponent toastGui, long delta) {
		PoseStack matrix = guiGraphics.pose();
		Minecraft mc = toastGui.getMinecraft();

		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderUtil.blit(matrix, 0, 0, 0, 0, 160, 32);
		matrix.pushPose();
		matrix.scale(0.9f, 0.9f, 1f);
		matrix.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getSkillRenderer(skill).renderInHud(matrix, ClientPlayerDataManager.get().getSkill(skill), mc.getDeltaFrameTime(), AoASkillRenderer.ProgressRenderType.None, false);
		matrix.popPose();
		RenderUtil.drawTextLegacy(matrix, mc.font, title, 30, 7, 1488129);

		int subtitleWidth = mc.font.width(subtitle);

		if (subtitleWidth <= 125f) {
			RenderUtil.drawTextLegacy(matrix, mc.font, subtitle, 30, 18, ColourUtil.WHITE);
		}
		else {
			matrix.pushPose();

			float scale = 125f / subtitleWidth;

			matrix.scale(scale, scale, 1);
			RenderUtil.drawTextLegacy(matrix, mc.font, subtitle, 30 / scale, 18 / scale, ColourUtil.WHITE);
			matrix.popPose();
		}

		return delta >= 3000 ? Visibility.HIDE : Visibility.SHOW;
	}
}
