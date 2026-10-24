package com.deveisia.aoa3.client.render.entity.boss;

import com.deveisia.aoa3.client.model.entity.boss.BaronessModel;
import com.deveisia.aoa3.client.render.entity.AoAMobRenderer;
import com.deveisia.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;
import com.deveisia.aoa3.common.registration.AoAEntities;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class BaronessRenderer extends AoAMobRenderer {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/baroness/baroness.png");
	private static final ResourceLocation INVULN_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/boss/baroness/invulnerable_baroness.png");
	private static final ResourceLocation AURA_TEXTURE = ResourceLocation.fromNamespaceAndPath("aoa3", "textures/entity/misc/invulnerability_aura.png");

	public BaronessRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new BaronessModel(0), AoAEntities.Mobs.BARONESS.get().getWidth() / 3, 1f, TEXTURE);

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new BaronessModel(0.5f), AURA_TEXTURE));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Mob baroness) {
		return baroness.isInvulnerable() ? INVULN_TEXTURE : TEXTURE;
	}
}
