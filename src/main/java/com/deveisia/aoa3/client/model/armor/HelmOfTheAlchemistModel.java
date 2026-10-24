package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.deveisia.aoa3.client.model.compat.LegacyModelPart;

public class HelmOfTheAlchemistModel extends SkillHelmetModel {
	public HelmOfTheAlchemistModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_alchemist.png"));

		texWidth = 64;
		texHeight = 64;

		customHead = new LegacyModelPart(this);
	}
}
