package noobanidus.mods.darktribute.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ConfigManager {
  private static Random rand = new Random();
  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;

  public static ForgeConfigSpec.ConfigValue<List<? extends String>> COMMANDS;

  static {
    COMMANDS = COMMON_BUILDER.comment("Commands that can be executed when Dark Tribute is made.", "Commands should be in the syntax of: \"/command to execute\".", "All commands are executed as the player who performed the Dark Tribute.").defineList("commands", Collections.emptyList(), o -> true);
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }

  public static String getCommand() {
    List<? extends String> COMMAND_LIST = COMMANDS.get();

    if (COMMAND_LIST.isEmpty()) {
      return null;
    }

    return COMMAND_LIST.get(rand.nextInt(COMMAND_LIST.size()));
  }

}
