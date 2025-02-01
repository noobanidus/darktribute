package noobanidus.mods.darktribute.gen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.init.ModItems;
import noobanidus.mods.darktribute.init.ModSounds;

public class DarkTributeLangProvider extends LanguageProvider {
  public DarkTributeLangProvider(PackOutput output) {
    super(output, DarkTribute.MODID, "en_us");
  }

  @Override
  protected void addTranslations() {
    // Tag translations
    add(ModItems.ADVANCEMENT.get(), "Dark Symbol");

    add("darktribute.message", "%s has made a dark tribute!");
    add("darktribute.advancements.tribute.title", "Dark Tribute");
    add("darktribute.advancements.tribute.description", "Gave dark tribute to the eternal fire!");
    add("darktribute.advancements.root.title", "Ominous whispers...");
    add("darktribute.advancements.root.description", "Something bright whispers its hunger for something precious...");
    add("darktribute.sounds.cackle", "Dark cackling");
    add("darktribute.sounds.whispers", "Ominous whispers");
  }
}
