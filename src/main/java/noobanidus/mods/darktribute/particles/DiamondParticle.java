package noobanidus.mods.darktribute.particles;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.init.ModParticles;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class DiamondParticle extends TexturedParticle {
  public static final ResourceLocation particles = new ResourceLocation(DarkTribute.MODID, "textures/item/darkened_diamond.png");

  protected float particleScale = 1f;
  protected float alpha = 1f;
  protected float colourScale = 1f;
  public final int particle = 16;

  public DiamondParticle(World world, double x, double y, double z, double mx, double my, double mz, float size, float red, float green, float blue, float alpha, float colourScale, int m, float gravity) {
    super(world, x, y, z, mx, my, mz);
    particleRed = red;
    particleGreen = green;
    particleBlue = blue;
    particleGravity = gravity;
    particleScale *= 2;
    maxAge = 3 * 50;
    setSize(0.01F, 0.01F);
    this.motionX = 0;
    this.motionZ = 0;
    this.motionY = 0.05;
    prevPosX = posX;
    prevPosY = posY;
    prevPosZ = posZ;
    canCollide = false;
    this.alpha = alpha;
    this.colourScale = colourScale;
  }

  @Override
  protected float getMinU() {
    return 0;
  }

  @Override
  protected float getMaxU() {
    return 1;
  }

  @Override
  protected float getMinV() {
    return 0;
  }

  @Override
  protected float getMaxV() {
    return 1;
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Nonnull
  @Override
  public IParticleRenderType getRenderType() {
    return NORMAL_RENDER;
  }

  private static void beginRenderCommon(BufferBuilder buffer, TextureManager textureManager) {
    RenderSystem.pushLightingAttributes();
    RenderSystem.depthMask(false);
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_DST_ALPHA);
    RenderSystem.disableLighting();
    RenderSystem.disableDepthTest();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1F);
    textureManager.bindTexture(particles);
    buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
  }

  private static void endRenderCommon() {
    RenderSystem.disableBlend();
    RenderSystem.enableDepthTest();
    RenderSystem.depthMask(true);
    RenderSystem.popAttributes();
  }

  private static final IParticleRenderType NORMAL_RENDER = new IParticleRenderType() {
    @Override
    public void beginRender(BufferBuilder bufferBuilder, TextureManager textureManager) {
      beginRenderCommon(bufferBuilder, textureManager);
    }

    @Override
    public void finishRender(Tessellator tessellator) {
      tessellator.draw();
      endRenderCommon();
    }

    @Override
    public String toString() {
      return "darktribute:diamond";
    }
  };

  public static class Data implements IParticleData {
    public final float size;
    public final float r, g, b, a, c;
    public final int m;
    public final float gravity;

    public Data(float size, float r, float g, float b, float a, float c, int m, float gravity) {
      this.size = size;
      this.r = r;
      this.g = g;
      this.b = b;
      this.m = m;
      this.a = a;
      this.c = c;
      this.gravity = gravity;
    }

    @Nonnull
    @Override
    public ParticleType<Data> getType() {
      return ModParticles.DIAMOND.get();
    }

    @Override
    public void write(PacketBuffer buf) {
      buf.writeFloat(size);
      buf.writeFloat(r);
      buf.writeFloat(g);
      buf.writeFloat(b);
      buf.writeFloat(a);
      buf.writeFloat(c);
      buf.writeInt(m);
      buf.writeFloat(gravity);
    }

    @Nonnull
    @Override
    public String getParameters() {
      return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f %.2f %d %.2f", this.getType().getRegistryName(), this.size, this.r, this.g, this.b, this.a, this.c, this.m, this.gravity);
    }

    public static final IDeserializer<Data> DESERIALIZER = new IDeserializer<Data>() {
      @Nonnull
      @Override
      public Data deserialize(@Nonnull ParticleType<Data> type, @Nonnull StringReader reader) throws CommandSyntaxException {
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

        return new Data(size, r, g, b, a, c, m, gr);
      }

      @Override
      public Data read(@Nonnull ParticleType<Data> type, PacketBuffer buf) {
        return new Data(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readInt(), buf.readFloat());
      }
    };
  }

  public static class Type extends ParticleType<Data> {
    public Type() {
      super(false, Data.DESERIALIZER);
    }

    public static class Factory implements IParticleFactory<Data> {
      @Nullable
      @Override
      public Particle makeParticle(Data data, World world, double x, double y, double z, double mx, double my, double mz) {
        return new DiamondParticle(world, x, y, z, mx, my, mz, data.size, data.r, data.g, data.b, data.a, data.c, data.m, data.gravity);
      }
    }
  }
}

