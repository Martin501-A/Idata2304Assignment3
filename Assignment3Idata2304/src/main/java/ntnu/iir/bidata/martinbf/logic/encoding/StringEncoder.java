package ntnu.iir.bidata.martinbf.logic.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Encodes data enums through strings with UTF-8.
 * A data enum is represented as a string of enum names separated by spaces.
 *
 * @param <E> The type of data to encode.
 */
public class StringEncoder<E extends Enum<E>> implements Encoder<E>{
  private final Charset charSet;

  /**
   * Creates a new StringEncoder.
   */
  public StringEncoder() {
    this.charSet = StandardCharsets.UTF_8;
  }

  /**
   * Encodes the given data into a byte array.
   *
   * @param data The data to encode.
   * @return A byte array representing the encoded data.
   */
  @Override
  public byte[] encode(E[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Data to encode cannot be null");
    }
    if (data.length == 0) {
      throw new IllegalArgumentException("Data to encode cannot be empty");
    }
    List<String> stringList = new ArrayList<>();
    for (E item : data) {
      if (item == null) {
        throw new IllegalArgumentException("Data to encode cannot contain null values");
      }
      stringList.add(item.name());
    }
    String resultString = String.join(" ", stringList);
    return resultString.getBytes(charSet);
  }

}
