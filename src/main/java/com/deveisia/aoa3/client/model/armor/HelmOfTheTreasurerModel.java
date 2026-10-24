package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;

public class HelmOfTheTreasurerModel extends SkillHelmetModel {
	public HelmOfTheTreasurerModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_treasurer.png"));

		texWidth = 32;
		texHeight = 32;

		customHead = new LegacyModelPart(this);
		customHead.setPos(0.0F, 24.0F, 0.0F);
		customHead.texOffs(16, 3).addBox(-1.5F, -8.75F, -5.5F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		customHead.texOffs(17, 13).addBox(-1.0F, -8.25F, -5.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		customHead.texOffs(0, 10).addBox(1.5F, -8.25F, -4.5F, 2.0F, 2.0F, 1.0F, -0.01F, false);
		customHead.texOffs(0, 10).addBox(-3.5F, -8.25F, -4.5F, 2.0F, 2.0F, 1.0F, -0.01F, false);
		customHead.texOffs(16, 0).addBox(-3.5F, -8.25F, 3.5F, 7.0F, 2.0F, 1.0F, -0.01F, false);
		customHead.texOffs(0, 0).addBox(1.083F, -6.2641F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		customHead.texOffs(4, 3).addBox(-3.707F, -4.2588F, 3.48F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		customHead.texOffs(4, 3).addBox(2.707F, -4.2588F, 3.48F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		customHead.texOffs(0, 0).addBox(-2.1172F, -6.25F, 3.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		customHead.texOffs(0, 17).addBox(-1.5F, -7.0F, 4.25F, 3.0F, 7.0F, 0.0F, 0.0F, false);
		customHead.texOffs(12, 14).addBox(3.25F, -8.25F, -3.5F, 1.0F, 2.0F, 3.0F, -0.01F, false);
		customHead.texOffs(12, 14).addBox(-4.25F, -8.25F, -3.5F, 1.0F, 2.0F, 3.0F, -0.01F, true);
		customHead.texOffs(12, 14).addBox(3.25F, -8.25F, 0.5F, 1.0F, 2.0F, 3.0F, -0.01F, false);
		customHead.texOffs(12, 14).addBox(-4.25F, -8.25F, 0.5F, 1.0F, 2.0F, 3.0F, -0.01F, true);
		customHead.texOffs(22, 8).addBox(2.75F, -8.25F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		customHead.texOffs(12, 7).addBox(4.15F, -6.25F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, false);
		customHead.texOffs(12, 7).addBox(-4.15F, -6.25F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, true);
		customHead.texOffs(22, 8).addBox(-4.75F, -8.25F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		customHead.texOffs(0, 0).addBox(-2.0F, -9.5F, -4.0F, 4.0F, 2.0F, 8.0F, 0.01F, false);

		LegacyModelPart bone5 = new LegacyModelPart(this);
		bone5.setPos(-0.5F, 0.5F, -3.75F);
		customHead.addChild(bone5);
		setRotationAngle(bone5, -0.4363F, 0.0F, 0.0F);
		bone5.texOffs(4, 11).addBox(-0.5F, -10.25F, -5.25F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		LegacyModelPart rcube_r1 = new LegacyModelPart(this);
		rcube_r1.setPos(0.0F, 0.0F, 0.0F);
		bone5.addChild(rcube_r1);
		setRotationAngle(rcube_r1, -0.3491F, 0.0F, 0.0F);
		rcube_r1.texOffs(24, 3).addBox(0.0F, -8.9F, -8.05F, 1.0F, 1.0F, 1.0F, 0.1F, false);

		LegacyModelPart bone12 = new LegacyModelPart(this);
		bone12.setPos(-1.5F, 2.0F, 0.0F);
		customHead.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.7854F);
		bone12.texOffs(0, 13).addBox(-0.7587F, -4.8658F, 3.5F, 1.0F, 3.0F, 1.0F, -0.01F, false);
		bone12.texOffs(0, 6).addBox(-2.7445F, -2.8517F, 3.5F, 2.0F, 1.0F, 1.0F, -0.01F, false);
		bone12.texOffs(0, 6).addBox(-5.2892F, -3.5822F, 3.46F, 2.0F, 1.0F, 1.0F, -0.01F, false);
		bone12.texOffs(4, 0).addBox(-1.4608F, -7.3322F, 3.46F, 1.0F, 2.0F, 1.0F, -0.01F, true);

		LegacyModelPart bone11 = new LegacyModelPart(this);
		bone11.setPos(-1.0F, 0.5F, -1.25F);
		customHead.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
		bone11.texOffs(28, 4).addBox(4.65F, -5.25F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone11.texOffs(28, 4).addBox(-3.65F, -5.25F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
		bone11.texOffs(18, 10).addBox(4.65F, -3.25F, -3.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone11.texOffs(18, 10).addBox(-3.65F, -3.25F, -3.5F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		LegacyModelPart bone6 = new LegacyModelPart(this);
		bone6.setPos(2.85F, 1.25F, 0.0F);
		customHead.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3491F);
		bone6.texOffs(24, 3).addBox(3.75F, -9.25F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone9 = new LegacyModelPart(this);
		bone9.setPos(-2.85F, 1.25F, 0.0F);
		customHead.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 0.3491F);
		bone9.texOffs(24, 3).addBox(-4.75F, -9.25F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone2 = new LegacyModelPart(this);
		bone2.setPos(-0.5F, 0.0F, 0.0F);
		customHead.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.6109F, 0.0F);
		bone2.texOffs(22, 8).addBox(0.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		LegacyModelPart rcube_r2 = new LegacyModelPart(this);
		rcube_r2.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(rcube_r2);
		setRotationAngle(rcube_r2, -0.3491F, 0.0F, 0.0F);
		rcube_r2.texOffs(24, 3).addBox(0.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone8 = new LegacyModelPart(this);
		bone8.setPos(0.5F, 0.0F, 0.0F);
		customHead.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.6109F, 0.0F);
		bone8.texOffs(22, 8).addBox(-2.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

		LegacyModelPart rcube_r3 = new LegacyModelPart(this);
		rcube_r3.setPos(0.0F, 0.0F, 0.0F);
		bone8.addChild(rcube_r3);
		setRotationAngle(rcube_r3, -0.3491F, 0.0F, 0.0F);
		rcube_r3.texOffs(24, 3).addBox(-1.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone7 = new LegacyModelPart(this);
		bone7.setPos(0.5F, 0.0F, -0.75F);
		customHead.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -2.3562F, 0.0F);
		bone7.texOffs(22, 8).addBox(0.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		bone7.texOffs(0, 13).addBox(0.5305F, -6.26F, -5.9659F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		LegacyModelPart rcube_r4 = new LegacyModelPart(this);
		rcube_r4.setPos(0.0F, 0.0F, 0.0F);
		bone7.addChild(rcube_r4);
		setRotationAngle(rcube_r4, -0.3491F, 0.0F, 0.0F);
		rcube_r4.texOffs(24, 3).addBox(0.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LegacyModelPart bone10 = new LegacyModelPart(this);
		bone10.setPos(-0.5F, 0.0F, -0.75F);
		customHead.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 2.3562F, 0.0F);
		bone10.texOffs(22, 8).addBox(-2.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		bone10.texOffs(0, 13).addBox(-1.5305F, -6.26F, -5.9659F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		LegacyModelPart rcube_r5 = new LegacyModelPart(this);
		rcube_r5.setPos(0.0F, 0.0F, 0.0F);
		bone10.addChild(rcube_r5);
		setRotationAngle(rcube_r5, -0.3491F, 0.0F, 0.0F);
		rcube_r5.texOffs(24, 3).addBox(-1.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LegacyModelPart bone3 = new LegacyModelPart(this);
		bone3.setPos(-0.5F, 0.0F, 0.0F);
		customHead.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.6545F);
		bone3.texOffs(0, 16).addBox(-3.7999F, -9.0588F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		LegacyModelPart bone4 = new LegacyModelPart(this);
		bone4.setPos(0.5F, 0.0F, 0.0F);
		customHead.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.6545F);
		bone4.texOffs(0, 16).addBox(1.7999F, -9.0588F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F, true);
	}
}
