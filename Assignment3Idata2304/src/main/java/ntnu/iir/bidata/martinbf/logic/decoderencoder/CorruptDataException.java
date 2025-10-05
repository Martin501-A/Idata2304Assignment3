package ntnu.iir.bidata.martinbf.logic.decoderencoder;

/**
 * Exception thrown when corrupt data is encountered during decoding or encoding.
 */
public class CorruptDataException extends Exception {
  public CorruptDataException(String message) {
    super(message);
  }
}
