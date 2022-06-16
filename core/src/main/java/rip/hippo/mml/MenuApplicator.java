package rip.hippo.mml;

import rip.hippo.mml.component.ComponentBinder;

/**
 * @param <T> binding object
 * @param <S> Entity type
 * @param <U> Menu type
 *
 * @author Hippo
 */
public interface MenuApplicator<T, S, U extends Menu<T, S>> {
  void open(S entity, U menu);

  ComponentBinder<T> getComponentBinder();
}
