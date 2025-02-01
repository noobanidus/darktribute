package noobanidus.mods.darktribute.networking.toClient;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.networking.ITributePacket;

public record PacketBanner() implements ITributePacket {
  private static PacketBanner INSTANCE = new PacketBanner();
  public static StreamCodec<ByteBuf, PacketBanner> STREAM_CODEC = StreamCodec.unit(INSTANCE);
  public static final CustomPacketPayload.Type<PacketBanner> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "banner"));

  @Override
  public void handle(IPayloadContext context) {
    context.enqueueWork(ClientNetworkHandler::startBanner);
  }

  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
