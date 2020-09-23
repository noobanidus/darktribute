package noobanidus.mods.darktribute.particles;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.darktribute.DarkTribute;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiamondParticle extends SpriteTexturedParticle {
  public static final ResourceLocation particles = new ResourceLocation(DarkTribute.MODID, "textures/item/darkened_diamond.png");

  protected float particleScale;
  protected float alpha;
  protected float colourScale;

  public DiamondParticle(ClientWorld world, double x, double y, double z, double mx, double my, double mz, DiamondParticleData data) {
    this(world, x, y, z, mx, my, mz, data.size, data.red, data.green, data.blue, data.alpha, data.colorScale, data.gravity);
  }

  public DiamondParticle(ClientWorld world, double x, double y, double z, double mx, double my, double mz, float size, float red, float green, float blue, float alpha, float colourScale, float gravity) {
    super(world, x, y, z, mx, my, mz);
    particleRed = red;
    particleGreen = green;
    particleBlue = blue;
    particleGravity = gravity;
    particleScale = size * 2;
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

  @Nonnull
  @Override
  public IParticleRenderType getRenderType() {
    return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
  }

/*  private static void beginRenderCommon(BufferBuilder buffer, TextureManager textureManager) {
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
  }*/

/*  private static final IParticleRenderType NORMAL_RENDER = new IParticleRenderType() {
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
  };*/


  public static class Factory implements IParticleFactory<DiamondParticleData> {
    @Nullable
    @Override
    public Particle makeParticle(DiamondParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      return new DiamondParticle(world, x, y, z, mx, my, mz, data);
    }
  }
}

