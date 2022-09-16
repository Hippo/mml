package rip.hippo.mml.spigot.bind;

import rip.hippo.mml.spigot.util.ItemStackBuilder;
import rip.hippo.mml.spigot.util.MenuData;

import java.util.Map;

/**
 * @author Hippo
 */
public interface MenuRegistry {
  void putMenu(String key, MenuData menuData);
  void putItem(String key, ItemStackBuilder itemStackBuilder);
  void removeMenu(String key);
  void removeItem(String key);
  boolean hasMenu(String key);
  boolean hasItem(String key);
  MenuData getMenu(String key);
  ItemStackBuilder getItem(String key);

  Map<String, MenuData> getMenus();
  Map<String, ItemStackBuilder> getItems();
}
