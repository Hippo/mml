package rip.hippo.example.mml.spigot.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.hippo.example.mml.spigot.config.MenuConfig;
import rip.hippo.mml.Menu;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.spigot.MenuLibrary;

/**
 * @author Hippo
 */
public final class ExampleCommand implements CommandExecutor {

  private final MenuLibrary menuLibrary;
  private final MenuConfig menuConfig;

  public ExampleCommand(MenuLibrary menuLibrary, MenuConfig menuConfig) {
    this.menuLibrary = menuLibrary;
    this.menuConfig = menuConfig;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }
    Player player = (Player) sender;
    boolean pane = args.length > 0 && args[0].equalsIgnoreCase("-p");

    StandardMenuApplicator<Player> applicator = menuLibrary.getApplicator(pane ? Material.STAINED_GLASS_PANE : Material.STAINED_GLASS);

    StandardMenu<Player> menu = menuLibrary.create(
        applicator,
        menuConfig.getTitle(),
        menuConfig.getMenuData(),
        menuConfig.isAllowDragging(),
        menuConfig.isAllowUnderInventoryInteract());



    applicator.open(player, menu);

    return true;
  }
}
