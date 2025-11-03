package ntnu.iir.bidata.martinbf.legacy.logic;

/**
 * Handles a message for a Recipient.
 */
public interface MessageHandler {


  /**
   * Handles the message and returns responses.
   */
  byte[] handleMessage(byte[] receivedData);
}
