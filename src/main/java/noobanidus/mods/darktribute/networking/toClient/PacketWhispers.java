package noobanidus.mods.darktribute.networking.toClient;


import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.networking.ITributePacket;

public record PacketWhispers() implements ITributePacket {
  private static final PacketWhispers INSTANCE = new PacketWhispers();
  public static final CustomPacketPayload.Type<PacketWhispers> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "whispers"));
  public static StreamCodec<ByteBuf, PacketWhispers> STREAM_CODEC = StreamCodec.unit(INSTANCE);

  @Override
  public void handle(IPayloadContext context) {
    context.enqueueWork(() -> ClientNetworkHandler.playSound());
  }

  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }

}
