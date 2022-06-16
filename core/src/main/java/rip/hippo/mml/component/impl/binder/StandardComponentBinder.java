package rip.hippo.mml.component.impl.binder;

import rip.hippo.mml.component.ComponentAttribute;
import rip.hippo.mml.component.ComponentBinder;

import java.util.*;

/**
 * @author Hippo
 */
public final class StandardComponentBinder implements ComponentBinder<String> {

  private final Map<String, List<ComponentAttribute>> componentAttributeMap = new HashMap<>();

  @Override
  public void bind(String binder, ComponentAttribute... componentAttributes) {
    if (binder.contains(" ")) {
      throw new IllegalArgumentException("Binding name cannot contain a space!");
    }
    componentAttributeMap.computeIfAbsent(binder, ignored -> new LinkedList<>()).addAll(Arrays.asList(componentAttributes));
  }

  @Override
  public <S extends ComponentAttribute> Optional<S> getComponent(String binder, Class<S> parent) {

    List<ComponentAttribute> componentAttributes = componentAttributeMap.get(binder);
    if (componentAttributes == null || componentAttributes.isEmpty()) {
      return Optional.empty();
    }

    for (ComponentAttribute componentAttribute : componentAttributes) {
      if (parent.equals(componentAttribute.getClass())) {
        return Optional.of(parent.cast(componentAttribute));
      }
    }

    return Optional.empty();
  }
}
