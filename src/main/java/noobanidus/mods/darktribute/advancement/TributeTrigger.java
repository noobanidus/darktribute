package noobanidus.mods.darktribute.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.behavior.declarative.Trigger;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import noobanidus.mods.darktribute.init.ModAdvancements;

import java.util.Optional;

public class TributeTrigger extends SimpleCriterionTrigger<TributeTrigger.TriggerInstance> {
  public void trigger(ServerPlayer player, ItemStack item) {
    this.trigger(player, (p_23687_) -> p_23687_.matches(item));
  }

  @Override
  public Codec<TriggerInstance> codec() {
    return TriggerInstance.CODEC;
  }

  public record TriggerInstance(Optional<ContextAwarePredicate> player,
                                Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
    public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((p_337348_) -> p_337348_.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
        .forGetter(TriggerInstance::player), ItemPredicate.CODEC.optionalFieldOf("item")
        .forGetter(TriggerInstance::item)).apply(p_337348_, TriggerInstance::new));

    public static Criterion<TriggerInstance> usedItem() {
      return ModAdvancements.TRIGGER.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.empty()));
    }

    public static Criterion<TriggerInstance> usedItem(TagKey<Item> tag) {
      return usedItem(ItemPredicate.Builder.item().of(tag));
    }

    public static Criterion<TriggerInstance> usedItem(ItemLike item) {
      return usedItem(ItemPredicate.Builder.item().of(new ItemLike[]{item.asItem()}));
    }

    public static Criterion<TriggerInstance> usedItem(ItemPredicate.Builder item) {
      return ModAdvancements.TRIGGER.get().createCriterion(new TriggerInstance(Optional.empty(), Optional.of(item.build())));
    }

    public boolean matches(ItemStack item) {
      return this.item.isEmpty() || ((ItemPredicate) this.item.get()).test(item);
    }

    public Optional<ContextAwarePredicate> player() {
      return this.player;
    }

    public Optional<ItemPredicate> item() {
      return this.item;
    }
  }
}
