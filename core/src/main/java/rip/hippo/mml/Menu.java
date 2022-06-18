package rip.hippo.mml;

import rip.hippo.mml.impl.StandardMenuApplicator;

import java.util.Optional;

/**
 * @param <T> Binding object
 * @param <S> Entity type
 *
 * @author Hippo
 */
public interface Menu<T, S> {
  void set(int slot, T binder);
  Optional<T> get(int slot);

  T[] getAll();

  String getTitle();
  int getRows();

  boolean isAllowedDragging();

  boolean isAllowUnderInventoryInteract();

  StandardMenuApplicator<S> getMenuApplicator();

  <U> Optional<U> getMetaData(Class<U> parent);

}
