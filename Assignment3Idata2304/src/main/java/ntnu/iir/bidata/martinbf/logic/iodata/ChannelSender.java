package ntnu.iir.bidata.martinbf.logic.iodata;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.entity.TVSubscriber;

/**
 * Is responsible for sending a channel when TV state is updated.
 */
public class ChannelSender implements DataSender<Channel>, TVSubscriber {
  private final TV tv;
  private final DataBroadcaster broadcaster;

  /**
   * Creates a channelSender with a tv and broadcaster.
   * @param tv
   * @param broadcaster
   */
  public ChannelSender(TV tv, DataBroadcaster broadcaster) {
    if  (broadcaster == null) {
      throw new IllegalArgumentException("DataBroadcaster cannot be null");
    }
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.broadcaster = broadcaster;
    this.tv = tv;
    tv.subscribe(this);
  }

  @Override
  public void update() {
    sendData(tv.getCurrentChannel());
  }

  @Override
  public void sendData(Channel data) {
    byte[] encodedData = data.toString().getBytes();
    broadcaster.broadcast(encodedData);
  }
}
