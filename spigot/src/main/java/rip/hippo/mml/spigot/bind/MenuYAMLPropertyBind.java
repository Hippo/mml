package rip.hippo.mml.spigot.bind;

import org.bukkit.configuration.ConfigurationSection;
import rip.hippo.mml.spigot.bind.impl.StandardMenuRegistry;
import rip.hippo.mml.spigot.util.ItemStackBuilder;
import rip.hippo.mml.spigot.util.MenuData;
import rip.hippo.possi.spigot.source.YAMLPropertyBind;
import rip.hippo.possi.spigot.source.YAMLPropertySource;

import java.util.Objects;

/**
 * @author Hippo
 */
public final class MenuYAMLPropertyBind extends YAMLPropertyBind<MenuRegistry> {

  public MenuYAMLPropertyBind(YAMLPropertySource source, String path) {
    super(source, path);
  }

  @Override
  public MenuRegistry read(ConfigurationSection section) {
    MenuRegistry menuRegistry = new StandardMenuRegistry();

    ConfigurationSection menus = Objects.requireNonNull(section.getConfigurationSection(getPath() + ".menus"));
    ConfigurationSection items = Objects.requireNonNull(section.getConfigurationSection(getPath() + ".items"));
    for (String key : items.getKeys(false)) {
      menuRegistry.putItem(key, ItemStackBuilder.of(Objects.requireNonNull(items.getConfigurationSection(key))));
    }
    for (String key : menus.getKeys(false)) {
      menuRegistry.putMenu(key, MenuData.of(Objects.requireNonNull(menus.getConfigurationSection(key))));
    }

    return menuRegistry;
  }

  @Override
  public void write(ConfigurationSection section, MenuRegistry value) {
    ConfigurationSection menus = section.createSection("menus");
    ConfigurationSection items = section.createSection("items");

    value.getItems().forEach((key, itemStackBuilder) -> itemStackBuilder.write(items.createSection(key)));
    value.getMenus().forEach((key, menuData) -> menuData.write(menus.createSection(key)));
  }
}
