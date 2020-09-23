package noobanidus.mods.darktribute.particles;

import com.mojang.serialization.Codec;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class DiamondParticleType extends ParticleType<DiamondParticleData> {
  public DiamondParticleType() {
    super(false, DiamondParticleData.DESERIALIZER);
  }

  @Override
  public Codec<DiamondParticleData> func_230522_e_() {
    return DiamondParticleData.CODEC;
  }
}
