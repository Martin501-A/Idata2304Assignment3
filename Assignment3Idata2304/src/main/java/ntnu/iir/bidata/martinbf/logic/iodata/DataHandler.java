package ntnu.iir.bidata.martinbf.logic.iodata;

/**
 * Represents a receiver of data from a UDP/TCP connection that handles it.
 */
public interface DataHandler {

  /**
   * Handles received data.
   *
   * @param data the received data to handle.
   */
  void handleReceivedData(byte[] data);
}
