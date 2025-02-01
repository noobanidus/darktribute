package noobanidus.mods.darktribute.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.DarkTributeTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DarkTributeDamageTagsProvider extends TagsProvider<DamageType> {
  protected DarkTributeDamageTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, Registries.DAMAGE_TYPE, lookupProvider, DarkTribute.MODID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(DarkTributeTags.DamageTypes.IS_LAVA).add(DamageTypes.LAVA);
  }

  @Override
  public String getName() {
    return "Dark Tribute Damage Tags";
  }
}
