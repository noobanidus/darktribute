package noobanidus.mods.darktribute.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import noobanidus.mods.darktribute.client.hud.BannerManager;

import java.util.function.Supplier;

public class PacketBanner {
  public PacketBanner(FriendlyByteBuf buffer) {
  }

  public PacketBanner() {
  }

  public void encode(FriendlyByteBuf buf) {
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
