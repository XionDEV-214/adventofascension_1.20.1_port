package com.deveisia.aoa3.client.model.entity.misc;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

public class LunarcherModel<T extends FlyingMob> extends HumanoidModel<FlyingMob> {
	public LunarcherModel() {
		super(LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0), 64, 32).bakeRoot());
	}

	@Override
	public void prepareMobModel(FlyingMob entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
		this.rightArmPose = ArmPose.EMPTY;
		this.leftArmPose = ArmPose.EMPTY;
		ItemStack itemstack = entity.getItemInHand(InteractionHand.MAIN_HAND);

		if (itemstack.getItem() instanceof BowItem && entity.isAggressive()) {
			if (entity.getMainArm() == HumanoidArm.RIGHT) {
				this.rightArmPose = ArmPose.BOW_AND_ARROW;
			}
			else {
				this.leftArmPose = ArmPose.BOW_AND_ARROW;
			}
		}

		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTickTime);
	}

	@Override
	public void setupAnim(FlyingMob entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ItemStack itemstack = entity.getMainHandItem();

		if (entity.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof BowItem))) {
			float f = Mth.sin(this.attackTime * (float)Math.PI);
			float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2F);
			this.leftArm.xRot = -((float)Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		}
	}

	@Override
	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		float rot = sideIn == HumanoidArm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelrenderer = this.getArm(sideIn);
		modelrenderer.x += rot;
		modelrenderer.translateAndRotate(matrixStackIn);
		modelrenderer.x -= rot;
	}
}
