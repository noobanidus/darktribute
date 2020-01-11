package noobanidus.mods.darktribute.entities;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import noobanidus.mods.darktribute.events.TributeHandler;

import java.util.Objects;

public class DiamondEntity extends ItemEntity {
  public boolean tribute = false;

  public DiamondEntity(ItemEntity item) {
    super(item.world, item.posX, item.posY, item.posZ, item.getItem());
  }

  @Override
  public void remove() {
    super.remove();
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if ((isInLava() || source.isFireDamage()) && isAlive() && !this.world.isRemote() && !tribute) {
      if (getThrowerId() != null) {
        TributeHandler.giveTribute(this, getThrower(), getItem().getCount());
        this.tribute = true;
      }
    }
    return super.attackEntityFrom(source, amount);
  }

  private PlayerEntity getThrower() {
    return world.getPlayerByUuid(Objects.requireNonNull(getThrowerId()));
  }
}
