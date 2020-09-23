package noobanidus.mods.darktribute.particles;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.init.ModItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DiamondParticle extends SpriteTexturedParticle {
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
    this.setSprite(Minecraft.getInstance().getItemRenderer().getItemModelMesher().getParticleIcon(ModItems.advancement.get()));
  }

  @Nonnull
  @Override
  public IParticleRenderType getRenderType() {
    return TERRAIN_OPAQUE;
  }

  private static final IParticleRenderType TERRAIN_OPAQUE = new IParticleRenderType() {
    public void beginRender(BufferBuilder p_217600_1_, TextureManager p_217600_2_) {
      RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_DST_ALPHA);
      RenderSystem.depthMask(true);
      RenderSystem.disableDepthTest();
      p_217600_2_.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
      p_217600_1_.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    }

    public void finishRender(Tessellator p_217599_1_) {
      p_217599_1_.draw();
      RenderSystem.enableDepthTest();
    }

    public String toString() {
      return "TERRAIN_SHEET_OPAQUE";
    }
  };

  public static class Factory implements IParticleFactory<DiamondParticleData> {
    @Nullable
    @Override
    public Particle makeParticle(DiamondParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      return new DiamondParticle(world, x, y, z, mx, my, mz, data);
    }
  }
}

