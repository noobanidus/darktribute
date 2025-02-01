package noobanidus.mods.darktribute.gen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import noobanidus.mods.darktribute.DarkTributeTags;
import noobanidus.mods.darktribute.advancement.TributeTrigger;
import noobanidus.mods.darktribute.init.ModAdvancements;
import noobanidus.mods.darktribute.init.ModItems;

import java.util.function.Consumer;

public class DarkTributeAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
  @Override
  public void generate(HolderLookup.Provider arg, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
    AdvancementHolder tributeRoot = Advancement.Builder.advancement()
        .display(Items.DIAMOND, Component.translatable("darktribute.advancements.root.title"), Component.translatable("darktribute.advancements.root.description"), ResourceLocation.parse("darktribute:textures/gui/dark_diamond.png"), AdvancementType.TASK, false,  false, false)
        .addCriterion("performed_tribute", PlayerTrigger.TriggerInstance.tick())
        .save(consumer, ModAdvancements.ROOT, existingFileHelper);

    Advancement.Builder.advancement()
        .display(ModItems.ADVANCEMENT.get(), Component.translatable("darktribute.advancements.tribute.title"), Component.translatable("darktribute.advancements.tribute.description"), null, AdvancementType.CHALLENGE, true, true, true)
        .addCriterion("performed_tribute", TributeTrigger.TriggerInstance.usedItem(DarkTributeTags.Items.TRIBUTE_ITEMS))
        .parent(tributeRoot).save(consumer, ModAdvancements.TRIBUTE, existingFileHelper);
  }
}
