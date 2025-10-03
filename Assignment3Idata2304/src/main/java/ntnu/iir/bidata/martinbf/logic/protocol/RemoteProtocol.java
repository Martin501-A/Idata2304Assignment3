package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

/**
 * Represents a protocol for controlling a TV remote.
 */
public class RemoteProtocol implements ClientProtocol {

  @Override
  public String getFirstMessage() {
    return "";
  }

  @Override
  public void processData(byte[] data) {

  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public boolean hasFailed() {
    return false;
  }

  @Override
  public void fail() {

  }

  @Override
  public void reset() {

  }

  @Override
  public byte[] finish() throws IllegalFinishException {
    return new byte[0];
  }
}
