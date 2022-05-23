package noobanidus.mods.darktribute.particles;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import noobanidus.mods.darktribute.init.ModItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiamondParticle extends TextureSheetParticle {
  protected float particleScale;
  protected float alpha;
  protected float colourScale;

  public DiamondParticle(ClientLevel world, double x, double y, double z, double mx, double my, double mz, DiamondParticleData data) {
    this(world, x, y, z, mx, my, mz, data.size, data.red, data.green, data.blue, data.alpha, data.colorScale, data.gravity);
  }

  public DiamondParticle(ClientLevel world, double x, double y, double z, double mx, double my, double mz, float size, float red, float green, float blue, float alpha, float colourScale, float gravity) {
    super(world, x, y, z, mx, my, mz);
    rCol = red;
    gCol = green;
    bCol = blue;
    gravity = gravity;
    particleScale = size * 2;
    lifetime = 3 * 50;
    setSize(0.01F, 0.01F);
    this.xd = 0;
    this.zd = 0;
    this.yd = 0.05;
    xo = x;
    yo = y;
    zo = z;
    hasPhysics = false;
    this.alpha = alpha;
    this.colourScale = colourScale;
    this.setSprite(Minecraft.getInstance().getItemRenderer().getItemModelShaper().getParticleIcon(ModItems.advancement.get()));
  }

  @Nonnull
  @Override
  public ParticleRenderType getRenderType() {
    return TERRAIN_OPAQUE;
  }

  private static final ParticleRenderType TERRAIN_OPAQUE = new ParticleRenderType() {
    public void begin(BufferBuilder pBuilder, TextureManager pTextureManager) {
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_DST_ALPHA);
      RenderSystem.depthMask(true);
      RenderSystem.disableDepthTest();
      pTextureManager.bind(TextureAtlas.LOCATION_BLOCKS);
      pBuilder.begin(7, DefaultVertexFormats.PARTICLE);
    }

    public void end(Tesselator pTesselator) {
      pTesselator.end();
      RenderSystem.enableDepthTest();
    }

    public String toString() {
      return "TERRAIN_SHEET_OPAQUE";
    }
  };

  public static class Factory implements IParticleFactory<DiamondParticleData> {
    @Nullable
    @Override
    public Particle createParticle(DiamondParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      return new DiamondParticle(world, x, y, z, mx, my, mz, data);
    }
  }
}

