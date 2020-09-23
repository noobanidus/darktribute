package noobanidus.mods.darktribute.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.darktribute.DarkTribute;

public class ModItems {
  public static final DeferredRegister<Item> itemRegistry = DeferredRegister.create(ForgeRegistries.ITEMS, DarkTribute.MODID);

  public static final RegistryObject<Item> advancement = itemRegistry.register("symbol", () -> new Item(new Item.Properties()));
}
