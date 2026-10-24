package com.deveisia.aoa3.mixin.client.accessor;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Gui.class)
public interface GuiAccessor {
	@Accessor("tickCount")
	int getTickCount();

	@Accessor("lastHealth")
	int getLastHealth();

	@Accessor("lastHealth")
	void setLastHealth(int health);

	@Accessor("displayHealth")
	void setDisplayHealth(int health);

	@Accessor("lastHealthTime")
	long getLastHealthTime();

	@Accessor("lastHealthTime")
	void setLastHealthTime(long time);

	@Accessor("healthBlinkTime")
	long getHealthBlinkTime();

	@Accessor("healthBlinkTime")
	void setHealthBlinkTime(long time);
}
