package rip.hippo.mml.spigot.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import rip.hippo.mml.component.ComponentPayload;

/**
 * @author Hippo
 */
public final class SpigotPayload implements ComponentPayload<HumanEntity> {

  private final InventoryClickEvent inventoryClickEvent;

  public SpigotPayload(InventoryClickEvent inventoryClickEvent) {
    this.inventoryClickEvent = inventoryClickEvent;
  }

  @Override
  public HumanEntity getEntity() {
    return inventoryClickEvent.getWhoClicked();
  }

  public InventoryClickEvent getInventoryClickEvent() {
    return inventoryClickEvent;
  }
}
