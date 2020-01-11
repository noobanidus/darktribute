package noobanidus.mods.darktribute.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import noobanidus.mods.darktribute.client.hud.BannerManager;
import noobanidus.mods.darktribute.particles.DiamondParticle;

import java.util.function.Supplier;

public class PacketBanner {
  public PacketBanner(PacketBuffer buffer) {
  }

  public PacketBanner() {
  }

  public void encode(PacketBuffer buf) {
  }

  public void handle(Supplier<NetworkEvent.Context> context) {
    context.get().enqueueWork(() -> handle(this, context));
  }

  @OnlyIn(Dist.CLIENT)
  public void handle(PacketBanner message, Supplier<NetworkEvent.Context> context) {
    BannerManager.startTribute();
    context.get().setPacketHandled(true);
  }
}
