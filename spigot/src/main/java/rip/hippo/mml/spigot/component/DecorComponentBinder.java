package rip.hippo.mml.spigot.component;

import org.bukkit.Material;
import rip.hippo.mml.component.ComponentAttribute;
import rip.hippo.mml.component.ComponentBinder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The decor binder takes in a material, if they request a number (as string value)
 * then they will be given an {@link ItemDisplay} in decor form, the number will be the items damage.
 *
 * @author Hippo
 */
public final class DecorComponentBinder implements ComponentBinder<String> {

  private static final Map<String, Integer> LEGACY_COLOR_DAMAGE = new HashMap<>();


  private final String material;
  private final ComponentBinder<String> componentBinder;


  public DecorComponentBinder(Material material, ComponentBinder<String> componentBinder) {
    this(material.name(), componentBinder);
  }

  public DecorComponentBinder(String material, ComponentBinder<String> componentBinder) {
    this.material = material;
    this.componentBinder = componentBinder;
  }

  @Override
  public void bind(String binder, ComponentAttribute... componentAttributes) {
    componentBinder.bind(binder, componentAttributes);
  }

  @Override
  public <S extends ComponentAttribute> Optional<S> getComponent(String binder, Class<S> parent) {
    Optional<S> component = componentBinder.getComponent(binder, parent);

    if (component.isPresent()) {
      return component;
    }

    try {
      String modernMaterialName = binder.toUpperCase() + "_" + material;
      Material modernMaterial = Objects.requireNonNull(Material.valueOf(modernMaterialName));
      if (parent.equals(ItemDisplay.class)) {
        return Optional.of(parent.cast(new ItemDisplay(modernMaterial)));
      } else {
        return Optional.of(parent.cast(ClickInteract.DENY));
      }
    } catch (Exception e) {
      try {
        int damage = LEGACY_COLOR_DAMAGE.containsKey(binder.toUpperCase()) ?
            LEGACY_COLOR_DAMAGE.get(binder.toUpperCase()) : Integer.parseInt(binder);

        if (parent.equals(ItemDisplay.class)) {
          return Optional.of(parent.cast(new ItemDisplay(Objects.requireNonNull(Material.valueOf(material)), damage)));
        } else {
          return Optional.of(parent.cast(ClickInteract.DENY));
        }
      } catch (Exception ignored) {}
    }

    return Optional.empty();
  }

  static {
    LEGACY_COLOR_DAMAGE.put("WHITE", 0);
    LEGACY_COLOR_DAMAGE.put("ORANGE", 1);
    LEGACY_COLOR_DAMAGE.put("MAGENTA", 2);
    LEGACY_COLOR_DAMAGE.put("LIGHT_BLUE", 3);
    LEGACY_COLOR_DAMAGE.put("YELLOW", 4);
    LEGACY_COLOR_DAMAGE.put("LIME", 5);
    LEGACY_COLOR_DAMAGE.put("PINK", 6);
    LEGACY_COLOR_DAMAGE.put("GRAY", 7);
    LEGACY_COLOR_DAMAGE.put("LIGHT_GRAY", 8);
    LEGACY_COLOR_DAMAGE.put("CYAN", 9);
    LEGACY_COLOR_DAMAGE.put("PURPLE", 10);
    LEGACY_COLOR_DAMAGE.put("BLUE", 11);
    LEGACY_COLOR_DAMAGE.put("BROWN", 12);
    LEGACY_COLOR_DAMAGE.put("GREEN", 13);
    LEGACY_COLOR_DAMAGE.put("RED", 14);
    LEGACY_COLOR_DAMAGE.put("BLACK", 15);
  }
}
