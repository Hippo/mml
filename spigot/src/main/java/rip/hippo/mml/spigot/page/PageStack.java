package rip.hippo.mml.spigot.page;

import rip.hippo.mml.spigot.util.MenuData;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * @author Hippo
 */
public final class PageStack<T> {

  private final String component;
  private final List<T> mirrorList;
  private final Stack<IndexedMenu> pages;
  private final boolean locked;

  public PageStack(String component, List<T> mirrorList) {
    this.component = component;
    this.mirrorList = mirrorList;
    this.locked = mirrorList.isEmpty();
    this.pages = locked ? null : new Stack<>();
  }

  public void acceptNext(MenuData menuData) {
    if (locked) {
      return;
    }
    pages.push(new IndexedMenu(component, menuData, getIndexedElements()));
  }

  public void acceptCopy() {
    if (pages.isEmpty()) {
      return;
    }
    IndexedMenu last = pages.peek();
    acceptNext(last.getMenuData().copy());
  }

  public Optional<T> getElement(int slot) {
    int index = getIndex(slot);
    if (index < 0 || index >= mirrorList.size()) {
      return Optional.empty();
    }
    return Optional.of(mirrorList.get(index));
  }

  public int getIndex(int slot) {
    if (locked) {
      return -1;
    }
    IndexedMenu current = pages.peek();
    if (!current.hasMapping(slot)) {
      return -1;
    }
    return current.getIndex(slot);
  }

  public IndexedMenu back() {
    if (locked) {
      return null;
    }
    if (hasBack()) {
      pages.pop();
    }
    return pages.peek();
  }

  public boolean hasBack() {
    return !locked && pages.size() > 1;
  }

  public boolean hasNext() {
    return !locked && getIndexedElements() < mirrorList.size();
  }



  private int getIndexedElements() {
    if (pages.isEmpty()) {
      return 0;
    }

    int offset = 0;
    for (IndexedMenu page : pages) {
      offset += page.getSize();
    }
    return offset;
  }
}
