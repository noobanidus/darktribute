package noobanidus.mods.darktribute.networking;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public interface ITributePacket extends CustomPacketPayload {
  void handle (IPayloadContext context);
}
