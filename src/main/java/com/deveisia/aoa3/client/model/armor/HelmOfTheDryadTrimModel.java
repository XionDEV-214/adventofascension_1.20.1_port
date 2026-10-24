package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.misc.FullbrightModelRenderer;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;

public class HelmOfTheDryadTrimModel extends SkillHelmetModel {
	public HelmOfTheDryadTrimModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_dryad_trim.png"));

		texWidth = 64;
		texHeight = 64;

		customHead = new LegacyModelPart(this);
		customHead.setPos(0.0F, 24.0F, 0.0F);
		customHead.texOffs(20, 0).addBox(-4.0F, -8.5F, -5.0F, 8.0F, 3.0F, 10.0F, 0.0F, false);
		customHead.texOffs(0, 32).addBox(-4.5F, -8.4F, 2.1F, 9.0F, 8.0F, 3.0F, 0.0F, false);
		customHead.texOffs(0, 0).addBox(-4.8F, -8.0F, -4.4F, 1.0F, 6.0F, 9.0F, 0.0F, true);
		customHead.texOffs(0, 0).addBox(3.8F, -8.0F, -4.4F, 1.0F, 6.0F, 9.0F, 0.0F, false);
		customHead.texOffs(12, 25).addBox(-1.5F, -8.7071F, -5.7071F, 3.0F, 4.0F, 1.0F, -0.25F, false);
		customHead.texOffs(0, 27).addBox(1.25F, -7.2071F, -5.45F, 2.0F, 2.0F, 1.0F, 0.0F, true);
		customHead.texOffs(0, 27).addBox(-3.25F, -7.2071F, -5.45F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		LegacyModelPart horn2 = new FullbrightModelRenderer(this);
		horn2.setPos(3.0F, -4.0F, -1.25F);
		customHead.addChild(horn2);
		setRotationAngle(horn2, -0.3054F, 0.0F, -0.6981F);
		horn2.texOffs(38, 34).addBox(-4.0F, -10.5F, -5.0F, 3.0F, 5.0F, 3.0F, -0.1F, true);

		LegacyModelPart bone21 = new LegacyModelPart(this);
		bone21.setPos(0.0F, 0.5F, -1.75F);
		horn2.addChild(bone21);
		setRotationAngle(bone21, -0.4443F, -0.0873F, 0.4005F);

		LegacyModelPart cube_r1 = new LegacyModelPart(this);
		cube_r1.setPos(-3.6837F, -1.4382F, -3.0115F);
		bone21.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.0F);
		cube_r1.texOffs(46, 42).addBox(-3.0F, -14.5F, -2.75F, 1.0F, 4.0F, 1.0F, 0.1F, true);
		cube_r1.texOffs(38, 42).addBox(-3.75F, -10.5F, -3.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		LegacyModelPart bone26 = new LegacyModelPart(this);
		bone26.setPos(-3.6837F, -1.4382F, -3.0115F);
		bone21.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, -0.8727F);

		LegacyModelPart cube_r2 = new LegacyModelPart(this);
		cube_r2.setPos(8.8186F, 2.3102F, -0.15F);
		bone26.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.0F);
		cube_r2.texOffs(46, 42).addBox(-4.0F, -15.0F, -2.25F, 1.0F, 4.0F, 1.0F, 0.1F, true);

		LegacyModelPart bone24 = new LegacyModelPart(this);
		bone24.setPos(5.25F, -7.5F, 3.25F);
		horn2.addChild(bone24);
		setRotationAngle(bone24, -0.1128F, -0.4389F, -0.7285F);

		LegacyModelPart cube_r3 = new LegacyModelPart(this);
		cube_r3.setPos(-3.6837F, -1.4382F, -3.0115F);
		bone24.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.0F);
		cube_r3.texOffs(46, 42).addBox(-3.75F, -9.5F, -3.0F, 1.0F, 4.0F, 1.0F, 0.1F, true);

		LegacyModelPart bone25 = new LegacyModelPart(this);
		bone25.setPos(0.0F, 0.0F, 0.0F);
		bone24.addChild(bone25);
		setRotationAngle(bone25, 0.0F, 0.0F, 0.6545F);

		LegacyModelPart cube_r4 = new LegacyModelPart(this);
		cube_r4.setPos(-8.8465F, 1.2291F, -3.0115F);
		bone25.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.0F);
		cube_r4.texOffs(46, 42).addBox(-3.75F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F, -0.1F, true);

		LegacyModelPart horn1 = new FullbrightModelRenderer(this);
		horn1.setPos(-3.0F, -4.0F, -1.25F);
		customHead.addChild(horn1);
		setRotationAngle(horn1, -0.3054F, 0.0F, 0.6981F);
		horn1.texOffs(38, 34).addBox(1.0F, -10.5F, -5.0F, 3.0F, 5.0F, 3.0F, -0.1F, false);

		LegacyModelPart bone22 = new LegacyModelPart(this);
		bone22.setPos(0.0F, 0.5F, -1.75F);
		horn1.addChild(bone22);
		setRotationAngle(bone22, -0.4443F, 0.0873F, -0.4005F);

		LegacyModelPart cube_r5 = new LegacyModelPart(this);
		cube_r5.setPos(3.6837F, -1.4382F, -3.0115F);
		bone22.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.0F);
		cube_r5.texOffs(46, 42).addBox(2.0F, -14.5F, -2.75F, 1.0F, 4.0F, 1.0F, 0.1F, false);
		cube_r5.texOffs(38, 42).addBox(1.75F, -10.5F, -3.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);

		LegacyModelPart bone23 = new LegacyModelPart(this);
		bone23.setPos(3.6837F, -1.4382F, -3.0115F);
		bone22.addChild(bone23);
		setRotationAngle(bone23, 0.0F, 0.0F, 0.8727F);

		LegacyModelPart cube_r6 = new LegacyModelPart(this);
		cube_r6.setPos(-8.8186F, 2.3102F, -0.15F);
		bone23.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.0F);
		cube_r6.texOffs(46, 42).addBox(3.0F, -15.0F, -2.25F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		LegacyModelPart bone27 = new LegacyModelPart(this);
		bone27.setPos(-5.25F, -7.5F, 3.25F);
		horn1.addChild(bone27);
		setRotationAngle(bone27, -0.1128F, 0.4389F, 0.7285F);

		LegacyModelPart cube_r7 = new LegacyModelPart(this);
		cube_r7.setPos(3.6837F, -1.4382F, -3.0115F);
		bone27.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.0F);
		cube_r7.texOffs(46, 42).addBox(2.75F, -9.5F, -3.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		LegacyModelPart bone28 = new LegacyModelPart(this);
		bone28.setPos(0.0F, 0.0F, 0.0F);
		bone27.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, -0.6545F);

		LegacyModelPart cube_r8 = new LegacyModelPart(this);
		cube_r8.setPos(8.8465F, 1.2291F, -3.0115F);
		bone28.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.0F);
		cube_r8.texOffs(46, 42).addBox(2.75F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F, -0.1F, false);

		LegacyModelPart bone3 = new LegacyModelPart(this);
		bone3.setPos(0.0F, -6.25F, 0.25F);
		customHead.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.7854F);
		bone3.texOffs(0, 24).addBox(-0.1205F, -0.1697F, -5.8071F, 2.0F, 2.0F, 1.0F, -0.2F, false);

		LegacyModelPart bone4 = new LegacyModelPart(this);
		bone4.setPos(0.0F, -6.25F, 0.25F);
		customHead.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 1.0472F);
		bone4.texOffs(20, 22).addBox(0.7961F, -3.2931F, -5.6071F, 4.0F, 2.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone6 = new LegacyModelPart(this);
		bone6.setPos(-0.7039F, 4.4569F, -0.5571F);
		bone4.addChild(bone6);
		setRotationAngle(bone6, 0.0F, -0.8727F, 0.0F);
		bone6.texOffs(20, 25).addBox(-0.3332F, -7.74F, -7.4593F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone7 = new LegacyModelPart(this);
		bone7.setPos(4.3098F, -7.4312F, -6.6234F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.7418F);
		bone7.texOffs(20, 25).addBox(-2.9012F, 2.2444F, -0.8259F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone8 = new LegacyModelPart(this);
		bone8.setPos(-2.5054F, 10.239F, 4.55F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.6981F, 0.0F);
		bone8.texOffs(24, 25).addBox(3.2821F, -8.0128F, -3.9714F, 3.0F, 1.0F, 1.0F, 0.0F, true);
		bone8.texOffs(24, 25).addBox(3.2821F, -7.2628F, -3.9614F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone9 = new LegacyModelPart(this);
		bone9.setPos(1.7821F, 1.7372F, 1.0786F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.5236F, 0.0F, 0.0F);
		bone9.texOffs(20, 27).addBox(2.25F, -10.6027F, 0.8676F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		LegacyModelPart bone10 = new LegacyModelPart(this);
		bone10.setPos(0.0F, -0.8057F, 5.3356F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, -1.1345F, 0.0F);

		LegacyModelPart cube_r9 = new LegacyModelPart(this);
		cube_r9.setPos(3.9776F, 0.953F, -0.2262F);
		bone10.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.0F);
		cube_r9.texOffs(29, 27).addBox(-4.5F, -9.0F, 4.2F, 2.0F, 2.0F, 2.0F, 0.0F, true);

		LegacyModelPart cube_r10 = new LegacyModelPart(this);
		cube_r10.setPos(-3.0224F, 0.203F, -0.2262F);
		bone10.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.0F);
		cube_r10.texOffs(27, 22).addBox(2.5F, -10.25F, -3.8F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		LegacyModelPart bone5 = new LegacyModelPart(this);
		bone5.setPos(0.0F, -6.25F, 0.25F);
		customHead.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -1.0472F);
		bone5.texOffs(20, 22).addBox(-4.7961F, -3.2931F, -5.6071F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone11 = new LegacyModelPart(this);
		bone11.setPos(0.7039F, 4.4569F, -0.5571F);
		bone5.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.8727F, 0.0F);
		bone11.texOffs(20, 25).addBox(-0.6668F, -7.74F, -7.4593F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone12 = new LegacyModelPart(this);
		bone12.setPos(-4.3098F, -7.4312F, -6.6234F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.7418F);
		bone12.texOffs(20, 25).addBox(1.9012F, 2.2444F, -0.8259F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone13 = new LegacyModelPart(this);
		bone13.setPos(2.5054F, 10.239F, 4.55F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, -0.6981F, 0.0F);
		bone13.texOffs(24, 25).addBox(-6.2821F, -8.0128F, -3.9714F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone13.texOffs(24, 25).addBox(-6.2821F, -7.2628F, -3.9614F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone14 = new LegacyModelPart(this);
		bone14.setPos(-1.7821F, 1.7372F, 1.0786F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.5236F, 0.0F, 0.0F);
		bone14.texOffs(20, 27).addBox(-4.25F, -10.6027F, 0.8676F, 2.0F, 1.0F, 4.0F, 0.0F, true);

		LegacyModelPart bone15 = new LegacyModelPart(this);
		bone15.setPos(0.0F, -0.8057F, 5.3356F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 1.1345F, 0.0F);

		LegacyModelPart cube_r11 = new LegacyModelPart(this);
		cube_r11.setPos(3.0224F, 0.203F, -0.2262F);
		bone15.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.0F);
		cube_r11.texOffs(29, 27).addBox(-4.5F, -8.25F, 4.2F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		cube_r11.texOffs(27, 22).addBox(-4.5F, -10.25F, -3.8F, 2.0F, 2.0F, 10.0F, 0.0F, true);

		LegacyModelPart bone2 = new LegacyModelPart(this);
		bone2.setPos(0.0F, -6.25F, 0.25F);
		customHead.addChild(bone2);
		setRotationAngle(bone2, 0.7854F, 0.0F, 0.0F);
		bone2.texOffs(37, 14).addBox(-3.5F, -5.0F, -2.25F, 7.0F, 3.0F, 4.0F, 0.0F, false);
		bone2.texOffs(0, 17).addBox(-1.5F, -5.5962F, -2.4749F, 3.0F, 2.0F, 5.0F, 0.0F, false);

		LegacyModelPart bone16 = new LegacyModelPart(this);
		bone16.setPos(0.0F, -6.25F, 2.25F);
		customHead.addChild(bone16);
		setRotationAngle(bone16, 0.3927F, 0.0F, 0.0F);
		bone16.texOffs(15, 13).addBox(-3.0F, -5.0F, -2.25F, 6.0F, 4.0F, 5.0F, 0.0F, false);
		bone16.texOffs(1, 43).addBox(-4.0F, 4.0F, -0.6F, 8.0F, 3.0F, 2.0F, 0.0F, false);
		bone16.texOffs(0, 24).addBox(-1.495F, -5.5962F, -2.4749F, 3.0F, 2.0F, 6.0F, 0.0F, false);
		bone16.texOffs(41, 26).addBox(-2.0F, -3.5962F, 1.7751F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		LegacyModelPart bone17 = new LegacyModelPart(this);
		bone17.setPos(0.0F, 0.9038F, 2.5251F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, -0.3054F, 0.0F, 0.0F);
		bone17.texOffs(24, 34).addBox(-1.5F, -4.25F, -0.75F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		LegacyModelPart bone18 = new LegacyModelPart(this);
		bone18.setPos(-0.5F, 0.0F, 1.25F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, -0.7854F, 0.0F, 0.0F);
		bone18.texOffs(25, 40).addBox(-0.5F, -3.25F, -1.75F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		LegacyModelPart bone19 = new LegacyModelPart(this);
		bone19.setPos(-0.5F, 1.0F, 2.5F);
		bone17.addChild(bone19);
		setRotationAngle(bone19, -0.3927F, 0.0F, 0.0F);
		bone19.texOffs(23, 45).addBox(-1.5F, -3.25F, -1.75F, 4.0F, 5.0F, 3.0F, 0.0F, false);

		LegacyModelPart bone20 = new LegacyModelPart(this);
		bone20.setPos(-1.5F, 4.5F, 2.75F);
		bone17.addChild(bone20);
		setRotationAngle(bone20, 0.48F, 0.0F, 0.0F);
		bone20.texOffs(26, 53).addBox(0.5F, -3.25F, -0.75F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		LegacyModelPart bone29 = new FullbrightModelRenderer(this);
		bone29.setPos(0.0F, 6.25F, -0.25F);
		bone2.addChild(bone29);
		bone29.texOffs(11, 0).addBox(-1.0F, -12.3462F, -1.2249F, 2.0F, 1.0F, 3.0F, 0.0F, false);
	}
}
