package noobanidus.mods.darktribute.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.particles.DiamondParticleType;

public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> particleRegistry = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DarkTribute.MODID);

  public static final RegistryObject<DiamondParticleType> DIAMOND = particleRegistry.register("diamond", DiamondParticleType::new);
}
