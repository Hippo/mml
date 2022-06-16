package rip.hippo.mml;

import rip.hippo.mml.component.ComponentBinder;

/**
 * @param <T> Binding Type
 * @param <S> Entity type
 * @param <U> Menu type
 * @param <V> Payload type
 *
 * @author Hippo
 */
@FunctionalInterface
public interface MenuBus<T, S, U extends Menu<T, S>, V> {
  void message(ComponentBinder<T> componentBinder, U menu, V payload);
}
