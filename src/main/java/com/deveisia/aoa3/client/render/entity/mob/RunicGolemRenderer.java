package com.deveisia.aoa3.client.render.entity.mob;

import com.deveisia.aoa3.client.model.entity.mob.mysterium.RunicGolemModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class RunicGolemRenderer extends AoAMobRenderer {
	private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/mysterium/runic_golem.png");
	private final ResourceLocation invulnTexture = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/misc/invulnerability_aura.png");

	public RunicGolemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RunicGolemModel(0), AoAEntities.Mobs.RUNIC_GOLEM.get().getWidth() / 3, 1f, ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/mob/mysterium/runic_golem.png"));

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new RunicGolemModel(0.5f), invulnTexture));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Mob runicGolem) {
		return texture;
	}
}
