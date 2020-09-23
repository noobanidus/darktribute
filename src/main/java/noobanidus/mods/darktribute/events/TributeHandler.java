package noobanidus.mods.darktribute.events;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
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
  public static void giveTribute(DiamondEntity diamond, PlayerEntity player, int count) {
    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    CommandSource source = server.getCommandSource();
    for (int i = 0; i < count; i++) {
      String command = ConfigManager.getCommand(serverPlayer);
      if (command != null) {
        server.getCommandManager().handleCommand(source, command);
      }
    }

    PacketParticles message = new PacketParticles(diamond.getPosX(), diamond.getPosY(), diamond.getPosZ());
    Networking.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), message);
    Networking.sendTo(message, (ServerPlayerEntity) player);

    PacketBanner banner = new PacketBanner();
    Networking.sendTo(banner, (ServerPlayerEntity) player);

    player.world.playSound(null, player.getPosition(), ModSounds.CACKLE.get(), SoundCategory.NEUTRAL, 1, 0.85f);

    ModAdvancements.TRIBUTE_TRIGGER.trigger((ServerPlayerEntity) player, null);

    ServerLifecycleHooks.getCurrentServer().getPlayerList().func_232641_a_(new TranslationTextComponent("darktribute.message", player.getName()).setStyle(Style.EMPTY.setFormatting(TextFormatting.DARK_RED)), ChatType.CHAT, player.getUniqueID());
  }

  public static void onItemToss(ItemTossEvent event) {
    PlayerEntity player = event.getPlayer();
    ItemEntity item = event.getEntityItem();
    if (item.getItem().getItem() == Items.DIAMOND) {
      event.setCanceled(true);
      if (!player.world.isRemote()) {
        DiamondEntity diamond = new DiamondEntity(item);
        diamond.setPositionAndRotation(item.getPosX(), item.getPosY(), item.getPosZ(), item.rotationYaw, item.rotationPitch);
        diamond.setMotion(item.getMotion());
        diamond.setPickupDelay(40);
        diamond.setThrowerId(player.getUniqueID());
        player.world.addEntity(diamond);
      }
    }
  }

  private static ResourceLocation root = new ResourceLocation(DarkTribute.MODID, "root");

  public static void onAdvancementGiven(AdvancementEvent event) {
    if (event.getAdvancement().getId().equals(root)) {
      PacketWhispers packet = new PacketWhispers();
      Networking.sendTo(packet, (ServerPlayerEntity) event.getPlayer());
    }
  }
}
