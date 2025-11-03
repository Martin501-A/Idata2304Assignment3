package ntnu.iir.bidata.martinbf.logic.iodata;

/**
 * Represents a sender of data.
 * The sender will Encode the data.
 */
public interface DataSender<T> {

  /**
   * Sends data.
   *
   * @param data the data to send.
   */
  void sendData(T data);
}
