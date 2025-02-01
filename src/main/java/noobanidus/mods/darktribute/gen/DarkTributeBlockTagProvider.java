package noobanidus.mods.darktribute.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import noobanidus.mods.darktribute.DarkTribute;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DarkTributeBlockTagProvider extends BlockTagsProvider {
  public DarkTributeBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, DarkTribute.MODID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
  }

  @Override
  public String getName() {
    return "Dark Tribute Block Tags";
  }
}
