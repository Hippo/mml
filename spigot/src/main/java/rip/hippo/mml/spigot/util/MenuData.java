package rip.hippo.mml.spigot.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.spigot.MenuLibrary;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * @author Hippo
 */
public final class MenuData {

  private final String title;
  private final List<String> creationData;
  private final boolean allowDragging;
  private final boolean allowUnderInventoryInteract;
  private final Material decor;

  private Object metaData;

  public MenuData(String title,
                  List<String> creationData,
                  boolean allowDragging,
                  boolean allowUnderInventoryInteract,
                  Material decor) {
    this.title = title;
    this.creationData = creationData;
    this.allowDragging = allowDragging;
    this.allowUnderInventoryInteract = allowUnderInventoryInteract;
    this.decor = decor;
  }

  public MenuData(String title,
                  List<String> creationData) {
    this(title, creationData, false, false, null);
  }

  public void accept(MenuLibrary menuLibrary,
                     BiConsumer<StandardMenuApplicator<Player>, StandardMenu<Player>> consumer) {
    StandardMenuApplicator<Player> applicator = getDecor().isPresent() ?
        menuLibrary.getApplicator(decor) : menuLibrary.getApplicator();
    StandardMenu<Player> menu = menuLibrary.create(applicator, this);
    consumer.accept(applicator, menu);
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

  public Optional<Material> getDecor() {
    return Optional.ofNullable(decor);
  }

  public Object getMetaData() {
    return metaData;
  }

  public void setMetaData(Object metaData) {
    this.metaData = metaData;
  }

  public static MenuData of(ConfigurationSection configurationSection) {
    String title = Objects.requireNonNull(configurationSection.getString("title"));
    List<String> creationData = Objects.requireNonNull(configurationSection.getStringList("creation-data"));
    boolean dragging = configurationSection.getBoolean("allow-dragging", false);
    boolean interact = configurationSection.getBoolean("allow-under-inventory-interact", false);
    Material decor = null;
    String decorMaterial = configurationSection.getString("decor");
    if (decorMaterial != null) {
      decor = Material.valueOf(decorMaterial);
    }

    return new MenuData(title, creationData, dragging, interact, decor);
  }
}
