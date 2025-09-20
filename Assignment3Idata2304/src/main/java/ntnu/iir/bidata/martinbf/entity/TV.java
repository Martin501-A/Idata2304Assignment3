package ntnu.iir.bidata.martinbf.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Television with channels and a power option.
 */
public class TV {
  private List<Channel> channels;
  private boolean powerStatus;
  private CircularIterator<Channel> channelIterator;



  /**
   * Create a TV with given channels.
   */
  public TV(List<Channel> channels) {
    this.powerStatus = false; // TV is initially off
    setChannels(channels);
  }

  /**
   * Set the channels for the TV. also sets the current channel to the first channel.
   */
  public void setChannels(List<Channel> channels) {
    if (channels == null) {
      throw new IllegalArgumentException("Channels cannot be null or empty");
    }
    if (channels.isEmpty()) {
      throw new IllegalArgumentException("Channels cannot be null or empty");
    }
    this.channels = new LinkedList<>();
    this.channels.addAll(channels);
    this.channelIterator = new CircularIterator<>(this.channels);
  }

  /**
   * Toggle the power status of the TV.
   */
  public void power() {
    this.powerStatus = !this.powerStatus;
  }

  /**
   * Change to the next channel. If at the end of the list, wrap around to the first channel.
   */
  public void nextChannel() {
    if (this.powerStatus) {
      channelIterator.next();
    }
  }

  /**
   * Change to the previous channel. If at the beginning of the list, wrap around to the last channel.
   */
  public void previousChannel() {
    if (this.powerStatus) {
      channelIterator.previous();
    }
  }

  /**
   * Get the current power status of the TV.
   */
  public boolean getPowerStatus() {
    return powerStatus;
  }

  /**
   * Get the list of channels available on the TV.
   */
  public List<Channel> getChannels() {
    return channels;
  }

  /**
   * Get the current channel being viewed.
   */
  public Channel getCurrentChannel() {
    if (powerStatus) {
      return channelIterator.current();
    }
    return Channel.NONE;
  }

}
