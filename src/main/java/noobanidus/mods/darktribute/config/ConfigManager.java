package noobanidus.mods.darktribute.config;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ConfigManager {
  private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

  private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

  public static ModConfigSpec COMMON_CONFIG;
  public static ModConfigSpec CLIENT_CONFIG;

  public static ModConfigSpec.ConfigValue<List<? extends String>> COMMANDS;
  public static ModConfigSpec.BooleanValue PLAY_WHISPERS;

  static {
    COMMANDS = COMMON_BUILDER.comment("Commands that can be executed when Dark Tribute is made.", "Commands should be in the syntax of: \"command to execute\" (with no slash).", "All commands are executed as the player who performed the Dark Tribute, with server operator permissions..")
        .defineList("commands", Collections.emptyList(), o -> true);
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  static {
    PLAY_WHISPERS = CLIENT_BUILDER.comment("Whether or not the 'ominous whispers' should be played upon first receiving diamonds.")
        .define("play_ominous_whispers", true);
    CLIENT_CONFIG = CLIENT_BUILDER.build();
  }

  public static String getCommand(ServerPlayer player) {
    List<? extends String> COMMAND_LIST = COMMANDS.get();

    if (COMMAND_LIST.isEmpty()) {
      return null;
    }

    String initialCommand = COMMAND_LIST.get(player.getRandom().nextInt(COMMAND_LIST.size()));
    if (initialCommand.startsWith("/execute as")) {
      return initialCommand;
    } else if (initialCommand.startsWith("/")) {
      initialCommand = initialCommand.substring(1);
    }

    return "/execute as " + player.getScoreboardName() + " run " + initialCommand;
  }
}
