package noobanidus.mods.darktribute.setup;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.init.ModParticles;
import noobanidus.mods.darktribute.particles.TributeParticle;

@EventBusSubscriber(modid = DarkTribute.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  @SubscribeEvent
  public static void setup(FMLClientSetupEvent event) {

  }

  @SubscribeEvent
  public static void registerParticle(RegisterParticleProvidersEvent event) {
    event.registerSpecial(ModParticles.TRIBUTE.get(), new TributeParticle.Provider());
  }
}
