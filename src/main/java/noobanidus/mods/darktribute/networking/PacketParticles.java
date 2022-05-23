package noobanidus.mods.darktribute.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import noobanidus.mods.darktribute.particles.DiamondParticleData;

import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class PacketParticles {
  private double posX;
  private double posY;
  private double posZ;

  public PacketParticles(FriendlyByteBuf buffer) {
    posX = buffer.readDouble();
    posY = buffer.readDouble();
    posZ = buffer.readDouble();
  }

  public PacketParticles(double posX, double posY, double posZ) {
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
  }

  public void encode(FriendlyByteBuf buf) {
    buf.writeDouble(posX);
    buf.writeDouble(posY);
    buf.writeDouble(posZ);
  }

  public void handle(Supplier<NetworkEvent.Context> context) {
    context.get().enqueueWork(() -> handle(this, context));
  }

  @OnlyIn(Dist.CLIENT)
  public void handle(PacketParticles message, Supplier<NetworkEvent.Context> context) {
    Minecraft mc = Minecraft.getInstance();
    //noinspection ConstantConditions
    if (mc != null && mc.player != null) {
      mc.player.level.addParticle(new DiamondParticleData(10f, 1f, 1f, 1f, 1f, 1f, 0f), message.posX, message.posY, message.posZ, 0, 0, 0);
    }

    context.get().setPacketHandled(true);
  }
}
