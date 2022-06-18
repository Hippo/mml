package rip.hippo.mml;

import rip.hippo.mml.impl.StandardMenuApplicator;

import java.util.List;

/**
 * @param <T> Binding object
 * @param <S> Entity type
 *
 * @author Hippo
 */
public interface MenuFactory<T, S> {
  Menu<T, S> create(StandardMenuApplicator<S> menuApplicator,
                    String title,
                    List<String> creationData,
                    boolean allowDragging,
                    boolean allowUnderInventoryInteract,
                    Object metaData);
}
