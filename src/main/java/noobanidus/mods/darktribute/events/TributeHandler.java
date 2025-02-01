package noobanidus.mods.darktribute.events;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.DarkTributeTags;
import noobanidus.mods.darktribute.config.ConfigManager;
import noobanidus.mods.darktribute.init.ModAdvancements;
import noobanidus.mods.darktribute.init.ModSounds;
import noobanidus.mods.darktribute.networking.toClient.PacketBanner;
import noobanidus.mods.darktribute.networking.toClient.PacketParticles;
import noobanidus.mods.darktribute.networking.toClient.PacketWhispers;

@EventBusSubscriber(modid= DarkTribute.MODID, bus=EventBusSubscriber.Bus.GAME)
public class TributeHandler {
  public static void giveTribute(ItemEntity diamond, ServerPlayer player, int count) {
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    if (server == null) {
      return;
    }
    CommandSourceStack source = server.createCommandSourceStack();
    for (int i = 0; i < count; i++) {
      String command = ConfigManager.getCommand(player);
      if (command != null) {
        server.getCommands().performPrefixedCommand(source, command);
      }
    }


    PacketParticles message = new PacketParticles(diamond.getX(), diamond.getY(), diamond.getZ(), diamond.getItem().getCount());
    PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, message);

    PacketDistributor.sendToPlayer(player, new PacketBanner());

    player.level().playSound(null, player, ModSounds.CACKLE.get(), SoundSource.NEUTRAL, 1, 0.85f);

    ModAdvancements.TRIGGER.get().trigger(player, diamond.getItem());

    ServerLifecycleHooks.getCurrentServer().getPlayerList().broadcastSystemMessage(Component.translatable("darktribute.message", player.getName()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED)), true);
  }

  private static final ResourceLocation DIAMONDS = ResourceLocation.withDefaultNamespace("story/mine_diamond");

  @SubscribeEvent
  public static void onAdvancementGiven (AdvancementEvent.AdvancementEarnEvent event) {
    if (event.getAdvancement().id().equals(DIAMONDS)) {
      PacketDistributor.sendToAllPlayers(new PacketWhispers());
    }
  }
}
