package noobanidus.mods.darktribute;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.mods.darktribute.client.hud.BannerManager;
import noobanidus.mods.darktribute.config.ConfigManager;
import noobanidus.mods.darktribute.events.TributeHandler;
import noobanidus.mods.darktribute.init.ModAdvancements;
import noobanidus.mods.darktribute.init.ModItems;
import noobanidus.mods.darktribute.init.ModParticles;
import noobanidus.mods.darktribute.init.ModSounds;
import noobanidus.mods.darktribute.setup.ClientSetup;
import noobanidus.mods.darktribute.setup.CommonSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("darktribute")
public class DarkTribute {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "darktribute";

  public DarkTribute() {
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(CommonSetup::setup);
    ModParticles.particleRegistry.register(modBus);
    ModSounds.soundRegistry.register(modBus);
    ModItems.itemRegistry.register(modBus);
    MinecraftForge.EVENT_BUS.addListener(TributeHandler::onItemToss);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
      modBus.addListener(ClientSetup::setup);
      modBus.addListener(ClientSetup::registerParticles);
      MinecraftForge.EVENT_BUS.addListener(BannerManager::clientTick);
      MinecraftForge.EVENT_BUS.addListener(BannerManager::hudRender);
    });

    ModAdvancements.init();
  }
}
