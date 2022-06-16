package rip.hippo.mml.component;

import java.util.Optional;

/**
 * @param <T> Binding object
 *
 * @author Hippo
 */
public interface ComponentBinder<T> {
  void bind(T binder, ComponentAttribute... componentAttributes);
  <S extends ComponentAttribute> Optional<S> getComponent(T binder, Class<S> parent);
}
