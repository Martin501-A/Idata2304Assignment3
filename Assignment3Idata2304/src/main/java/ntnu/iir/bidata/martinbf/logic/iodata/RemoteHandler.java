package ntnu.iir.bidata.martinbf.logic.iodata;

import ntnu.iir.bidata.martinbf.entity.Remote;

/**
 * Represents a receiver of data from the television to the remote.
 */
public class RemoteHandler implements DataHandler {
  private final Remote remote;

  public RemoteHandler(Remote remote) {
    if (remote == null) {
      throw new IllegalArgumentException("Remote object cannot be null");
    }
    this.remote = remote;
  }

  @Override
  public void handleReceivedData(byte[] data) {
    this.remote.setCurrentChannel(new String(data));
  }
}
