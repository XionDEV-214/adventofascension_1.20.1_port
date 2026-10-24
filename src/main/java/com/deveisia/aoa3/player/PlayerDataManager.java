package com.deveisia.aoa3.player;

import com.deveisia.aoa3.player.resource.AoAResource;
import com.deveisia.aoa3.player.skill.AoASkill;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface PlayerDataManager {
	Player player();

	void updatePlayerInstance(Player player);

	boolean isLegitimate();

	int getTotalLevel();

	Collection<AoASkill.Instance> getSkills();

	@Nonnull
	AoASkill.Instance getSkill(AoASkill skill);

	Collection<AoAResource.Instance> getResources();

	@Nonnull
	AoAResource.Instance getResource(AoAResource resource);

	void loadFromNbt(CompoundTag nbt);

	void addListener(AoAPlayerEventListener listener, boolean active, AoAPlayerEventListener.ListenerType... types);

	List<AoAPlayerEventListener> getListeners(AoAPlayerEventListener.ListenerType eventType);
}
