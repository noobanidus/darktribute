package noobanidus.mods.darktribute.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.mods.darktribute.DarkTribute;

public class ModSounds {
  public static final DeferredRegister<SoundEvent> soundRegistry = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DarkTribute.MODID);

  public static RegistryObject<SoundEvent> CACKLE = soundRegistry.register("cackle", () -> new SoundEvent(new ResourceLocation(DarkTribute.MODID, "darktribute.cackle")));
  public static RegistryObject<SoundEvent> WHISPERS = soundRegistry.register("whispers", () -> new SoundEvent(new ResourceLocation(DarkTribute.MODID, "darktribute.whispers")));
}
