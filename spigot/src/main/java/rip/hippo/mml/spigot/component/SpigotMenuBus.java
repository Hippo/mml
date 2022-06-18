package rip.hippo.mml.spigot.component;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.impl.StandardMenuBus;

/**
 * @author Hippo
 */
public final class SpigotMenuBus implements StandardMenuBus<SpigotPayload, Player> {

  @Override
  public void message(ComponentBinder<String> componentBinder, Menu<String, Player> menu, SpigotPayload payload) {

    InventoryClickEvent inventoryClickEvent = payload.getInventoryClickEvent();
    if (inventoryClickEvent.getSlot() != inventoryClickEvent.getRawSlot()) {
      if (inventoryClickEvent.isShiftClick()) {
        inventoryClickEvent.setCancelled(true);
        return;
      }
      inventoryClickEvent.setCancelled(!menu.isAllowUnderInventoryInteract());
      return;
    }

    menu.get(inventoryClickEvent.getSlot()).ifPresent(binder -> {
      componentBinder.getComponent(binder, ClickInteract.class).ifPresent(interact -> {
        interact.onInteract(payload);
      });
    });
  }
}
