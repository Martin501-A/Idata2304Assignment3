package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Decodes Strings into StringEnums.
 *
 * @param <D> The type of data to Decode.
 */
public class StringEnumDecoder<D extends Enum<D>> implements Decoder<String,D> {
  private final Charset charSet;
  private final Class<D> decodeClass;

  /**
   * Creates a new StringEncoder.
   */
  public StringEnumDecoder(Class<D> decodeClass, Charset charSet) {
    if (decodeClass == null) {
      throw new IllegalArgumentException("Decode class cannot be null");
    }
    if (charSet == null) {
      throw new IllegalArgumentException("Charset cannot be null");
    }
    this.charSet = charSet;
    this.decodeClass = decodeClass;
  }

  /**
   * Decodes the given byte data into an array of objects of the enum type.
   *
   * @param data The byte data to decode.
   * @return An array of objects of type D.
   */
  //TODO if problem arises look here when it comes to Decoding
  @Override
  public D decode(String data) throws CorruptDataException {
    throw new IllegalCallerException("Function not implemented");
  }
}
