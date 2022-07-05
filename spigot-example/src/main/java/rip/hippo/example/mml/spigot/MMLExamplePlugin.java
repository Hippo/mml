package rip.hippo.example.mml.spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import rip.hippo.example.mml.spigot.command.ExampleCommand;
import rip.hippo.mml.spigot.MenuLibrary;
import rip.hippo.mml.spigot.component.ClickInteract;
import rip.hippo.mml.spigot.component.ItemDisplay;
import rip.hippo.mml.spigot.util.ItemStackBuilder;
import rip.hippo.mml.spigot.util.MenuData;

import java.util.logging.Level;

/**
 * @author Hippo
 */
public final class MMLExamplePlugin extends JavaPlugin {

  private final MenuLibrary menuLibrary = new MenuLibrary();

  @Override
  public void onEnable() {
    saveDefaultConfig();
    menuLibrary.install(this);


    menuLibrary.bind("x", ClickInteract.DENY);
    menuLibrary.bind(
        "foo",
        new ItemDisplay(
            new ItemStackBuilder()
                .name("&cfoo")
                .material(Material.PAPER)
                .build()
        ),
        new ClickInteract((player, payload) -> {
          player.sendMessage("foo");
          return true;
        }));

    menuLibrary.bind(
        "bar",
        new ItemDisplay(
            new ItemStackBuilder()
                .name("&dbar")
                .material(Material.PAPER)
                .build()
        ), ClickInteract.DENY);

    menuLibrary.bind("baz",
        new ClickInteract((player, payload) -> {
          player.sendMessage("baz");
          return false;
        }));

    Bukkit.getPluginCommand("mmlexample")
        .setExecutor(new ExampleCommand(menuLibrary, MenuData.of(getConfig())));

    Bukkit.getLogger().log(Level.INFO, Bukkit.getVersion());
    Bukkit.getLogger().log(Level.INFO, Bukkit.getBukkitVersion());
  }
}
