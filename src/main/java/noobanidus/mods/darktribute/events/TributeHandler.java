package noobanidus.mods.darktribute.events;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.config.ConfigManager;
import noobanidus.mods.darktribute.entities.DiamondEntity;
import noobanidus.mods.darktribute.init.ModAdvancements;
import noobanidus.mods.darktribute.init.ModSounds;
import noobanidus.mods.darktribute.networking.Networking;
import noobanidus.mods.darktribute.networking.PacketBanner;
import noobanidus.mods.darktribute.networking.PacketParticles;
import noobanidus.mods.darktribute.networking.PacketWhispers;

public class TributeHandler {
  public static void giveTribute(DiamondEntity diamond, Player player, int count) {
    ServerPlayer serverPlayer = (ServerPlayer) player;
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    CommandSourceStack source = server.createCommandSourceStack();
    for (int i = 0; i < count; i++) {
      String command = ConfigManager.getCommand(serverPlayer);
      if (command != null) {
        server.getCommands().performCommand(source, command);
      }
    }

    PacketParticles message = new PacketParticles(diamond.getX(), diamond.getY(), diamond.getZ());
    Networking.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), message);
    Networking.sendTo(message, (ServerPlayer) player);

    PacketBanner banner = new PacketBanner();
    Networking.sendTo(banner, (ServerPlayer) player);

    player.level.playSound(null, player.blockPosition(), ModSounds.CACKLE.get(), SoundSource.NEUTRAL, 1, 0.85f);

    ModAdvancements.TRIBUTE_TRIGGER.trigger((ServerPlayer) player, null);

    ServerLifecycleHooks.getCurrentServer().getPlayerList().broadcastMessage(new TranslatableComponent("darktribute.message", player.getName()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), ChatType.CHAT, player.getUUID());
  }

  public static void onItemToss(ItemTossEvent event) {
    Player player = event.getPlayer();
    ItemEntity item = event.getEntityItem();
    if (item.getItem().getItem() == Items.DIAMOND) {
      event.setCanceled(true);
      if (!player.level.isClientSide()) {
        DiamondEntity diamond = new DiamondEntity(item);
        diamond.absMoveTo(item.getX(), item.getY(), item.getZ(), item.getYRot(), item.getXRot());
        diamond.setDeltaMovement(item.getDeltaMovement());
        diamond.setPickUpDelay(40);
        diamond.setThrower(player.getUUID());
        player.level.addFreshEntity(diamond);
      }
    }
  }

  private static ResourceLocation root = new ResourceLocation(DarkTribute.MODID, "root");

  public static void onAdvancementGiven(AdvancementEvent event) {
    if (event.getAdvancement().getId().equals(root)) {
      PacketWhispers packet = new PacketWhispers();
      Networking.sendTo(packet, (ServerPlayer) event.getPlayer());
    }
  }
}
