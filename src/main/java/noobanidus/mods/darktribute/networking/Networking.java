package noobanidus.mods.darktribute.networking;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import noobanidus.mods.darktribute.DarkTribute;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Networking {
  public static Networking INSTANCE = new Networking(DarkTribute.MODID);
  private final String PROTOCOL_VERSION = Integer.toString(2);
  private short index = 0;

  public final SimpleChannel HANDLER;

  public Networking(String modid) {
    this.HANDLER = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation(modid, "main_network_channel"))
        .clientAcceptedVersions(PROTOCOL_VERSION::equals)
        .serverAcceptedVersions(PROTOCOL_VERSION::equals)
        .networkProtocolVersion(() -> PROTOCOL_VERSION)
        .simpleChannel();
  }

  private int id = 0;

  public void sendToInternal(Object msg, ServerPlayerEntity player) {
    if (!(player instanceof FakePlayer))
      HANDLER.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
  }

  public void sendToServerInternal(Object msg) {
    HANDLER.sendToServer(msg);
  }

  public <MSG> void sendInternal(PacketDistributor.PacketTarget target, MSG message) {
    HANDLER.send(target, message);
  }

  public <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
    HANDLER.registerMessage(index, messageType, encoder, decoder, messageConsumer);
    index++;
    if (index > 0xFF)
      throw new RuntimeException("Too many messages!");
  }

  public static void registerMessages () {
    INSTANCE.doRegisterMessages();
  }

  private void doRegisterMessages() {
    //registerMessage(ShoulderRide.class, ShoulderRide::encode, ShoulderRide::new, ShoulderRide::handle);
    registerMessage(PacketParticles.class, PacketParticles::encode, PacketParticles::new, PacketParticles::handle);
    registerMessage(PacketBanner.class, PacketBanner::encode, PacketBanner::new, PacketBanner::handle);
    registerMessage(PacketWhispers.class, PacketWhispers::encode, PacketWhispers::new, PacketWhispers::handle);
  }

  public static void sendTo(Object msg, ServerPlayerEntity player) {
    INSTANCE.sendToInternal(msg, player);
  }

  public static void sendToServer(Object msg) {
    INSTANCE.sendToServerInternal(msg);
  }

  public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
    INSTANCE.sendInternal(target, message);
  }
}
