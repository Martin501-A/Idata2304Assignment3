package ntnu.iir.bidata.martinbf.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a remote for a television.
 * The remote holds knows the current TV channel.
 */
public class Remote {
  private Channel currentChannel;
  private List<RemoteSubscriber> subscribers;

  /**
   * Instantiates a new Remote.
   */
  public Remote() {
    setCurrentChannel(Channel.NONE);
    subscribers = new ArrayList<>();
  }

  /**
   * Sets the current channel.
   *
   * @param channel the new current channel
   */
  public void setCurrentChannel(Channel channel) {
    if (channel == null) {
      throw new IllegalArgumentException("Channel cannot be null");
    }
    this.currentChannel = channel;
  }

  /**
   * Gets the current channel.
   *
   * @return the current channel
   */
  public Channel getCurrentChannel() {
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
}

