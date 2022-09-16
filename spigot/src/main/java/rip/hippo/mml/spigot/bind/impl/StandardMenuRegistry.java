package rip.hippo.mml.spigot.bind.impl;

import rip.hippo.mml.spigot.bind.MenuRegistry;
import rip.hippo.mml.spigot.util.ItemStackBuilder;
import rip.hippo.mml.spigot.util.MenuData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hippo
 */
public final class StandardMenuRegistry implements MenuRegistry {

  private final Map<String, MenuData> menuDataMap = new HashMap<>();
  private final Map<String, ItemStackBuilder> itemStackBuilderMap = new HashMap<>();

  @Override
  public void putMenu(String key, MenuData menuData) {
    menuDataMap.put(key, menuData);
  }

  @Override
  public void putItem(String key, ItemStackBuilder itemStackBuilder) {
    itemStackBuilderMap.put(key, itemStackBuilder);
  }

  @Override
  public void removeMenu(String key) {
    menuDataMap.remove(key);
  }

  @Override
  public void removeItem(String key) {
    itemStackBuilderMap.remove(key);
  }

  @Override
  public boolean hasMenu(String key) {
    return menuDataMap.containsKey(key);
  }

  @Override
  public boolean hasItem(String key) {
    return itemStackBuilderMap.containsKey(key);
  }

  @Override
  public MenuData getMenu(String key) {
    return menuDataMap.get(key);
  }

  @Override
  public ItemStackBuilder getItem(String key) {
    return itemStackBuilderMap.get(key);
  }

  @Override
  public Map<String, MenuData> getMenus() {
    return menuDataMap;
  }

  @Override
  public Map<String, ItemStackBuilder> getItems() {
    return itemStackBuilderMap;
  }
}
