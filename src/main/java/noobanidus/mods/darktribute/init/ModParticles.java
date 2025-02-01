package noobanidus.mods.darktribute.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.particles.TributeParticleData;

public class ModParticles {
  private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, DarkTribute.MODID);

  public static final DeferredHolder<ParticleType<?>, TributeParticleData.TributeParticleType> TRIBUTE = PARTICLES.register("tribute", () -> new TributeParticleData.TributeParticleType(true));

  public static void register(IEventBus bus) {
    PARTICLES.register(bus);
  }
}
