package noobanidus.mods.darktribute.init;


import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import noobanidus.mods.darktribute.DarkTribute;

public class ModItems {
  private static final DeferredRegister.Items ITEM = DeferredRegister.createItems(DarkTribute.MODID);

  public static final DeferredHolder<Item, Item> ADVANCEMENT = ITEM.register("symbol", () -> new Item(new Item.Properties()));

  public static void register (IEventBus bus) {
    ITEM.register(bus);
  }
}
