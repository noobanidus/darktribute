package noobanidus.mods.darktribute.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import noobanidus.mods.darktribute.DarkTribute;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL14C;

@OnlyIn(Dist.CLIENT)
public class BannerManager {
  private static ResourceLocation DARK_TRIBUTE = new ResourceLocation(DarkTribute.MODID, "textures/gui/logo.png");
  private static final int TOTAL_TICKS = 80;
  private static int ticksActive = 0;

  public static void displayBanner(RenderGameOverlayEvent.Post event) {
    Minecraft mc = Minecraft.getInstance();
    mc.getTextureManager().bind(DARK_TRIBUTE);

    // 10 ticks to fade in
    // 10 ticks to fade out

    int a = 255;

    if (ticksActive <= 10) {
      if (ticksActive == 0) {
        a = 10;
      } else {
        a = Math.min(255, 25 * ticksActive);
      }
    } else if (ticksActive >= TOTAL_TICKS - 20) {
      a = 255 - (a / Math.max(1, (TOTAL_TICKS - ticksActive)));
    }

    float textureWidth = (float) (926 / mc.window.getGuiScale());
    float textureHeight = (float) (501 / mc.window.getGuiScale());
    int width = (int) textureWidth;
    int height = (int) textureHeight;

    int x = mc.window.getGuiScaledWidth() / 2 - width / 2;
    int y = mc.window.getGuiScaledHeight() / 2 - height / 2;

    int u = 0, v = 0;
    RenderSystem.texParameter(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL14C.GL_CLAMP_TO_EDGE);
    RenderSystem.texParameter(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL14C.GL_CLAMP_TO_EDGE);
    float f = 1.0F / textureWidth;
    float f1 = 1.0F / textureHeight;
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuilder();
    //noinspection deprecation
    bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
    bufferbuilder.vertex((double) x, (double) (y + height), 0.0D).uv((u * f), ((v + (float) height) * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex((double) (x + width), (double) (y + height), 0.0D).uv(((u + (float) width) * f), ((v + (float) height) * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex((double) (x + width), (double) y, 0.0D).uv(((u + (float) width) * f), (v * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex((double) x, (double) y, 0.0D).uv((u * f), (v * f1)).color(255, 255, 255, a).endVertex();
    tessellator.end();
  }

  public static void startTribute() {
    ticksActive = TOTAL_TICKS;
  }

  public static void clientTick(TickEvent.ClientTickEvent event) {
    if (event.phase == TickEvent.Phase.START && event.side == LogicalSide.CLIENT) {
      if (ticksActive > 0) {
        ticksActive--;
      }
    }
  }

  public static void hudRender(RenderGameOverlayEvent.Post event) {
    if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
      if (ticksActive > 0) {
        displayBanner(event);
      }
    }
  }
}
