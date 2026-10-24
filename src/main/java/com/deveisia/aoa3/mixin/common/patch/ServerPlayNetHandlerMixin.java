package com.deveisia.aoa3.mixin.common.patch;

import com.deveisia.aoa3.util.EntityUtil;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraftforge.common.ForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerPlayNetHandlerMixin {
	@Shadow
	private double lastGoodY;

	@Redirect(method = "handleMovePlayer",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;lastGoodZ:D"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V")
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;onGround()Z"),
			require = 0)
	private boolean aoa3$onGround(ServerPlayer player, ServerboundMovePlayerPacket packet) {
		if (packet.getY(player.getY()) - this.lastGoodY <= 0 || packet.isOnGround())
			return false;

		return player.onGround() || (player.getDeltaMovement().y() < -player.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get()) && packet.getY(player.getY()) == player.getY() + EntityUtil.getEntityJumpVelocity(player));
	}
}
