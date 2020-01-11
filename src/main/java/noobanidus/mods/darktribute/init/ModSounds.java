package noobanidus.mods.darktribute.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.darktribute.DarkTribute;

public class ModSounds {
  public static final DeferredRegister<SoundEvent> soundRegistry = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, DarkTribute.MODID);

  public static RegistryObject<SoundEvent> CACKLE = soundRegistry.register("cackle", () -> new SoundEvent(new ResourceLocation(DarkTribute.MODID, "darktribute.cackle")));
}
