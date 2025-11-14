package ntnu.iir.bidata.martinbf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a remote for a television.
 * The remote holds knows the current TV channel.
 */
public class Remote {
  private String currentChannel;
  private final List<RemoteSubscriber> subscribers;

  /**
   * Instantiates a new Remote.
   */
  public Remote() {
    this.currentChannel = Channel.NONE.toString();
    subscribers = new ArrayList<>();
  }

  /**
   * Sets the current channel.
   *
   * @param channel the new current channel
   */
  public void setCurrentChannel(String channel) {
    if (channel == null) {
      throw new IllegalArgumentException("Channel cannot be null");
    }
    this.currentChannel = channel;
    updateSubscribers();
  }

  /**
   * Gets the current channel.
   *
   * @return the current channel
   */
  public String getCurrentChannel() {
    return this.currentChannel;
  }

  /**
   * Subscribes a RemoteSubscriber to the remote.
   */
  public void subscribe(RemoteSubscriber subscriber) {
    if (subscriber == null) {
      throw new IllegalArgumentException("Subscriber cannot be null");
    }
    this.subscribers.add(subscriber);
  }

  /**
   * Updates Subscribers.
   */
  public void updateSubscribers() {
    for (RemoteSubscriber subscriber: subscribers) {
      subscriber.update();
    }
  }
}

