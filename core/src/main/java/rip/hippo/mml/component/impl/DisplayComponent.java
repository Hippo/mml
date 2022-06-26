package rip.hippo.mml.component.impl;

import rip.hippo.mml.component.ComponentAttribute;

/**
 * @param <T> Display object
 * @param <S> Entity type
 * @param <U> Display payload
 *
 * @author Hippo
 */
@FunctionalInterface
public interface DisplayComponent<T, S, U> extends ComponentAttribute {
  T get(S entity, U payload);
}
