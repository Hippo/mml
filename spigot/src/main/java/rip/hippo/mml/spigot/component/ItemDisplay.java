package rip.hippo.mml.spigot.component;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rip.hippo.mml.component.impl.DisplayComponent;
import rip.hippo.mml.spigot.util.ItemStackBuilder;

import java.util.function.Function;

/**
 * @author Hippo
 */
public final class ItemDisplay implements DisplayComponent<ItemStack, Player> {

  private final Function<Player, ItemStack> itemSupplier;

  public ItemDisplay(Function<Player, ItemStack> itemSupplier) {
    this.itemSupplier = itemSupplier;
  }
  public ItemDisplay(ItemStack itemStack) {
    this.itemSupplier = ignored -> itemStack;
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
  public ItemStack get(Player player) {
    return itemSupplier.apply(player);
  }
}
