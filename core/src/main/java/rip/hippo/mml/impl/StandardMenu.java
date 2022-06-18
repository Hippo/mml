package rip.hippo.mml.impl;

import rip.hippo.mml.Menu;

import java.util.Optional;

/**
 * @param <T> Entity type
 *
 * @author Hippo
 */
public final class StandardMenu<T> implements Menu<String, T> {

  private final StandardMenuApplicator<T> menuApplicator;
  private final String title;
  private final int rows;
  private final boolean allowDragging, allowUnderInventoryInteract;
  private final String[] binds;
  private final Object metaData;

  public StandardMenu(StandardMenuApplicator<T> menuApplicator,
                      String title,
                      int rows,
                      boolean allowDragging,
                      boolean allowUnderInventoryInteract,
                      Object metaData) {
    this.menuApplicator = menuApplicator;
    this.title = title;
    this.rows = rows;
    this.allowDragging = allowDragging;
    this.allowUnderInventoryInteract = allowUnderInventoryInteract;
    this.binds = new String[rows * 9];
    this.metaData = metaData;
  }

  @Override
  public void set(int slot, String binder) {
    this.binds[slot] = binder;
  }

  @Override
  public Optional<String> get(int slot) {
    if (slot < 0 || slot >= binds.length) {
      return Optional.empty();
    }
    return Optional.ofNullable(binds[slot]);
  }

  @Override
  public String[] getAll() {
    return binds;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public boolean isAllowedDragging() {
    return allowDragging;
  }

  @Override
  public boolean isAllowUnderInventoryInteract() {
    return allowUnderInventoryInteract;
  }

  @Override
  public StandardMenuApplicator<T> getMenuApplicator() {
    return menuApplicator;
  }

  @Override
  public <U> Optional<U> getMetaData(Class<U> parent) {
    if (parent.isInstance(metaData)) {
      return Optional.of(parent.cast(metaData));
    }
    return Optional.empty();
  }
}
