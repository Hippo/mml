# MML
Minecraft Menu Library, making Minecraft menus without a hassle.


# Adding MML to your project

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("rip.hippo.mml:core:1.0.6")
    
    // add if spigot plugin
    implementation("rip.hippo.mml:spigot:1.0.6")
}
```

# Introduction
MML is a library which takes a better approach to making menus in Minecraft.

MML abstracts the functionality of the menu to its `ComponentBinder`, which binds component attributes to a binding object, normally a `String`.

There is 2 attributes `Interact` and `Display`, a binding object can have one or both attributes.

Interact attributes have a callback which is executed once the desired slot is interacted with.

Display attributes will return a display (item) object.

This allows us to easily represent the entire gui contents and functionality by a `List<T>` (`T` being our binding object).
The size of the list will determine the amount of rows the gui has.

So, if our binding object is a `String` we can simply represent a gui as such:

```
x x interact x both x display x x
```

In this example `x` would hold no attributes, `interact` will have an interact attribute, `display` will have a display, and `both` will have both.

# Spigot integration
MML has a very nice spigot implementation, using the `MenuLibrary` class.

In this example we will implement our example representation written above.

Therefore, lets define this list for simplicity.

```java
List<String> menuContents = Collections.singletonList("x x interact x both x display x x");
```

Firstly, we need to make a `MenuLibrary` instance and install it on our plugin.

```java
private final MenuLibrary menuLibrary = new MenuLibrary();

@Override
public void onEnable() {
  menuLibrary.install(this);
}
```

Creating bindings goes as such:

```java
menuLibrary.bind("x", ClickInteract.DENY); // deny click event for empty slots

menuLibrary.bind("interact", new ClickInteract((player, payload) -> {
  player.sendMessage("interact!");
  return true; // return true to cancel the click event
}));

menuLibrary.bind("display", new ItemDisplay(new ItemStack(Material.STONE)));

menuLibrary.bind("both", new ClickInteract((player, payload) -> {
    player.sendMessage("interact on both!");
    return true;
}), new ItemDisplay(new ItemStack(Material.DIRT)));
```

Now we need to create our menu to display to players.

```java
Player player = ...;

// First we need to get our menu applicator
StandardMenuApplicator<Player> applicator = menuLibrary.getApplicator();

// Make our menu instance
StandardMenu<Player> menu = menuLibrary.create(applicator, "&cMenu Title", menuContents);

// Now we can open the menu
applicator.open(player, menu);
```

### More Information on Spigot Integration

MML has a `DecorComponentBinder`, which tries to bind decor items to our menus contents via the items damage value.

Let's say we wanted to have a multitude of stained-glass panes instead of no items in our example gui.
We can define our list as this now.

```java
List<String> menuContents = Collections.singletonList("14 11 interact 4 both 4 display 11 14");
```

Then the only change we would need to make from before is with our applicator.

```java
StandardMenuApplicator<Player> applicator = menuLibrary.getApplicator(Material.STAINED_GLASS_PANE);
```

MML includes an `ItemStackBuilder` which can be used as such:

```java
ItemStack itemStack = new ItemStackBuilder()
    .name("&cName")
    .material(Material.WOOL)
    .addLoreLine("&7Lore line here")
    .damage(14)
    .build();
```

It can also load from a `ConfigurationSection` as such.

```yaml
# The only thing required is the material attribute
my-item:
  name: "&cMy Item"
  material: WOOL
  amount: 5
  damage: 14
  lore:
    - "&7Lore line 1"
    - "&dSecond line"
```

Load at like this.
```java
ItemStackBuilder itemStackBuilder = ItemStackBuilder.of(configurationSection);
```

Check out `spigot-example` for a whole integration.