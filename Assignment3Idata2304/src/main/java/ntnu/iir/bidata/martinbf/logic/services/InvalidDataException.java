package ntnu.iir.bidata.martinbf.logic.services;

/**
 * This means that an invalid message was sent to a recipient.
 * Being Invalid means that the recipient is not able to handle
 */
public class InvalidDataException extends RuntimeException {
  public InvalidDataException(String message) {
    super(message);
  }
}
