package ntnu.iir.bidata.martinbf.logic.services.encoder;

/**
 * A generic encoder interface.
 */
public interface Encoder<T> {

  /**
   * Encodes the given array of objects of type T into a byte array.
   *
   * @param data The array of objects to encode.
   * @return A byte array representing the encoded data.
   */
  byte[] encode(T[] data);
}
