package ntnu.iir.bidata.martinbf.logic.services;

/**
 * Handles a message for a Recipient.
 */
public interface MessageHandler {


  /**
   * Handles the message and returns responses.
   */
  byte[] handleMessage(byte[] receivedData);
}
