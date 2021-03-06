package rip.hippo.mml.testing.component;

import rip.hippo.mml.component.ComponentPayload;
import rip.hippo.mml.testing.MockGame;

/**
 * @author Hippo
 */
public final class MockPayload implements ComponentPayload<MockGame> {

  private final MockGame entity;
  private final int slot;
  private final boolean view;
  private final String metaData;

  public MockPayload(MockGame entity, int slot, boolean view, String metaData) {
    this.entity = entity;
    this.slot = slot;
    this.view = view;
    this.metaData = metaData;
  }



  @Override
  public MockGame getEntity() {
    return entity;
  }

  public int getSlot() {
    return slot;
  }

  public String getMetaData() {
    return metaData;
  }

  public boolean isView() {
    return view;
  }

  @Override
  public String toString() {
    return "MockPayload{" +
        "slot=" + slot +
        ", metaData='" + metaData + '\'' +
        '}';
  }
}
