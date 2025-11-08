package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.logic.TVMessage;
import ntnu.iir.bidata.martinbf.logic.iodata.DataSender;
import ntnu.iir.bidata.martinbf.logic.iodata.RemoteCommandSender;

/**
 * Is responsible for handling user input, when sending tv commands.
 */
public class RemoteCommandController {
  private final DataSender<TVMessage> sender;

  /**
   * Instantiates a RemoteCommandController with a sender that sends the commands.
   *
   * @param sender the sender that sends the command.
   */
  public RemoteCommandController(DataSender<TVMessage> sender) {
    if (sender == null) {
      throw new IllegalArgumentException("Sender is null");
    }
    this.sender = sender;
  }

  /**
   * Sends a Power message.
   */
  public void sendPower() {
    this.sender.sendData(TVMessage.POWER);
  }

  /**
   * Sends a channel_up message.
   */
  public void sendChannelUp() {
    this.sender.sendData(TVMessage.CHANNEL_UP);
  }

  /**
   * Sends a channel_down message.
   */
  public void sendChannelDown() {
    this.sender.sendData(TVMessage.CHANNEL_DOWN);
  }
}
