package ntnu.iir.bidata.martinbf.legacy.logic;

/**
 * Exception thrown when corrupt data is encountered during decoding.
 */
public class CorruptDataException extends Exception {
  public CorruptDataException(String message) {
    super(message);
  }
}
