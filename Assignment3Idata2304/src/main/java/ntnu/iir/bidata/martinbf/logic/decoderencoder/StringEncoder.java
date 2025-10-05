package ntnu.iir.bidata.martinbf.logic.decoderencoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Encodes and decodes data enums through strings with UTF-8.
 *
 * @param <D> The type of data to decode to.
 * @param <E> The type of data to encode from.
 */
public class StringEncoder<D extends Enum<D>, E extends Enum<E>> implements Encoder<E>, Decoder<D> {
  private final Charset charSet;
  private final Class<D> decodeClass;

  /**
   * Creates a new StringEncoder.
   */
  public StringEncoder(Class<D> decodeClass) {
    this.charSet = StandardCharsets.UTF_8;
    this.decodeClass = decodeClass;
  }

  /**
   * Encodes the given data into a byte array.
   *
   * @param data The data to encode.
   * @return A byte array representing the encoded data.
   */
  @Override
  public byte[] encode(E[] data) {
    List<String> stringList = new ArrayList<>();
    for (E item : data) {
      if (item != null) {
        stringList.add(item.name());
      }
    }
    String resultString = String.join(" ", stringList);
    return resultString.getBytes(charSet);
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
        if (stringArray[i] != null) {
          resultArray[i] = Enum.valueOf(decodeClass, stringArray[i]);
        }
      }
      return resultArray;
    } catch (IllegalArgumentException e) {
      throw new CorruptDataException("Decoding failed: Invalid enum value");
    }
  }
}
