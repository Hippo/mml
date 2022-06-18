package rip.hippo.mml.testing;

import rip.hippo.mml.Menu;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.impl.StandardMenuBus;
import rip.hippo.mml.testing.component.MockPayload;

import java.util.Objects;

/**
 * @author Hippo
 */
public final class MockGame {

  private final String entity;
  private final StandardMenuBus<MockPayload, MockGame> menuBus;
  private final ComponentBinder<String> componentBinder;

  private Menu<String, MockGame> currentMenu;

  public MockGame(String entity, StandardMenuBus<MockPayload, MockGame> menuBus, ComponentBinder<String> componentBinder) {
    this.entity = entity;
    this.menuBus = menuBus;
    this.componentBinder = componentBinder;
  }

  public void open(Menu<String, MockGame> menu) {
    this.currentMenu = menu;
  }

  public void close() {
    this.currentMenu = null;
  }

  public void interact(int slot, String metaData) {
    Objects.requireNonNull(currentMenu);

    MockPayload mockPayload = new MockPayload(entity, slot, false, metaData);
    menuBus.message(componentBinder, currentMenu, mockPayload);
  }

  public void view(int slot) {
    Objects.requireNonNull(currentMenu);

    MockPayload mockPayload = new MockPayload(entity, slot, true, null);
    menuBus.message(componentBinder, currentMenu, mockPayload);
  }

}
