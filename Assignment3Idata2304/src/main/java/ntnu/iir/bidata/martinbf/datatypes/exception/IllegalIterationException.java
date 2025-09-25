package ntnu.iir.bidata.martinbf.datatypes.exception;

/**
 * Exception thrown when an illegal iteration is attempted.
 */
public class IllegalIterationException extends RuntimeException {
  public IllegalIterationException(String message) {
    super(message);
  }
}
