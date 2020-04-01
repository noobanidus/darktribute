package noobanidus.mods.darktribute.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import noobanidus.mods.darktribute.client.hud.BannerManager;
import noobanidus.mods.darktribute.init.ModSounds;

import java.util.function.Supplier;

public class PacketWhispers {
  public PacketWhispers(PacketBuffer buffer) {
  }

  public PacketWhispers() {
  }

  public void encode(PacketBuffer buf) {
  }

  public void handle(Supplier<NetworkEvent.Context> context) {
    context.get().enqueueWork(() -> handle(this, context));
  }

  @OnlyIn(Dist.CLIENT)
  public void handle(PacketWhispers message, Supplier<NetworkEvent.Context> context) {
    Minecraft mc = Minecraft.getInstance();
    if (mc.world != null && mc.player != null) {
      mc.world.playSound(mc.player.getPosition(), ModSounds.WHISPERS.get(), SoundCategory.NEUTRAL, 1f, 1f, false);
    }
    context.get().setPacketHandled(true);
  }
}
