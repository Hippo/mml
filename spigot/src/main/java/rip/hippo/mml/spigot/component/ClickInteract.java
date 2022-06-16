package rip.hippo.mml.spigot.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import rip.hippo.mml.component.impl.InteractiveComponent;

import java.util.function.BiFunction;

/**
 * @author Hippo
 */
public final class ClickInteract implements InteractiveComponent<HumanEntity, SpigotPayload> {

  public static final ClickInteract DENY = new ClickInteract((player, payload) -> true);

  private final BiFunction<Player, SpigotPayload, Boolean> clickAction;

  public ClickInteract(BiFunction<Player, SpigotPayload, Boolean> clickAction) {
    this.clickAction = clickAction;
  }

  @Override
  public void onInteract(SpigotPayload payload) {
    HumanEntity entity = payload.getEntity();
    if (entity instanceof Player) {
      payload.getInventoryClickEvent().setCancelled(clickAction.apply((Player) entity, payload));
    }
  }
}
