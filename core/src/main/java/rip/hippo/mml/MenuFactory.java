package rip.hippo.mml;

import rip.hippo.mml.impl.StandardMenuApplicator;

import java.util.List;
import java.util.Map;

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
                    Map<String, Object> metaDataMap);
}
