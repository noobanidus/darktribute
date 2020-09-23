package noobanidus.mods.darktribute.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.particles.DiamondParticle;
import noobanidus.mods.darktribute.particles.DiamondParticleType;

public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> particleRegistry = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, DarkTribute.MODID);

  public static final RegistryObject<DiamondParticleType> DIAMOND = particleRegistry.register("diamond", DiamondParticleType::new);
}
