package noobanidus.mods.darktribute.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import noobanidus.mods.darktribute.DarkTribute;
import org.joml.Matrix4f;

@EventBusSubscriber(modid = DarkTribute.MODID, value= Dist.CLIENT, bus= EventBusSubscriber.Bus.GAME)
public class BannerManager {
  private static final ResourceLocation DARK_TRIBUTE = ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "textures/gui/logo.png");
  private static final int TOTAL_TICKS = 80;
  private static int ticksActive = 0;

  public static void displayBanner(RenderGuiEvent.Post event) {
    GuiGraphics graphics = event.getGuiGraphics();
    Minecraft mc = Minecraft.getInstance();

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

    float textureWidth = (float) (926 / mc.getWindow().getGuiScale());
    float textureHeight = (float) (501 / mc.getWindow().getGuiScale());
    int width = (int) textureWidth;
    int height = (int) textureHeight;

    int x = mc.getWindow().getGuiScaledWidth() / 2 - width / 2;
    int y = mc.getWindow().getGuiScaledHeight() / 2 - height / 2;

    int u = 0, v = 0;

    RenderSystem.setShaderTexture(0, DARK_TRIBUTE);
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    Matrix4f matrix = graphics.pose().last().pose();
    float f = 1.0F / textureWidth;
    float f1 = 1.0F / textureHeight;
    BufferBuilder bufferBuilder = Tesselator.getInstance()
        .begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
    bufferBuilder.addVertex(matrix, x, y + height, 0.0F).setUv(u * f, v + ((float) height) * f1)
        .setColor(255, 255, 255, a);
    bufferBuilder.addVertex(matrix, x + width, y + height, 0.0F)
        .setUv(u + ((float) width) * f, v + ((float) height) * f1).setColor(255, 255, 255, a);
    bufferBuilder.addVertex(matrix, x + width, y, 0.0F).setUv(u + ((float) width) * f, v * f1)
        .setColor(255, 255, 255, a);
    bufferBuilder.addVertex(matrix, x, y, 0.0F).setUv(u * f, v * f1).setColor(255, 255, 255, a);
    BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
  }

  public static void startTribute() {
    ticksActive = TOTAL_TICKS;
  }

  @SubscribeEvent
  public static void clientTick(ClientTickEvent.Pre event) {
    if (ticksActive > 0) {
      ticksActive--;
    }
  }

  @SubscribeEvent
  public static void hudRender(RenderGuiEvent.Post event) {
    if (ticksActive > 0) {
      displayBanner(event);
    }
  }
}
