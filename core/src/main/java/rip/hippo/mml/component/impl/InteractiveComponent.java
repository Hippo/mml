package rip.hippo.mml.component.impl;

import rip.hippo.mml.component.ComponentAttribute;
import rip.hippo.mml.component.ComponentPayload;

/**
 * @param <T> Interactive entity
 * @param <S> Interactive payload
 *
 * @author Hippo
 */
@FunctionalInterface
public interface InteractiveComponent<T, S extends ComponentPayload<T>> extends ComponentAttribute {
  void onInteract(S payload);
}
