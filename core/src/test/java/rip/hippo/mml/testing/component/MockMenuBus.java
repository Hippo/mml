package rip.hippo.mml.testing.component;

import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.impl.StandardMenuBus;
import rip.hippo.mml.testing.MockGame;

import java.util.Optional;

/**
 * @author Hippo
 */
public final class MockMenuBus implements StandardMenuBus<MockPayload, MockGame> {

  @Override
  public void message(ComponentBinder<String> componentBinder, Menu<String, MockGame> menu, MockPayload payload) {
    int slot = payload.getSlot();
    Optional<String> optionalBind = menu.get(slot);
    if (!optionalBind.isPresent()) {
      return;
    }
    String binder = optionalBind.get();

    if (payload.isView()) {
      Optional<MockDisplay> optionalDisplay = componentBinder.getComponent(binder, MockDisplay.class);
      if (optionalDisplay.isPresent()) {
        MockDisplay mockDisplay = optionalDisplay.get();
        System.out.println(payload.getEntity() + " found a message at slot " + payload.getSlot() + ": " + mockDisplay.get(payload.getEntity(), menu));
      } else {
        System.out.println(payload.getEntity() + " Did not find a message at slot " + payload.getSlot());
      }
    } else {
      componentBinder.getComponent(binder, MockInteract.class)
          .ifPresent(mockInteract -> mockInteract.onInteract(payload));
    }
  }
}
