package ntnu.iir.bidata.martinbf.logic.client.handler;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.Remote;

/**
 * Handles responses for the remote.
 */
public class RemoteHandler implements ResponseHandler {
  private Remote remote;

  /**
   * Creates a new RemoteHandler.
   */
  public RemoteHandler(Remote remote) {
    if (remote == null) {
      throw new IllegalArgumentException("Remote cannot be null");
    }
    this.remote = remote;
  }

  @Override
  public void handle(String response) throws Exception {
    if (response == null) {
      throw new Exception(); //TODO Make custom exception.
    }
    this.remote.setCurrentChannel(Channel.valueOf(response));
  }
}
