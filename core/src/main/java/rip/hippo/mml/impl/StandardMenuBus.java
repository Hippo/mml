package rip.hippo.mml.impl;

import rip.hippo.mml.Menu;
import rip.hippo.mml.MenuBus;

/**
 * @param <T> Payload type
 * @param <S> Entity type
 *
 * @author Hippo
 */
@FunctionalInterface
public interface StandardMenuBus<T, S> extends MenuBus<String, S, Menu<String, S>, T> {
}
