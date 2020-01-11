package noobanidus.mods.darktribute.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.particles.DiamondParticle;

public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> particleRegistry = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, DarkTribute.MODID);

  public static final RegistryObject<DiamondParticle.Type> DIAMOND = particleRegistry.register("diamond", DiamondParticle.Type::new);
}
