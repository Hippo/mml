package rip.hippo.mml.spigot.page;

import rip.hippo.mml.spigot.util.MenuData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hippo
 */
public final class IndexedMenu {

  private final MenuData menuData;
  private final Map<Integer, Integer> slotMappings;
  private int size;
  public IndexedMenu(String component, MenuData menuData, int offset) {
    this.menuData = menuData;
    this.slotMappings = new HashMap<>();
    int slot = 0;
    int count = offset;
    for (String creationDatum : menuData.getCreationData()) {
      String[] split = creationDatum.split(" ");
      for (String comp : split) {
        if (comp.equals(component)) {
          slotMappings.put(slot, count);
          size++;
          count++;
        }
        slot++;
      }
    }
  }

  public boolean hasMapping(int slot) {
    return slotMappings.containsKey(slot);
  }

  public int getIndex(int slot) {
    return slotMappings.get(slot);
  }

  public int getSize() {
    return size;
  }

  public MenuData getMenuData() {
    return menuData;
  }
}

