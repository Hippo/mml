package rip.hippo.mml.testing;

import org.junit.jupiter.api.Test;
import rip.hippo.mml.Menu;
import rip.hippo.mml.MenuFactory;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.component.impl.binder.StandardComponentBinder;
import rip.hippo.mml.impl.StandardMenuApplicator;
import rip.hippo.mml.impl.StandardMenuFactory;
import rip.hippo.mml.testing.component.MockDisplay;
import rip.hippo.mml.testing.component.MockInteract;
import rip.hippo.mml.testing.component.MockMenuBus;

import java.util.Collections;
import java.util.List;

/**
 * @author Hippo
 */
public final class MockTest {

  @Test
  public void test() {
    ComponentBinder<String> componentBinder = new StandardComponentBinder();
    StandardMenuApplicator<MockGame> menuApplicator = new MockMenuApplicator(componentBinder);
    MenuFactory<String, MockGame> menuFactory = new StandardMenuFactory<>();


    componentBinder.bind("i", new MockInteract());
    componentBinder.bind("b", new MockInteract(), new MockDisplay("foo"));
    componentBinder.bind("v", new MockDisplay("bar"));

    String menuName = "My Menu";
    /*
    i = interact component (index = 2)
    b = interact & view component (index = 4)
    v = view component (index = 6)
     */
    List<String> menuData = Collections.singletonList("x x i x b x v x x");

    Menu<String, MockGame> menu = menuFactory.create(menuApplicator,
        menuName,
        menuData,
        false,
        false);

    MockGame mockGame = new MockGame("My Entity", new MockMenuBus(), componentBinder);

    menuApplicator.open(mockGame, menu);

    mockGame.interact(2, "interact at slot 2");
    System.out.println();

    mockGame.view(4);
    mockGame.interact(4, "interact at slot 4");
    System.out.println();

    mockGame.view(6);

    System.out.println();
    System.out.println("no component test: ");
    mockGame.interact(1, "interact with none");
    mockGame.view(1);

    System.out.println();
    System.out.println("out of bound test: ");
    mockGame.interact(999, "out of bounds");
    mockGame.view(999);
  }
}
