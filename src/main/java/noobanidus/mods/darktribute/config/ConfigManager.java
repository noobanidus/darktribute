package noobanidus.mods.darktribute.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import noobanidus.mods.darktribute.DarkTribute;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ConfigManager {
  private static Random rand = new Random();
  private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  public static ForgeConfigSpec CLIENT_CONFIG;

  public static ForgeConfigSpec.ConfigValue<List<? extends String>> COMMANDS;
  public static ForgeConfigSpec.BooleanValue PLAY_WHISPERS;

  static {
    COMMANDS = COMMON_BUILDER.comment("Commands that can be executed when Dark Tribute is made.", "Commands should be in the syntax of: \"command to execute\" (with no slash).", "All commands are executed as the player who performed the Dark Tribute, with server operator permissions..").defineList("commands", Collections.emptyList(), o -> true);
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  static {
    PLAY_WHISPERS = CLIENT_BUILDER.comment("Whether or not the 'ominous whispers' should be played upon first receiving diamonds.").define("play_ominous_whispers", true);
    CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }

  public static String getCommand(ServerPlayer player) {
    List<? extends String> COMMAND_LIST = COMMANDS.get();

    if (COMMAND_LIST.isEmpty()) {
      return null;
    }

    String initialCommand = COMMAND_LIST.get(rand.nextInt(COMMAND_LIST.size()));
    if (initialCommand.startsWith("/execute as")) {
      return initialCommand;
    } else if (initialCommand.startsWith("/")) {
      initialCommand = initialCommand.substring(1);
    }

    return "/execute as " + player.getScoreboardName() + " run " + initialCommand;
  }

  public static void configReloaded (ModConfigEvent event) {
    if (event.getConfig().getType() == ModConfig.Type.COMMON) {
      COMMON_CONFIG.setConfig(event.getConfig().getConfigData());
      DarkTribute.LOG.info("DarkTribute config reloaded!");
    } else if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
      CLIENT_CONFIG.setConfig(event.getConfig().getConfigData());
      DarkTribute.LOG.info("DarkTribute client config reloaded!");
    }
  }
}
