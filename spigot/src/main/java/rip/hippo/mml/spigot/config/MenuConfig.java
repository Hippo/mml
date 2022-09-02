package rip.hippo.mml.spigot.config;

import org.bukkit.configuration.ConfigurationSection;
import rip.hippo.mml.spigot.util.ItemStackBuilder;
import rip.hippo.mml.spigot.util.MenuData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Hippo
 */
public abstract class MenuConfig {

  private final Map<String, ItemStackBuilder> itemMap;
  private final Map<String, MenuData> menuMap;


  public MenuConfig(ConfigurationSection configurationSection) {
    this.itemMap = new HashMap<>();
    this.menuMap = new HashMap<>();

    ConfigurationSection items = Objects.requireNonNull(configurationSection.getConfigurationSection("items"));
    ConfigurationSection menus = Objects.requireNonNull(configurationSection.getConfigurationSection("menus"));
    for (String key : items.getKeys(false)) {
      itemMap.put(key, ItemStackBuilder.of(Objects.requireNonNull(items.getConfigurationSection(key))));
    }
    for (String key : menus.getKeys(false)) {
      menuMap.put(key, MenuData.of(Objects.requireNonNull(menus.getConfigurationSection(key))));
    }
  }

  public ItemStackBuilder getItem(String key) {
    return itemMap.get(key);
  }

  public MenuData getMenu(String key) {
    return menuMap.get(key);
  }

  public boolean hasItem(String key) {
    return itemMap.containsKey(key);
  }

  public boolean hasMenu(String key) {
    return menuMap.containsKey(key);
  }
}