package rip.hippo.mml.testing.component;

import rip.hippo.mml.component.impl.DisplayComponent;

/**
 * @author Hippo
 */
public final class MockDisplay implements DisplayComponent<String> {

  private final String message;

  public MockDisplay(String message) {
    this.message = message;
  }

  @Override
  public String get() {
    return message;
  }
}
