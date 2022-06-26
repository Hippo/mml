package rip.hippo.mml.spigot.component;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rip.hippo.mml.Menu;
import rip.hippo.mml.component.impl.DisplayComponent;
import rip.hippo.mml.spigot.util.ItemStackBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Hippo
 */
public final class ItemDisplay implements DisplayComponent<ItemStack, Player, String, Menu<String, Player>> {

  private final BiFunction<Player, Menu<String, Player>, ItemStack> itemSupplier;

  public ItemDisplay(BiFunction<Player, Menu<String, Player>, ItemStack> itemSupplier) {
    this.itemSupplier = itemSupplier;
  }

  public ItemDisplay(Function<Player, ItemStack> itemSupplier) {
    this((player, ignored) -> itemSupplier.apply(player));
  }

  public ItemDisplay(ItemStack itemStack) {
    this.itemSupplier = (ignored, ignored1) -> itemStack;
  }

  public ItemDisplay(Material material, int damage) {
    this(
        new ItemStackBuilder(false)
            .name(ChatColor.RESET.toString())
            .material(material)
            .damage(damage)
            .build()
    );
  }

  public ItemDisplay(Material material) {
    this(material, 0);
  }

  @Override
  public ItemStack get(Player player, Menu<String, Player> menu) {
    return itemSupplier.apply(player, menu);
  }
}
