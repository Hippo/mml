package rip.hippo.mml.testing.component;

import rip.hippo.mml.component.impl.InteractiveComponent;

/**
 * @author Hippo
 */
public final class MockInteract implements InteractiveComponent<String, MockPayload> {

  @Override
  public void onInteract(MockPayload payload) {
    String entity = payload.getEntity();
    System.out.println(entity + " used payload: " + payload);
  }
}
