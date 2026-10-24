package com.deveisia.aoa3.client.render;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public final class CustomDimensionRenders {
	public static void init(IEventBus modBus) {
		modBus.addListener(CustomDimensionRenders::registerEffects);
	}

	private static void registerEffects(final RegisterDimensionSpecialEffectsEvent ev) {
		ev.register(PlainSky.ID, new PlainSky());
		ev.register(CelestialOnly.ID, new CelestialOnly());
		ev.register(Shyrelands.ID, new Shyrelands());
		ev.register(Lunalus.ID, new Lunalus());
	}

	public static class PlainSky extends DimensionSpecialEffects {
		public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "plain_sky");

		public PlainSky() {
			super(Float.NaN, true, SkyType.NONE, false, true);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour;
		}

		@Override
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}

		@Nullable
		@Override
		public float[] getSunriseColor(float celestialAngle, float partialTicks) {
			return null;
		}
	}

	public static class CelestialOnly extends DimensionSpecialEffects {
		public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "celestial_only");

		public CelestialOnly() {
			super(Float.NaN, true, SkyType.NORMAL, false, false);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}

	public static class Shyrelands extends DimensionSpecialEffects {
		public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "shyrelands");

		public Shyrelands() {
			super(40, true, SkyType.NORMAL, false, true);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
		}

		@Override
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}
	}

	public static class Lunalus extends DimensionSpecialEffects {
		public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "lunalus");
		private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AdventOfAscension.MOD_ID, "textures/gui/realmstonegui/background.png");

		public Lunalus() {
			super(Float.NaN, true, SkyType.NONE, false, true);
		}

		@Override
		public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
			return biomeFogColour;
		}

		@Override
		public boolean isFoggyAt(int posX, int posZ) {
			return false;
		}

		@Nullable
		@Override
		public float[] getSunriseColor(float celestialAngle, float partialTicks) {
			return null;
		}

		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack matrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.depthMask(false);
			RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
			RenderSystem.setShaderTexture(0, TEXTURE);

			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder buffer = tesselator.getBuilder();

			for (int i = 0; i < 6; ++i) {
				matrix.pushPose();

				switch (i) {
					case 1 -> matrix.mulPose(Axis.XP.rotationDegrees(90.0F));
					case 2 -> matrix.mulPose(Axis.XP.rotationDegrees(-90.0F));
					case 3 -> matrix.mulPose(Axis.XP.rotationDegrees(180.0F));
					case 4 -> matrix.mulPose(Axis.ZP.rotationDegrees(90.0F));
					case 5 -> matrix.mulPose(Axis.ZP.rotationDegrees(-90.0F));
					default -> {}
				}

				Matrix4f matrix4f = matrix.last().pose();

				buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
				buffer.vertex(matrix4f, -100, -100, -100).uv(0, 0).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, -100, -100, 100).uv(0, 1).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, 100, -100, 100).uv(1, 1).color(150, 150, 150, 255).endVertex();
				buffer.vertex(matrix4f, 100, -100, -100).uv(1, 0).color(150, 150, 150, 255).endVertex();
				tesselator.end();
				matrix.popPose();
			}

			RenderSystem.depthMask(true);
			RenderSystem.disableBlend();

			return true;
		}
	}
}
