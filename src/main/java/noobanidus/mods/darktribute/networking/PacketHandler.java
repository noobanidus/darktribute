package noobanidus.mods.darktribute.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.networking.toClient.PacketBanner;
import noobanidus.mods.darktribute.networking.toClient.PacketParticles;
import noobanidus.mods.darktribute.networking.toClient.PacketWhispers;

public class PacketHandler {

  public PacketHandler(IEventBus modEventBus) {
    modEventBus.addListener(RegisterPayloadHandlersEvent.class, event -> {
      PayloadRegistrar registrar = event.registrar(DarkTribute.NETWORK_VERSION);
      registerClientToServer(new PacketRegistrar(registrar, true));
      registerServerToClient(new PacketRegistrar(registrar, false));
    });
  }

  protected void registerClientToServer(PacketRegistrar registrar) {
  }

  protected void registerServerToClient(PacketRegistrar registrar) {
    registrar.play(PacketWhispers.TYPE, PacketWhispers.STREAM_CODEC);
    registrar.play(PacketParticles.TYPE, PacketParticles.STREAM_CODEC);
    registrar.play(PacketBanner.TYPE, PacketBanner.STREAM_CODEC);
  }

  protected record PacketRegistrar(PayloadRegistrar registrar, boolean toServer) {

    public <MSG extends ITributePacket> void play(CustomPacketPayload.Type<MSG> type, StreamCodec<? super RegistryFriendlyByteBuf, MSG> reader) {
      if (toServer) {
        registrar.playToServer(type, reader, ITributePacket::handle);
      } else {
        registrar.playToClient(type, reader, ITributePacket::handle);
      }
    }
  }
}
