package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;

/**
 * Decodes a string into a StringEnums.
 *
 * @param <D> The type of data to Decode.
 */
public class StringEnumDecoder<D extends Enum<D>> implements Decoder<String,D> {
  private final Class<D>  enumType;

  /**
   * Creates a new StringEncoder.
   *
   * @param enumType the type this decoder decodes to.
   * @throws IllegalArgumentException if enumType is null.
   */
  public StringEnumDecoder(Class<D> enumType) {
    if (enumType == null) {
      throw new IllegalArgumentException("enumType cannot be null");
  }
    this.enumType = enumType;
  }

  /**
   * Decodes the given byte data into an array of objects of the enum type.
   *
   * @param data The data to transform into an enum.
   * @return The enum corresponding to the data.
   * @throws IllegalArgumentException if data is null or empty, or if data does not correspond to enum.
   */
  @Override
  public D decode(String data) throws CorruptDataException {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (data.isEmpty()) {
      throw new IllegalArgumentException("Data cannot be empty");
    }
    D enumValue = null;
    boolean found = false;
    D[] values = enumType.getEnumConstants();
    for (int index = 0; index < values.length && !found; index++) {
      if (values[index].toString().equals(data)) {
        found = true;
        enumValue = values[index];
      }
    }
    if (enumValue == null) {
      throw new CorruptDataException("Data was either sent wrong or corrupted during transfer");
    }
    return enumValue;
  }
}
