package rip.hippo.mml.spigot.component;

import org.bukkit.Material;
import rip.hippo.mml.component.ComponentAttribute;
import rip.hippo.mml.component.ComponentBinder;
import rip.hippo.mml.component.impl.binder.StandardComponentBinder;

import java.util.Optional;

/**
 * The decor binder takes in a material, if they request a number (as string value)
 * then they will be given an {@link ItemDisplay} in decor form, the number will be the items damage.
 *
 * @author Hippo
 */
public final class DecorComponentBinder implements ComponentBinder<String> {

  private final Material material;
  private final ComponentBinder<String> componentBinder;

  public DecorComponentBinder(Material material, ComponentBinder<String> componentBinder) {
    this.material = material;
    this.componentBinder = componentBinder;
  }

  @Override
  public void bind(String binder, ComponentAttribute... componentAttributes) {
    componentBinder.bind(binder, componentAttributes);
  }

  @Override
  public <S extends ComponentAttribute> Optional<S> getComponent(String binder, Class<S> parent) {
    try {
      int damage = Integer.parseInt(binder);
      if (parent.equals(ItemDisplay.class)) {
        return Optional.of(parent.cast(new ItemDisplay(material, damage)));
      } else {
        return Optional.of(parent.cast(ClickInteract.DENY));
      }
    } catch (Exception ignored) {}
    return componentBinder.getComponent(binder, parent);
  }
}
