package rip.hippo.example.mml.spigot.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @author Hippo
 */
public final class MenuConfig {

  private final String title;
  private final List<String> menuData;
  private final boolean allowDragging, allowUnderInventoryInteract;

  public MenuConfig(FileConfiguration fileConfiguration) {
    this.title = fileConfiguration.getString("title");
    this.menuData = fileConfiguration.getStringList("contents");
    this.allowDragging = fileConfiguration.getBoolean("allow-dragging");
    this.allowUnderInventoryInteract = fileConfiguration.getBoolean("allow-under-inventory-interact");
  }

  public String getTitle() {
    return title;
  }

  public List<String> getMenuData() {
    return menuData;
  }

  public boolean isAllowDragging() {
    return allowDragging;
  }

  public boolean isAllowUnderInventoryInteract() {
    return allowUnderInventoryInteract;
  }
}
