package rip.hippo.mml.spigot.component;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import rip.hippo.mml.component.impl.DisplayComponent;
import rip.hippo.mml.spigot.util.ItemStackBuilder;

/**
 * @author Hippo
 */
public final class ItemDisplay implements DisplayComponent<ItemStack> {


  private final ItemStack itemStack;

  public ItemDisplay(ItemStack itemStack) {
    this.itemStack = itemStack;
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
  public ItemStack get() {
    return itemStack;
  }
}
