package com.deveisia.aoa3.content.entity.misc;

import com.deveisia.aoa3.common.registration.AoAEntities;
import com.deveisia.aoa3.util.WorldUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class FakeTntEntity extends PrimedTnt {
	private final Entity owner;

	public FakeTntEntity(Level world, BlockPos position, Entity owner) {
		super(AoAEntities.Misc.FAKE_TNT.get(), world);

		this.owner = owner;

		moveTo(position.getX(), position.getY(), position.getZ(), random.nextFloat() * 360.0f, 0.0f);
	}

	public FakeTntEntity(EntityType<? extends PrimedTnt> entityType, Level world) {
		super(entityType, world);

		this.owner = null;
	}

	protected void explode() {
		WorldUtil.createExplosion(this.owner, level(), this, 4.0f);
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
