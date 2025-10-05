package ntnu.iir.bidata.martinbf.logic.encoding;

/**
 * Exception thrown when corrupt data is encountered during decoding.
 */
public class CorruptDataException extends Exception {
  public CorruptDataException(String message) {
    super(message);
  }
}
