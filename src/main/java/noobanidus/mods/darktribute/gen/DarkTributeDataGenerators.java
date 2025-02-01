package noobanidus.mods.darktribute.gen;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import noobanidus.mods.darktribute.DarkTribute;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DarkTribute.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DarkTributeDataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput output = event.getGenerator().getPackOutput();
    CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
    ExistingFileHelper helper = event.getExistingFileHelper();

    DarkTributeBlockTagProvider blocks;
    generator.addProvider(event.includeServer(), blocks = new DarkTributeBlockTagProvider(output, provider, helper));
    generator.addProvider(event.includeServer(), new DarkTributeItemTagsProvider(output, provider, blocks.contentsGetter(), helper));
    generator.addProvider(event.includeServer(), new DarkTributeDamageTagsProvider(output, provider, helper));
    generator.addProvider(event.includeClient(), new DarkTributeLangProvider(output));
    generator.addProvider(event.includeServer(), new AdvancementProvider(output, provider, helper, List.of(new DarkTributeAdvancementGenerator())));
    generator.addProvider(event.includeClient(), new DarkTributeItemModelProvider(output, helper));
    generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(Component.literal("Dark Tribute resources"), DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA), Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
  }
}
