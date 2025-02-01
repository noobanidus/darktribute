package noobanidus.mods.darktribute;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import noobanidus.mods.darktribute.config.ConfigManager;
import noobanidus.mods.darktribute.init.ModAdvancements;
import noobanidus.mods.darktribute.init.ModItems;
import noobanidus.mods.darktribute.init.ModParticles;
import noobanidus.mods.darktribute.init.ModSounds;
import noobanidus.mods.darktribute.networking.PacketHandler;
import noobanidus.mods.darktribute.setup.CommonSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DarkTribute.MODID)
public class DarkTribute {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "darktribute";
  public static final String NETWORK_VERSION = "darktribute-1.21.1";

  public DarkTribute(ModContainer container, IEventBus modBus) {
    container.registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    container.registerConfig(ModConfig.Type.CLIENT, ConfigManager.CLIENT_CONFIG);

    modBus.addListener(CommonSetup::setup);
    ModParticles.register(modBus);
    ModSounds.register(modBus);
    ModItems.register(modBus);
    ModAdvancements.register(modBus);

    new PacketHandler(modBus);
  }
}
