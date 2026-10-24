package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;

public class HelmOfTheTinkererTrimModel extends SkillHelmetModel {
	public HelmOfTheTinkererTrimModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_tinkerer_trim.png"));

		texWidth = 64;
		texHeight = 64;

		customHead = new LegacyModelPart(this);
	}
}
