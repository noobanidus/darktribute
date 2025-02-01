package noobanidus.mods.darktribute.init;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.advancement.TributeTrigger;

public class ModAdvancements {
  public static final ResourceLocation ROOT = ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "root");
  public static final ResourceLocation TRIBUTE = ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, "tribute");
  private static final DeferredRegister<CriterionTrigger<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, DarkTribute.MODID);

  public static final DeferredHolder<CriterionTrigger<?>, TributeTrigger> TRIGGER = REGISTER.register("tribute", TributeTrigger::new);

  public static void register(IEventBus bus) {
    REGISTER.register(bus);
  }
}
