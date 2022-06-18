package rip.hippo.mml.testing.component;

import rip.hippo.mml.Menu;
import rip.hippo.mml.component.impl.DisplayComponent;
import rip.hippo.mml.impl.StandardMenu;
import rip.hippo.mml.testing.MockGame;

/**
 * @author Hippo
 */
public final class MockDisplay implements DisplayComponent<String, MockGame, String, Menu<String, MockGame>> {

  private final String message;

  public MockDisplay(String message) {
    this.message = message;
  }

  @Override
  public String get(MockGame entity, Menu<String, MockGame> menu) {
    return message;
  }
}
