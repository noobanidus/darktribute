package noobanidus.mods.darktribute.particles;

import com.mojang.serialization.Codec;
import net.minecraft.particles.IParticleData;
import net.minecraft.core.particles.ParticleType;

public class DiamondParticleType extends ParticleType<DiamondParticleData> {
  public DiamondParticleType() {
    super(false, DiamondParticleData.DESERIALIZER);
  }

  @Override
  public Codec<DiamondParticleData> codec() {
    return DiamondParticleData.CODEC;
  }
}
