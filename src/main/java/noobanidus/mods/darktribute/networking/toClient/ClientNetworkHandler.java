package noobanidus.mods.darktribute.networking.toClient;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import noobanidus.mods.darktribute.client.hud.BannerManager;
import noobanidus.mods.darktribute.config.ConfigManager;
import noobanidus.mods.darktribute.init.ModSounds;
import noobanidus.mods.darktribute.particles.TributeParticleData;

import java.util.Arrays;

public class ClientNetworkHandler {
  public static void startBanner() {
    BannerManager.startTribute();
  }

  public static void displayParticles(double posX, double posY, double posZ, int count) {
    Minecraft minecraft = Minecraft.getInstance();
    int totalParticles = 1;
    if (count > 8) {
      totalParticles += 1;
    }
    if (count > 16) {
      totalParticles += 2;
    }
    if (count > 32){
      totalParticles += 3;
    }
    if (count > 64) {
      totalParticles += 4;
    }

    //noinspection ConstantValue
    if (minecraft != null && minecraft.level != null) {
      RandomSource random = minecraft.level.getRandom();
      for (int i = 0; i < totalParticles; i++) {
        minecraft.level.addParticle(new TributeParticleData(10f, Arrays.asList(1f, 1f, 1f, 1f), 1f, 0f), posX + random.nextFloat() - 0.5f, posY + random.nextFloat() - 0.5f, posZ + random.nextFloat() - 0.5f, 0, 0, 0);
      }
    }
  }

  public static void playSound() {
    Minecraft minecraft = Minecraft.getInstance();
    //noinspection ConstantValue
    if (minecraft != null && minecraft.player != null && minecraft.level != null && ConfigManager.PLAY_WHISPERS.get()) {
      minecraft.level.playLocalSound(minecraft.player.blockPosition(), ModSounds.WHISPERS.get(), SoundSource.NEUTRAL, 1f, 1f, false);
    }
  }
}
