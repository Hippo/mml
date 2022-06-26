package rip.hippo.mml.spigot.component;

import org.bukkit.entity.Player;
import rip.hippo.mml.Menu;
import rip.hippo.mml.impl.StandardMenu;

/**
 * @author Hippo
 */
public final class DisplayPayload {

  private final StandardMenu<Player> menu;
  private final int index;

  public DisplayPayload(StandardMenu<Player> menu, int index) {
    this.menu = menu;
    this.index = index;
  }

  public StandardMenu<Player> getMenu() {
    return menu;
  }

  public int getIndex() {
    return index;
  }
}
