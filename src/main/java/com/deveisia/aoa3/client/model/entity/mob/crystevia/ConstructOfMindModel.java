package com.deveisia.aoa3.client.model.entity.mob.crystevia;

import com.deveisia.aoa3.client.model.compat.LegacyModelPart;
import com.deveisia.aoa3.client.model.compat.LegacyEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Mob;

public class ConstructOfMindModel extends LegacyEntityModel<Mob> {
	private final LegacyModelPart or1;
	private final LegacyModelPart ir1;
	private final LegacyModelPart or2;
	private final LegacyModelPart ir2;
	private final LegacyModelPart ir3;
	private final LegacyModelPart or3;
	private final LegacyModelPart ir4;
	private final LegacyModelPart or4;
	private final LegacyModelPart or5;
	private final LegacyModelPart or6;

	public ConstructOfMindModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(or1 = new LegacyModelPart(this, 28, 0)).addBox(-15.0f, -3.0f, -3.0f, 2, 6, 6);
		or1.setPos(0.0f, 4.0f, 0.0f);
		or1.setTexSize(64, 64);
		or1.mirror = true;
		setRotation(or1, 0.0f, 0.0f, 0.0f);
		(ir1 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, 4.0f, -3.0f, 6, 6, 6);
		ir1.setPos(0.0f, 4.0f, 0.0f);
		ir1.setTexSize(64, 64);
		ir1.mirror = true;
		setRotation(ir1, 0.0f, 0.0f, 0.0f);
		(or2 = new LegacyModelPart(this, 0, 15)).addBox(-4.0f, 12.0f, -3.0f, 8, 8, 6);
		or2.setPos(0.0f, 4.0f, 0.0f);
		or2.setTexSize(64, 64);
		or2.mirror = true;
		setRotation(or2, 0.0f, 0.0f, 0.0f);
		(ir2 = new LegacyModelPart(this, 0, 0)).addBox(-11.0f, -3.0f, -3.0f, 6, 6, 6);
		ir2.setPos(0.0f, 4.0f, 0.0f);
		ir2.setTexSize(64, 64);
		ir2.mirror = true;
		setRotation(ir2, 0.0f, 0.0f, 0.0f);
		(ir3 = new LegacyModelPart(this, 0, 0)).addBox(-3.0f, -11.0f, -3.0f, 6, 6, 6);
		ir3.setPos(0.0f, 4.0f, 0.0f);
		ir3.setTexSize(64, 64);
		ir3.mirror = true;
		setRotation(ir3, 0.0f, 0.0f, 0.0f);
		(or3 = new LegacyModelPart(this, 0, 15)).addBox(-4.0f, -21.0f, -3.0f, 8, 8, 6);
		or3.setPos(0.0f, 4.0f, 0.0f);
		or3.setTexSize(64, 64);
		or3.mirror = true;
		setRotation(or3, 0.0f, 0.0f, 0.0f);
		(ir4 = new LegacyModelPart(this, 0, 0)).addBox(5.0f, -3.0f, -3.0f, 6, 6, 6);
		ir4.setPos(0.0f, 4.0f, 0.0f);
		ir4.setTexSize(64, 64);
		ir4.mirror = true;
		setRotation(ir4, 0.0f, 0.0f, 0.0f);
		(or4 = new LegacyModelPart(this, 28, 0)).addBox(-19.0f, -3.0f, -3.0f, 2, 6, 6);
		or4.setPos(0.0f, 4.0f, 0.0f);
		or4.setTexSize(64, 64);
		or4.mirror = true;
		setRotation(or4, 0.0f, 0.0f, 0.0f);
		(or5 = new LegacyModelPart(this, 28, 0)).addBox(17.0f, -3.0f, -3.0f, 2, 6, 6);
		or5.setPos(0.0f, 4.0f, 0.0f);
		or5.setTexSize(64, 64);
		or5.mirror = true;
		setRotation(or5, 0.0f, 0.0f, 0.0f);
		(or6 = new LegacyModelPart(this, 28, 0)).addBox(13.0f, -3.0f, -3.0f, 2, 6, 6);
		or6.setPos(0.0f, 4.0f, 0.0f);
		or6.setTexSize(64, 64);
		or6.mirror = true;
		setRotation(or6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		or1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final LegacyModelPart model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(Mob entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ir1.zRot = ageInTicks * 0.067f * 1.25f;
		ir2.zRot = ageInTicks * 0.067f * 1.25f;
		ir3.zRot = ageInTicks * 0.067f * 1.25f;
		ir4.zRot = ageInTicks * 0.067f * 1.25f;
		or1.xRot = ageInTicks * 0.067f * 2.25f;
		or2.xRot = ageInTicks * 0.067f * 2.25f;
		or3.xRot = ageInTicks * 0.067f * 2.25f;
		or4.xRot = ageInTicks * 0.067f * 2.25f;
		or5.xRot = ageInTicks * 0.067f * 2.25f;
		or6.xRot = ageInTicks * 0.067f * 2.25f;
	}
}
