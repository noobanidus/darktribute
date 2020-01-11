package noobanidus.mods.darktribute.setup;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import noobanidus.mods.darktribute.networking.Networking;

public class CommonSetup {
  public static void setup (FMLCommonSetupEvent event) {
    Networking.registerMessages();
  }
}
