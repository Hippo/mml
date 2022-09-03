package rip.hippo.mml.impl;

import rip.hippo.mml.MenuFactory;

import java.util.List;
import java.util.Map;

/**
 * @param <T> Entity type
 *
 * @author Hippo
 */
public final class StandardMenuFactory<T> implements MenuFactory<String, T> {

  @Override
  public StandardMenu<T> create(StandardMenuApplicator<T> applicator,
                                String title,
                                List<String> creationData,
                                boolean allowDragging,
                                boolean allowUnderInventoryInteract,
                                Map<String, Object> metaDataMap) {
    int rows = creationData.size();
    StandardMenu<T> menu = new StandardMenu<>(applicator,
        title,
        rows,
        allowDragging,
        allowUnderInventoryInteract,
        metaDataMap);

    int row = 0;
    for (String line : creationData) {
      String[] binds = line.split(" ");
      if (binds.length != 9) {
        throw new IllegalArgumentException("Not enough binds on row: " + row + " for: " + title);
      }
      for (int i = 0; i < 9; i++) {
        menu.set((row * 9) + i, binds[i]);
      }
      row++;
    }

    return menu;
  }
}
