package noobanidus.mods.darktribute.entities;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import noobanidus.mods.darktribute.events.TributeHandler;

import java.util.Objects;

public class DiamondEntity extends ItemEntity {
  public boolean tribute = false;

  public DiamondEntity(ItemEntity item) {
    super(item.level, item.getX(), item.getY(), item.getZ(), item.getItem());
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    if ((isInLava() || source.isFire()) && isAlive() && !this.level.isClientSide() && !tribute) {
      if (getThrower() != null) {
        TributeHandler.giveTribute(this, getThrowerPlayer(), getItem().getCount());
        this.tribute = true;
      }
    }
    return super.hurt(source, amount);
  }

  private Player getThrowerPlayer() {
    return level.getPlayerByUUID(Objects.requireNonNull(getThrower()));
  }
}
