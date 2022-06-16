package rip.hippo.mml.component;

/**
 * @param <T> Entity type
 *
 * @author Hippo
 */
@FunctionalInterface
public interface ComponentPayload<T> {
  T getEntity();
}
