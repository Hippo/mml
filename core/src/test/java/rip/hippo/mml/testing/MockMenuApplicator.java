package rip.hippo.mml.testing;

import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.impl.StandardMenuApplicator;

/**
 * @author Hippo
 */
public final class MockMenuApplicator implements StandardMenuApplicator<MockGame> {

  private final ComponentBinder<String> componentBinder;

  public MockMenuApplicator(ComponentBinder<String> componentBinder) {
    this.componentBinder = componentBinder;
  }

  @Override
  public void open(MockGame entity, Menu<String, MockGame> menu) {
    entity.open(menu);
  }

  @Override
  public ComponentBinder<String> getComponentBinder() {
    return componentBinder;
  }
}
