package noobanidus.mods.darktribute.gen;

import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import noobanidus.mods.darktribute.DarkTribute;
import noobanidus.mods.darktribute.init.ModItems;

public class DarkTributeItemModelProvider extends ItemModelProvider {
  public DarkTributeItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, DarkTribute.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    // BLOCKS
    getBuilder(ModItems.ADVANCEMENT.getKey().location().toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", "item/darkened_diamond");
  }

  @Override
  public String getName() {
    return "Dark Tribute Item Model Generator";
  }
}
