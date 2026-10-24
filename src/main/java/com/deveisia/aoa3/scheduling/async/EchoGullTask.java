package com.deveisia.aoa3.scheduling.async;

import com.deveisia.aoa3.library.builder.EffectBuilder;
import com.deveisia.aoa3.scheduling.AoAScheduler;
import com.deveisia.aoa3.util.EntityUtil;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.util.Tuple;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EchoGullTask implements Runnable {
	private final Level world;
	private final ArrayList<Tuple<LivingEntity, Integer>> entityList;

	public EchoGullTask(Level world, ArrayList<Tuple<LivingEntity, Integer>> entities) {
		this.world = world;
		this.entityList = entities;
	}

	@Override
	public void run() {
		if (!entityList.isEmpty()) {
			Iterator<Tuple<LivingEntity, Integer>> it = entityList.iterator();
			int distance = 0;

			while (it.hasNext()) {
				Tuple<LivingEntity, Integer> entry = it.next();

				if (distance == 0 || entry.getB() <= distance + 1) {
					distance = entry.getB();

					EntityUtil.applyPotions(entry.getA(), new EffectBuilder(MobEffects.GLOWING, 7));
					it.remove();
				}
				else {
					if (!entityList.isEmpty())
						AoAScheduler.scheduleSyncronisedTask(this, 1);

					return;
				}
			}
		}
	}
}
