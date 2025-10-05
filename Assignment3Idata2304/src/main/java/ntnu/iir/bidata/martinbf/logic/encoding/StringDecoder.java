package ntnu.iir.bidata.martinbf.logic.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Decodes data enums through strings with UTF-8.
 * A data enum is represented as an enum that holds a String.
 *
 * @param <D> The type of data to Decode.
 */
public class StringDecoder<D extends Enum<D>> implements Decoder<D> {
  private final Charset charSet;
  private final Class<D> decodeClass;

  /**
   * Creates a new StringEncoder.
   */
  public StringDecoder(Class<D> decodeClass) {
    this.charSet = StandardCharsets.UTF_8;
    this.decodeClass = decodeClass;
  }

  /**
   * Decodes the given byte data into an array of objects of type.
   *
   * @param data The byte data to decode.
   * @return An array of objects of type D.
   */
  //TODO if problem arises look here when it comes to Decoding
  @SuppressWarnings("unchecked")
  @Override
  public D[] decode(byte[] data) throws CorruptDataException {
    try {
      String stringData = new String(data, charSet);
      String[] stringArray = stringData.split(" ");
      D[] resultArray = (D[]) java.lang.reflect.Array.newInstance(decodeClass, stringArray.length);
      for (int i = 0; i < stringArray.length; i++) {
          resultArray[i] = Enum.valueOf(decodeClass, stringArray[i]);
      }
      return resultArray;
    } catch (IllegalArgumentException e) {
      throw new CorruptDataException("Decoding failed: Invalid enum value");
    } catch (NullPointerException e) {
      throw new CorruptDataException("Decoding failed: Data is null" );
    }
  }
}
