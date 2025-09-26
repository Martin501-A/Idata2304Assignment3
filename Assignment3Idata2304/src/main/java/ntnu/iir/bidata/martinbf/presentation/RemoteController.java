package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.RemoteClient;

/**
 * Handles User Input and sends commands to the TV.
 */
public class RemoteController {
  private RemoteApp app;
  private RemoteClient remote;

  public RemoteController(RemoteApp app, RemoteClient remote) {
    if (app == null) {
      throw new IllegalArgumentException("App cannot be null");
    }
    if (remote == null) {
      throw new IllegalArgumentException("Remote cannot be null");
    }
    this.app = app;
    this.remote = remote;
  }

  /**
   * Tells the TV to toggle power.
   */
  public void power() {
    this.remote.protocol(Command.POWER);
    this.app.update();
  }

  /**
   * Tells the TV to go to the next channel.
   */  public void channelUp() {
    this.remote.protocol(Command.CHANNEL_UP);
    this.app.update();
  }

  /**
   * Tells the TV to go to the previous channel.
   */
  public void channelDown() {
    this.remote.protocol(Command.CHANNEL_DOWN);
    this.app.update();
  }


}
