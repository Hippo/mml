package rip.hippo.mml.spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.spigot.component.DisplayPayload;
import rip.hippo.mml.spigot.component.ItemDisplay;
import rip.hippo.translate.ChatTranslate;

import java.util.Map;


/**
 * @author Hippo
 */
public final class SpigotMenuApplicator implements StandardMenuApplicator<Player> {

  private final Map<Player, StandardMenu<Player>> playerMenuMap;
  private final ComponentBinder<String> componentBinder;
  private final boolean translateColor;

  public SpigotMenuApplicator(Map<Player, StandardMenu<Player>> playerMenuMap,
                              ComponentBinder<String> componentBinder, boolean translateColor) {
    this.playerMenuMap = playerMenuMap;
    this.componentBinder = componentBinder;
    this.translateColor = translateColor;
  }

  public SpigotMenuApplicator(Map<Player, StandardMenu<Player>> playerMenuMap,
                              ComponentBinder<String> componentBinder) {
    this(playerMenuMap, componentBinder, true);
  }

  @Override
  public void open(Player entity, Menu<String, Player> menu) {

    if (!(menu instanceof StandardMenu)) {
      throw new IllegalArgumentException("Menu is not a standard menu!");
    }

    Inventory inventory = Bukkit.createInventory(null, menu.getRows() * 9,
        translateColor ? ChatTranslate.translate(menu.getTitle()) : menu.getTitle());

    String[] binds = menu.getAll();
    for (int i = 0; i < binds.length; i++) {
      String binder = binds[i];
      int index = i;
      componentBinder.getComponent(binder, ItemDisplay.class)
          .ifPresent(display -> inventory.setItem(index, display.get(entity, new DisplayPayload((StandardMenu<Player>) menu, index))));
    }

    entity.openInventory(inventory);
    playerMenuMap.put(entity, (StandardMenu<Player>) menu);
  }

  @Override
  public ComponentBinder<String> getComponentBinder() {
    return componentBinder;
  }
}
