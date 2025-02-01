package noobanidus.mods.darktribute.particles;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;
import noobanidus.mods.darktribute.init.ModItems;
import org.jetbrains.annotations.Nullable;

public class TributeParticle extends TextureSheetParticle {
  protected float particleScale;
  protected float alpha;
  protected float colourScale;

  public TributeParticle(ClientLevel world, double x, double y, double z, double mx, double my, double mz, TributeParticleData data) {
    this(world, x, y, z, mx, my, mz, data.size(), data.red(), data.green(), data.blue(), data.alpha(), data.colorScale(), data.gravity());
  }

  @Override
  protected int getLightColor(float partialTick) {
    return 15728880;
  }

  public TributeParticle(ClientLevel world, double x, double y, double z, double mx, double my, double mz, float size, float red, float green, float blue, float alpha, float colourScale, float gravity) {
    super(world, x, y, z, mx, my, mz);
    this.rCol = red;
    this.gCol = green;
    this.bCol = blue;
    this.gravity = gravity;
    this.particleScale = size * 2;
    this.lifetime = 3 * 50;
    this.setSize(0.01F, 0.01F);
    this.xd = 0;
    this.zd = 0;
    this.yd = 0.05;
    this.xo = x;
    this.yo = y;
    this.zo = z;
    this.hasPhysics = false; // ???
    this.alpha = alpha;
    this.colourScale = colourScale;
    ItemStack stack = new ItemStack(ModItems.ADVANCEMENT.get());
    var model = Minecraft.getInstance().getItemRenderer().getModel(stack, level, null, 0);
    this.setSprite(model.getOverrides().resolve(model, stack, level, null, 0).getParticleIcon(ModelData.EMPTY));
  }

  @Override
  public ParticleRenderType getRenderType() {
    return TERRAIN_OPAQUE;
  }

  private static final ParticleRenderType TERRAIN_OPAQUE = new ParticleRenderType() {
    @Override
    public @Nullable BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
      RenderSystem.depthMask(false);
      RenderSystem.disableDepthTest();
      RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
      return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
    }

    @Override
    public boolean isTranslucent() {
      return true;
    }
  };

  public static class Provider implements ParticleProvider<TributeParticleData> {
    @Override
    public @Nullable Particle createParticle(TributeParticleData type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
      return new TributeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, type);
    }
  }
}
