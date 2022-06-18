package rip.hippo.mml.spigot.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import rip.hippo.mml.component.ComponentPayload;
import rip.hippo.mml.impl.StandardMenu;

/**
 * @author Hippo
 */
public final class SpigotPayload implements ComponentPayload<HumanEntity> {

  private final InventoryClickEvent inventoryClickEvent;
  private final StandardMenu<Player> menu;

  public SpigotPayload(InventoryClickEvent inventoryClickEvent, StandardMenu<Player> menu) {
    this.inventoryClickEvent = inventoryClickEvent;
    this.menu = menu;
  }

  @Override
  public HumanEntity getEntity() {
    return inventoryClickEvent.getWhoClicked();
  }

  public InventoryClickEvent getInventoryClickEvent() {
    return inventoryClickEvent;
  }

  public StandardMenu<Player> getMenu() {
    return menu;
  }
}
