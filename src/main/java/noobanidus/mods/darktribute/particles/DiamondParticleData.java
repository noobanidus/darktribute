package noobanidus.mods.darktribute.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import noobanidus.mods.darktribute.init.ModParticles;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class DiamondParticleData implements IParticleData {
  public static final IDeserializer<DiamondParticleData> DESERIALIZER = new IDeserializer<DiamondParticleData>() {
    @Override
    public DiamondParticleData deserialize(ParticleType<DiamondParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
        reader.expect(' ');
        float size = reader.readFloat();
        reader.expect(' ');
        float r = reader.readFloat();
        reader.expect(' ');
        float g = reader.readFloat();
        reader.expect(' ');
        float b = reader.readFloat();
        reader.expect(' ');
        float a = reader.readFloat();
        reader.expect(' ');
        float c = reader.readFloat();
        reader.expect(' ');
        int m = reader.readInt();
        reader.expect(' ');
        float gr = reader.readFloat();
        reader.expect(' ');

        return new DiamondParticleData(size, r, g, b, a, c, gr);
    }

    @Override
    public DiamondParticleData read(ParticleType<DiamondParticleData> particleTypeIn, PacketBuffer buf) {
        return new DiamondParticleData(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
    }
  };
  public static final Codec<DiamondParticleData> CODEC = RecordCodecBuilder.create(val -> val.group(
      Codec.FLOAT.fieldOf("size").forGetter(data -> data.size),
      Codec.FLOAT.fieldOf("red").forGetter(data -> data.red),
      Codec.FLOAT.fieldOf("green").forGetter(data -> data.green),
      Codec.FLOAT.fieldOf("blue").forGetter(data -> data.blue),
      Codec.FLOAT.fieldOf("alpha").forGetter(data -> data.alpha),
      Codec.FLOAT.fieldOf("colorScale").forGetter(data -> data.colorScale),
      Codec.FLOAT.fieldOf("gravity").forGetter(data -> data.gravity)
  ).apply(val, DiamondParticleData::new));

  public final float size, red, green, blue, alpha, colorScale, gravity;

  public DiamondParticleData(float size, float red, float green, float blue, float alpha, float colorScale, float gravity) {
    this.size = size;
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.colorScale = colorScale;
    this.gravity = gravity;
  }

  @Override
  public ParticleType<?> getType() {
    return ModParticles.DIAMOND.get();
  }

  @Override
  public void write(PacketBuffer buffer) {
    buffer.writeFloat(size);
    buffer.writeFloat(red);
    buffer.writeFloat(green);
    buffer.writeFloat(blue);
    buffer.writeFloat(alpha);
    buffer.writeFloat(colorScale);
    buffer.writeFloat(gravity);
  }

  @Override
  public String getParameters() {
    return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f %.2f %.2f", getType().getRegistryName(), size, red, green, blue, alpha, colorScale, gravity);
  }
}
