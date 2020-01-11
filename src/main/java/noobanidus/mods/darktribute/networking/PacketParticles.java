package noobanidus.mods.darktribute.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import noobanidus.mods.darktribute.particles.DiamondParticle;

import java.util.function.Supplier;

public class PacketParticles {
  private double posX;
  private double posY;
  private double posZ;

  public PacketParticles(PacketBuffer buffer) {
    posX = buffer.readDouble();
    posY = buffer.readDouble();
    posZ = buffer.readDouble();
  }

  public PacketParticles(double posX, double posY, double posZ) {
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
  }

  public void encode(PacketBuffer buf) {
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
    mc.player.world.addParticle(new DiamondParticle.Data(10f, 1f, 1f, 1f, 1f, 1f, 20, 0f), message.posX, message.posY, message.posZ, 0, 0, 0);

    context.get().setPacketHandled(true);
  }
}
