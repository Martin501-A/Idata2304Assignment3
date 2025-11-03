package ntnu.iir.bidata.martinbf.logic.iodata;

/**
 * Broadcasts data to all connections.
 */
public interface DataBroadcaster {

  /**
   * Broadcast data.
   *
   * @param data the data to braodcast.
   */
  void broadcast(byte[] data);
}
