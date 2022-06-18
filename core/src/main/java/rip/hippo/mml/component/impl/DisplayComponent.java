package rip.hippo.mml.component.impl;

import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentAttribute;

/**
 * @param <T> Display object
 * @param <S> Entity type
 * @param <U> Binding object
 * @param <V> Menu type
 *
 * @author Hippo
 */
@FunctionalInterface
public interface DisplayComponent<T, S, U, V extends Menu<U, S>> extends ComponentAttribute {
  T get(S entity, V menu);
}
