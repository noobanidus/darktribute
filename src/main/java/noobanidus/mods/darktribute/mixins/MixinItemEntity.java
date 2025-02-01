package noobanidus.mods.darktribute.mixins;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import noobanidus.mods.darktribute.DarkTributeTags;
import noobanidus.mods.darktribute.events.TributeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class MixinItemEntity {
  @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;onDestroyed(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/damagesource/DamageSource;)V"))
  private void DarkTributeHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
    ItemEntity thisEntity = ((ItemEntity) (Object) this);
    if (source.is(DarkTributeTags.DamageTypes.IS_LAVA) && thisEntity.getItem()
        .is(DarkTributeTags.Items.TRIBUTE_ITEMS)) {
      if (thisEntity.getOwner() instanceof ServerPlayer player) {
        TributeHandler.giveTribute(thisEntity, player, thisEntity.getItem().getCount());
      }
    }
  }
}
