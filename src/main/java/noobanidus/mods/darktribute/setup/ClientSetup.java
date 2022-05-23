package noobanidus.mods.darktribute.setup;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.mods.darktribute.particles.DiamondParticle;
import noobanidus.mods.darktribute.init.ModParticles;
import noobanidus.mods.darktribute.particles.DiamondParticleData;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {
  public static void setup (FMLClientSetupEvent event) {

  }

  public static void registerParticles (ParticleFactoryRegisterEvent event) {
    Minecraft.getInstance().particleEngine.register(ModParticles.DIAMOND.get(), new DiamondParticle.Factory());
  }
}
