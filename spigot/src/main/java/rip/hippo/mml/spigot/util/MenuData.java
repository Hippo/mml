package rip.hippo.mml.spigot.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.spigot.MenuLibrary;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Hippo
 */
public final class MenuData {

  private String title;
  private final List<String> creationData;
  private boolean allowDragging;
  private boolean allowUnderInventoryInteract;
  private String decor;

  private transient final Map<String, Object> metaDataMap;

  public MenuData(String title,
                  List<String> creationData,
                  boolean allowDragging,
                  boolean allowUnderInventoryInteract,
                  String decor) {
    this.title = title;
    this.creationData = creationData;
    this.allowDragging = allowDragging;
    this.allowUnderInventoryInteract = allowUnderInventoryInteract;
    this.decor = decor;
    this.metaDataMap = new HashMap<>();
  }

  public MenuData(String title,
                  List<String> creationData) {
    this(title, creationData, false, false, null);
  }

  public MenuData(MenuData menuData) {
    this(menuData.title,
        new ArrayList<>(menuData.creationData),
        menuData.allowDragging,
        menuData.allowUnderInventoryInteract,
        menuData.decor);
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

  public Optional<String> getDecor() {
    return Optional.ofNullable(decor);
  }

  public Map<String, Object> getMetaDataMap() {
    return metaDataMap;
  }

  public void addMetaData(String key, Object value) {
    metaDataMap.put(key, value);
  }

  public MenuData copy() {
    return new MenuData(this);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAllowDragging(boolean allowDragging) {
    this.allowDragging = allowDragging;
  }

  public void setAllowUnderInventoryInteract(boolean allowUnderInventoryInteract) {
    this.allowUnderInventoryInteract = allowUnderInventoryInteract;
  }

  public void setDecor(String decor) {
    this.decor = decor;
  }

  public static MenuData of(ConfigurationSection configurationSection) {
    String title = Objects.requireNonNull(configurationSection.getString("title"));
    List<String> creationData = Objects.requireNonNull(configurationSection.getStringList("creation-data"));
    boolean dragging = configurationSection.getBoolean("allow-dragging", false);
    boolean interact = configurationSection.getBoolean("allow-under-inventory-interact", false);
    String decor = configurationSection.getString("decor");

    return new MenuData(title, creationData, dragging, interact, decor);
  }
}
