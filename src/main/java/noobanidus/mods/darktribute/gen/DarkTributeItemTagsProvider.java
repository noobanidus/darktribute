package noobanidus.mods.darktribute.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.DarkTributeTags;

import java.util.concurrent.CompletableFuture;

public class DarkTributeItemTagsProvider extends ItemTagsProvider {
  public DarkTributeItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
    super(p_275343_, p_275729_, p_275322_, DarkTribute.MODID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(DarkTributeTags.Items.TRIBUTE_ITEMS).add(Items.DIAMOND);
  }

  @Override
  public String getName() {
    return "Dark Tribute Item Tags";
  }
}
