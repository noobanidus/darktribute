package noobanidus.mods.darktribute.advancement;

import com.google.gson.JsonElement;
import net.minecraft.entity.player.ServerPlayerEntity;
import noobanidus.libs.noobutil.advancement.IGenericPredicate;

import javax.annotation.Nullable;

public class TributePredicate implements IGenericPredicate<Void> {
  @Override
  public boolean test(ServerPlayerEntity player, Void condition) {
    return true;
  }

  @Override
  public IGenericPredicate<Void> deserialize(@Nullable JsonElement element) {
    return new TributePredicate();
  }
}
