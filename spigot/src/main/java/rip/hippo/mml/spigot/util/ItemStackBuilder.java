package rip.hippo.mml.spigot.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import rip.hippo.translate.ChatTranslate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    this.name = translateColorCodes ? ChatTranslate.translate(name) : name;
    return this;
  }

  public ItemStackBuilder material(Material material) {
    this.material = material;
    return this;
  }

  public ItemStackBuilder addLoreLine(String line) {
    lore.add(translateColorCodes ? ChatTranslate.translate(line) : line);
    return this;
  }

  public ItemStackBuilder addLores(List<String> lines) {
    for (String line : lines) {
      addLoreLine(line);
    }
    return this;
  }

  public ItemStackBuilder setLores(List<String> lines) {
    lore.clear();
    addLores(lines);
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

  public ItemStackBuilder copy() {
    ItemStackBuilder itemStackBuilder = new ItemStackBuilder(translateColorCodes);
    itemStackBuilder.name = name;
    itemStackBuilder.material = material;
    itemStackBuilder.amount = amount;
    itemStackBuilder.damage = damage;
    itemStackBuilder.lore.addAll(lore);
    return itemStackBuilder;
  }

  public ItemStack build() {
    ItemStack itemStack = new ItemStack(material, amount);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemStack.setDurability(damage);

    if (itemMeta != null) {
      if (name != null) {
        itemMeta.setDisplayName(name);
      }
      if (!lore.isEmpty()) {
        itemMeta.setLore(lore);
      }
      itemStack.setItemMeta(itemMeta);
    }

    return itemStack;
  }

  public String getName() {
    return name;
  }

  public Material getMaterial() {
    return material;
  }

  public int getAmount() {
    return amount;
  }

  public short getDamage() {
    return damage;
  }

  public List<String> getLore() {
    return lore;
  }

  public static ItemStackBuilder of(ConfigurationSection configurationSection, boolean translateColorCodes) {
    String name = configurationSection.getString("name");
    Material material = Material.valueOf(Objects.requireNonNull(configurationSection.getString("material")));
    List<String> lore = configurationSection.getStringList("lore");
    int damage = configurationSection.getInt("damage", -1);
    int amount = configurationSection.getInt("amount", 1);

    ItemStackBuilder itemStackBuilder = new ItemStackBuilder(translateColorCodes)
        .material(material)
        .addLores(lore)
        .amount(amount);

    if (name != null) {
      itemStackBuilder.name(name);
    }

    if (damage != -1) {
      itemStackBuilder.damage(damage);
    }

    return itemStackBuilder;
  }

  public static ItemStackBuilder of(ConfigurationSection configurationSection) {
    return of(configurationSection, true);
  }
}
