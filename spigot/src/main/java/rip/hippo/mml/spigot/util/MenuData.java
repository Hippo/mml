package rip.hippo.mml.spigot.util;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;

/**
 * @author Hippo
 */
public final class MenuData {

  private final String title;
  private final List<String> creationData;
  private final boolean allowDragging;
  private final boolean allowUnderInventoryInteract;

  public MenuData(String title,
                  List<String> creationData,
                  boolean allowDragging,
                  boolean allowUnderInventoryInteract) {
    this.title = title;
    this.creationData = creationData;
    this.allowDragging = allowDragging;
    this.allowUnderInventoryInteract = allowUnderInventoryInteract;
  }

  public MenuData(String title,
                  List<String> creationData) {
    this(title, creationData, false, false);
  }

  public String getTitle() {
    return title;
  }

  public List<String> getCreationData() {
    return creationData;
  }

  public boolean isAllowDragging() {
    return allowDragging;
  }

  public boolean isAllowUnderInventoryInteract() {
    return allowUnderInventoryInteract;
  }

  public static MenuData of(ConfigurationSection configurationSection) {
    String title = Objects.requireNonNull(configurationSection.getString("title"));
    List<String> creationData = Objects.requireNonNull(configurationSection.getStringList("creation-data"));
    boolean dragging = configurationSection.getBoolean("allow-dragging", false);
    boolean interact = configurationSection.getBoolean("allow-under-inventory-interact", false);

    return new MenuData(title, creationData, dragging, interact);
  }
}
