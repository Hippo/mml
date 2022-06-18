package rip.hippo.mml.spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import rip.hippo.mml.component.ComponentAttribute;
import rip.hippo.mml.component.impl.binder.StandardComponentBinder;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.impl.StandardMenuBus;
import rip.hippo.mml.impl.StandardMenuFactory;
import rip.hippo.mml.spigot.component.DecorComponentBinder;
import rip.hippo.mml.spigot.component.SpigotMenuBus;
import rip.hippo.mml.spigot.component.SpigotPayload;
import rip.hippo.mml.spigot.listener.PlayerListener;
import rip.hippo.mml.spigot.util.MenuData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Hippo
 */
public final class MenuLibrary {

  private final Map<Player, StandardMenu<Player>> playerMenuMap;
  private final StandardComponentBinder standardComponentBinder;
  private final StandardMenuFactory<Player> menuFactory;
  private final StandardMenuBus<SpigotPayload, Player> menuBus;

  public MenuLibrary(Map<Player, StandardMenu<Player>> playerMenuMap,
                     StandardComponentBinder standardComponentBinder,
                     StandardMenuFactory<Player> menuFactory,
                     StandardMenuBus<SpigotPayload, Player> menuBus) {
    this.playerMenuMap = playerMenuMap;
    this.standardComponentBinder = standardComponentBinder;
    this.menuFactory = menuFactory;
    this.menuBus = menuBus;
  }

  public MenuLibrary() {
    this.playerMenuMap = new HashMap<>();
    this.standardComponentBinder = new StandardComponentBinder();
    this.menuFactory = new StandardMenuFactory<>();
    this.menuBus = new SpigotMenuBus();
  }

  public void install(JavaPlugin javaPlugin) {
    Bukkit.getPluginManager().registerEvents(new PlayerListener(this), javaPlugin);
  }

  public StandardMenuApplicator<Player> getApplicator() {
    return new SpigotMenuApplicator(playerMenuMap, standardComponentBinder);
  }

  public StandardMenuApplicator<Player> getApplicator(Material material) {
    return new SpigotMenuApplicator(playerMenuMap, new DecorComponentBinder(material, standardComponentBinder));
  }

  public void bind(String binder, ComponentAttribute... componentAttributes) {
    standardComponentBinder.bind(binder, componentAttributes);
  }

  public StandardMenu<Player> create(StandardMenuApplicator<Player> menuApplicator, MenuData menuData) {
    return create(menuApplicator,
        menuData.getTitle(),
        menuData.getCreationData(),
        menuData.isAllowDragging(),
        menuData.isAllowUnderInventoryInteract());
  }
  public StandardMenu<Player> create(StandardMenuApplicator<Player> menuApplicator,
                             String title,
                             List<String> creationData,
                             boolean allowDragging,
                             boolean allowUnderInventoryInteract) {
    return menuFactory.create(menuApplicator, title, creationData, allowDragging, allowUnderInventoryInteract);
  }

  public StandardMenu<Player> create(StandardMenuApplicator<Player> menuApplicator,
                                     String title,
                                     List<String> creationData) {
    return create(menuApplicator, title, creationData, false, false);
  }
  public Optional<StandardMenu<Player>> getMenu(Player player) {
    return Optional.ofNullable(playerMenuMap.get(player));
  }
  public void remove(Player player) {
    playerMenuMap.remove(player);
  }

  public StandardMenuBus<SpigotPayload, Player> getMenuBus() {
    return menuBus;
  }
}
