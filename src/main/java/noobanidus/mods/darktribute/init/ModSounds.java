package noobanidus.mods.darktribute.init;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import noobanidus.mods.darktribute.DarkTribute;

public class ModSounds {
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, DarkTribute.MODID);

  public static DeferredHolder<SoundEvent, SoundEvent> CACKLE = SOUNDS.register("cackle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "darktribute.cackle")));

  public static DeferredHolder<SoundEvent, SoundEvent> WHISPERS = SOUNDS.register("whispers", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "darktribute.whispers")));

  public static void register (IEventBus bus) {
    SOUNDS.register(bus);
  }
}
