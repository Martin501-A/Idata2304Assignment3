package ntnu.iir.bidata.martinbf.logic.protocol.exception;

/**
 * Exception thrown when an illegal finish operation is attempted in a protocol.
 */
public class IllegalFinishException extends Exception {

  /**
   * Constructs a new IllegalFinishException with the specified detail message.
   *
   * @param message The detail message.
   */
  public IllegalFinishException(String message) {
    super(message);
  }
}
