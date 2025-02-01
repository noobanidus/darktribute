package noobanidus.mods.darktribute.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import noobanidus.mods.darktribute.init.ModParticles;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record TributeParticleData(float size, List<Float> color, float colorScale,
                                  float gravity) implements ParticleOptions {
  public static final StreamCodec<ByteBuf, TributeParticleData> STREAM_CODEC = StreamCodec.composite(
      ByteBufCodecs.FLOAT, TributeParticleData::size,
      ByteBufCodecs.FLOAT.apply(ByteBufCodecs.list(4)), TributeParticleData::color,
      ByteBufCodecs.FLOAT, TributeParticleData::colorScale,
      ByteBufCodecs.FLOAT, TributeParticleData::gravity,
      TributeParticleData::new
  );
  public static final MapCodec<TributeParticleData> CODEC = RecordCodecBuilder.mapCodec(val -> val.group(
      Codec.FLOAT.fieldOf("size").forGetter(data -> data.size),
      Codec.FLOAT.listOf().fieldOf("color").forGetter(data -> data.color),
      Codec.FLOAT.fieldOf("colorScale").forGetter(data -> data.colorScale),
      Codec.FLOAT.fieldOf("gravity").forGetter(data -> data.gravity)
  ).apply(val, TributeParticleData::new));

  public float red () {
    return color.get(0);
  }

  public float green () {
    return color.get(1);
  }

  public float blue () {
    return color.get(2);
  }

  public float alpha () {
    return color.get(3);
  }

  @Override
  public ParticleType<?> getType() {
    return ModParticles.TRIBUTE.get();
  }

  public static class TributeParticleType extends ParticleType<TributeParticleData> {
    public TributeParticleType(boolean overrideLimitter) {
      super(overrideLimitter);
    }

    @Override
    public MapCodec<TributeParticleData> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, TributeParticleData> streamCodec() {
      return STREAM_CODEC;
    }
  }
}
