package ntnu.iir.bidata.martinbf.entity.entities;

/**
 * Represents a remote for a television.
 * The remote holds knows the current TV channel.
 */
public class Remote {
  private Channel currentChannel;

  /**
   * Instantiates a new Remote.
   */
  public Remote() {
    setCurrentChannel(Channel.NONE);
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
}

