package rip.hippo.mml.spigot.listener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rip.hippo.mml.Menu;
import rip.hippo.mml.spigot.MenuLibrary;
import rip.hippo.mml.spigot.component.SpigotPayload;

/**
 * @author Hippo
 */
public final class PlayerListener implements Listener {

  private final MenuLibrary menuLibrary;

  public PlayerListener(MenuLibrary menuLibrary) {
    this.menuLibrary = menuLibrary;
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
    menuLibrary.remove(playerQuitEvent.getPlayer());
  }

  @EventHandler
  public void onPlayerCloseInventory(InventoryCloseEvent inventoryCloseEvent) {
    HumanEntity humanEntity = inventoryCloseEvent.getPlayer();
    if (humanEntity instanceof Player) {
      Player player = (Player) humanEntity;
      menuLibrary.remove(player);
    }
  }

  @EventHandler
  public void onInventoryDrag(InventoryDragEvent inventoryDragEvent) {
    HumanEntity whoClicked = inventoryDragEvent.getWhoClicked();
    if (!(whoClicked instanceof Player)) {
      return;
    }
    Player player = (Player) whoClicked;
    menuLibrary.getMenu(player)
        .map(Menu::isAllowedDragging)
        .ifPresent(b -> inventoryDragEvent.setCancelled(!b));
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
    HumanEntity whoClicked = inventoryClickEvent.getWhoClicked();
    if (!(whoClicked instanceof Player)) {
      return;
    }
    Player player = (Player) whoClicked;
    menuLibrary.getMenu(player)
        .ifPresent(menu -> menuLibrary.getMenuBus()
            .message(menu.getMenuApplicator().getComponentBinder(),menu, new SpigotPayload(inventoryClickEvent)));
  }

}
