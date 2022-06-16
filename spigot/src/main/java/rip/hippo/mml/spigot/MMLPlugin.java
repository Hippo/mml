package rip.hippo.mml.spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * @author Hippo
 */
public final class MMLPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    Bukkit.getLogger().log(Level.INFO, "Running MML v1.0.0");
  }
}
