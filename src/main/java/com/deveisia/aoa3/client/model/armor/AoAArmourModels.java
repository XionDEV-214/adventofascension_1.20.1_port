package com.deveisia.aoa3.client.model.armor;

import com.deveisia.aoa3.common.registration.custom.AoASkills;
import com.deveisia.aoa3.player.skill.AoASkill;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;

public final class AoAArmourModels {
	private static final HashMap<AoASkill, Pair<? extends SkillHelmetModel, ? extends SkillHelmetModel>> skillHelmetModels = new HashMap<AoASkill, Pair<? extends SkillHelmetModel, ? extends SkillHelmetModel>>(10);

	@Nullable
	public static SkillHelmetModel getSkillHelmetModel(AoASkill skill, boolean trim) {
		if (skillHelmetModels.isEmpty()) {

			skillHelmetModels.put(AoASkills.DEXTERITY.get(), Pair.of(new HelmOfTheDextrousModel(), new HelmOfTheDextrousTrimModel()));

			skillHelmetModels.put(AoASkills.EXTRACTION.get(), Pair.of(new HelmOfTheTreasurerModel(), new HelmOfTheTreasurerTrimModel()));
			skillHelmetModels.put(AoASkills.FARMING.get(), Pair.of(new HelmOfTheDryadModel(), new HelmOfTheDryadTrimModel()));

			skillHelmetModels.put(AoASkills.HAULING.get(), Pair.of(new HelmOfTheTrawlerModel(), new HelmOfTheTrawlerTrimModel()));

			skillHelmetModels.put(AoASkills.INNERVATION.get(), Pair.of(new HelmOfTheWarriorModel(), new HelmOfTheWarriorTrimModel()));
		}

		if (!skillHelmetModels.containsKey(skill))
			return null;

		return trim ? skillHelmetModels.get(skill).getSecond() : skillHelmetModels.get(skill).getFirst();
	}

	@Nullable
	public static ResourceLocation getSkillHelmetTexture(AoASkill skill, boolean trim) {
		SkillHelmetModel model = getSkillHelmetModel(skill, trim);

		return model == null ? null : model.getTexturePath();
	}
}
