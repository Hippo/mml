package rip.hippo.mml.component.impl;

import rip.hippo.mml.component.ComponentAttribute;

/**
 * @param <T> Display object
 * @param <S> Entity type
 *
 * @author Hippo
 */
@FunctionalInterface
public interface DisplayComponent<T, S> extends ComponentAttribute {
  T get(S entity);
}
