package com.deveisia.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.deveisia.aoa3.client.model.misc.FullbrightModelRenderer;
import com.deveisia.aoa3.content.entity.animal.ShinySquidEntity;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;

public class ShinySquidModel extends LegacyEntityModel<ShinySquidEntity> {
	protected final LegacyModelPart body;
	protected final LegacyModelPart[] tentacles = new LegacyModelPart[8];
	protected final ImmutableList<LegacyModelPart> parts;

	public ShinySquidModel() {
		this.body = new FullbrightModelRenderer(this, 0, 0);
		this.body.addBox(-6, -8, -6, 12, 16, 12);
		this.body.y += 8;

		for(int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i] = new FullbrightModelRenderer(this, 48, 0);
			double rotPos = (double)i * Math.PI * 2 / (double)this.tentacles.length;

			this.tentacles[i].addBox(-1, 0, -1, 2, 18, 2);

			this.tentacles[i].x = (float)Math.cos(rotPos) * 5;
			this.tentacles[i].z = (float)Math.sin(rotPos) * 5;
			this.tentacles[i].y = 15;
			rotPos = (double)i * Math.PI * -2 / (double)this.tentacles.length + (Math.PI / 2d);
			this.tentacles[i].yRot = (float)rotPos;
		}

		ImmutableList.Builder<LegacyModelPart> builder = ImmutableList.builder();

		builder.add(this.body);
		builder.addAll(Arrays.asList(this.tentacles));

		this.parts = builder.build();
	}

	@Override
	public void setupAnim(ShinySquidEntity squid, float pLimbSwing, float pLimbSwingAmount, float age, float pNetHeadYaw, float pHeadPitch) {
		for(LegacyModelPart modelrenderer : this.tentacles) {
			modelrenderer.xRot = age;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		for (LegacyModelPart part : this.parts) {
			part.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}
}
