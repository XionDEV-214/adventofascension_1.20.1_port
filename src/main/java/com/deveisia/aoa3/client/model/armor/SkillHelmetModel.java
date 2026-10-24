package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.client.model.compat.LegacyModel;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class SkillHelmetModel extends HumanoidModel<LivingEntity> implements LegacyModel {
	private final ResourceLocation texturePath;
	public int texWidth = 64;
	public int texHeight = 32;
	protected LegacyModelPart customHead;

	public SkillHelmetModel(ResourceLocation texturePath) {
		super(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR));

		this.texturePath = texturePath;
	}

	public ResourceLocation getTexturePath() {
		return this.texturePath;
	}

	@Override
	public int getTexWidth() {
		return this.texWidth;
	}

	@Override
	public int getTexHeight() {
		return this.texHeight;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (this.customHead == null || !this.head.visible)
			return;

		this.customHead.copyFrom(this.head);
		this.customHead.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	protected void setRotationAngle(LegacyModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
