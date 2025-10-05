package ntnu.iir.bidata.martinbf.logic.decoderencoder;

/**
 * A generic parser interface for parsing byte data into objects of type T.
 *
 * @param <T> The type of objects to parse the data into.
 */
public interface Decoder<T> {

  /**
   * Parses the given byte data into an array of objects of type T.
   *
   * @param data The byte data to parse.
   * @return An array of objects of type T.
   */
  T[] decode(byte[] data) throws CorruptDataException;
}
