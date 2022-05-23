package noobanidus.mods.darktribute.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
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
    float f = 1.0F / textureWidth;
    float f1 = 1.0F / textureHeight;
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableBlend();
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderTexture(0, DARK_TRIBUTE);
    BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
    bufferbuilder.vertex(x, y + height, 0.0D).uv((u * f), ((v + (float) height) * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex(x + width, y + height, 0.0D).uv(((u + (float) width) * f), ((v + (float) height) * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex(x + width, y, 0.0D).uv(((u + (float) width) * f), (v * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.vertex(x, y, 0.0D).uv((u * f), (v * f1)).color(255, 255, 255, a).endVertex();
    bufferbuilder.end();
    BufferUploader.end(bufferbuilder);
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
