package noobanidus.mods.darktribute;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;

public class DarkTributeTags {
  public static class Items {
    public static final TagKey<Item> TRIBUTE_ITEMS = modTag("tribute");

    private static TagKey<Item> modTag(String name) {
      return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, name));
    }
  }

  public static class DamageTypes {
    public static final TagKey<DamageType> IS_LAVA = modTag("is_lava");

    private static TagKey<DamageType> modTag(String name) {
      return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(DarkTribute.MODID, name));
    }
  }
}
