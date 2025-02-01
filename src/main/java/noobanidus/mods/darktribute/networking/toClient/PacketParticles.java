package noobanidus.mods.darktribute.networking.toClient;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.networking.ITributePacket;

public record PacketParticles(double posX, double posY, double posZ, int count) implements ITributePacket {
  public static StreamCodec<ByteBuf, PacketParticles> STREAM_CODEC = StreamCodec.composite(
      ByteBufCodecs.DOUBLE,
      PacketParticles::posX,
      ByteBufCodecs.DOUBLE,
      PacketParticles::posY,
      ByteBufCodecs.DOUBLE,
      PacketParticles::posZ,
      ByteBufCodecs.VAR_INT,
      PacketParticles::count,
      PacketParticles::new
  );

  public static final CustomPacketPayload.Type<PacketParticles> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "particles"));

  @Override
  public void handle(IPayloadContext context) {
    context.enqueueWork(() -> ClientNetworkHandler.displayParticles(this.posX, this.posY, this.posZ, this.count));
  }

  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
