package rip.hippo.mml.component.impl;

import rip.hippo.mml.component.ComponentAttribute;

/**
 * @param <T> Display object
 *
 * @author Hippo
 */
@FunctionalInterface
public interface DisplayComponent<T> extends ComponentAttribute {
  T get();
}
