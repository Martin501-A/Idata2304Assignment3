package ntnu.iir.bidata.martinbf.logic.iodata;

import ntnu.iir.bidata.martinbf.logic.TVMessage;

/**
 * Represents a sender of remote commands to the television.
 * This class also encodes the message.
 */
public class RemoteCommandSender implements DataSender<TVMessage> {
  private DataBroadcaster broadcaster;

  /**
   * Instantiates the sender with a broadcaster.
   *
   * @param broadcaster the broadcaster which handles the sending of the data.
   */
  public RemoteCommandSender(DataBroadcaster broadcaster) {
    if (broadcaster==null) {
      throw new IllegalArgumentException("broadcaster is null");
    }
    this.broadcaster = broadcaster;
  }

  /**
   * Encodes and sends data.
   *
   * @param data the data to encode and send.
   */
  @Override
  public void sendData(TVMessage data) {
    if (data == null) {
      throw new IllegalArgumentException("data is null");
    }
    byte[] dataToSend = new byte[1];
    switch (data) {
      case POWER:
        dataToSend[0] = 1;
        break;
      case CHANNEL_UP:
        dataToSend[0] = 2;
        break;
      case CHANNEL_DOWN:
        dataToSend[0] = 3;
        break;
    }
    if (dataToSend[0] >= 1) {
      broadcaster.broadcast(dataToSend);
    }
  }

}
