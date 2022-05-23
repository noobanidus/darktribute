package noobanidus.mods.darktribute.init;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import noobanidus.libs.noobutil.advancement.GenericTrigger;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.advancement.TributePredicate;

public class ModAdvancements {
  public static final ResourceLocation TRIBUTE_ID = new ResourceLocation(DarkTribute.MODID, "tribute");
  public static GenericTrigger<Void> TRIBUTE_TRIGGER;

  public static void init () {
    TRIBUTE_TRIGGER = CriteriaTriggers.register(new GenericTrigger<>(TRIBUTE_ID, new TributePredicate()));
  }
}
