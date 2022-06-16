package rip.hippo.mml.spigot.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Hippo
 */
public final class ItemStackBuilder {

  private final boolean translateColorCodes;

  private String name;
  private Material material;
  private int amount = 1;
  private short damage;
  private final List<String> lore = new LinkedList<>();

  public ItemStackBuilder(boolean translateColorCodes) {
    this.translateColorCodes = translateColorCodes;
  }

  public ItemStackBuilder() {
    this(true);
  }

  public ItemStackBuilder name(String name) {
    this.name = translateColorCodes ? ChatColor.translateAlternateColorCodes('&', name) : name;
    return this;
  }

  public ItemStackBuilder material(Material material) {
    this.material = material;
    return this;
  }

  public ItemStackBuilder addLoreLine(String line) {
    lore.add(translateColorCodes ? ChatColor.translateAlternateColorCodes('&', line) : line);
    return this;
  }

  public ItemStackBuilder addLores(List<String> lines) {
    for (String line : lines) {
      addLoreLine(line);
    }
    return this;
  }

  public ItemStackBuilder amount(int amount) {
    this.amount = amount;
    return this;
  }

  public ItemStackBuilder damage(int damage) {
    this.damage = (short) damage;
    return this;
  }

  public ItemStack build() {
    ItemStack itemStack = new ItemStack(material, amount);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemStack.setDurability(damage);

    if (itemMeta != null) {
      itemMeta.setDisplayName(name);
      itemMeta.setLore(lore);
      itemStack.setItemMeta(itemMeta);
    }

    return itemStack;
  }
}
