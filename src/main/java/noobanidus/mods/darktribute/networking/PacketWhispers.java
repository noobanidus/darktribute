package noobanidus.mods.darktribute.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import noobanidus.mods.darktribute.init.ModSounds;

import java.util.function.Supplier;

public class PacketWhispers {
  public PacketWhispers(FriendlyByteBuf buffer) {
  }

  public PacketWhispers() {
  }

  public void encode(FriendlyByteBuf buf) {
  }

  public void handle(Supplier<NetworkEvent.Context> context) {
    context.get().enqueueWork(() -> handle(this, context));
  }

  @OnlyIn(Dist.CLIENT)
  public void handle(PacketWhispers message, Supplier<NetworkEvent.Context> context) {
    Minecraft mc = Minecraft.getInstance();
    if (mc.level != null && mc.player != null) {
      mc.level.playLocalSound(mc.player.blockPosition(), ModSounds.WHISPERS.get(), SoundSource.NEUTRAL, 1f, 1f, false);
    }
    context.get().setPacketHandled(true);
  }
}
