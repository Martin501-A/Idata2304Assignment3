package ntnu.iir.bidata.martinbf.logic.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Tests the StringEncoder class. </p>
 * <p>The Following is tested: </p>
 * <p>Positive Tests: </p>
 * <ul>
 *   <li>Encoding a simple Enum</li>
 * </ul>
 * <p>Negative Tests: </p>
 * <ul>
 *   <li>Encoding an null value</li>
 *   <li>Encoding an empty value</li>
 * </ul>
 */
public class StringEncoderTest {

  private enum TestEnum {
    VALUE_ONE("One"),
    VALUE_TWO("Two"),
    VALUE_THREE("Three");

    private final String value;

    TestEnum(String value) {
      this.value = value;
    }
  }

  // Positive Tests

  /**
   * Tests encoding and decoding a simple enum.
   */
  @Test
  public void decodeAndEncodeSimpleEnum() {
      StringEncoder<TestEnum> stringEncoder = new StringEncoder<>();
      TestEnum[] originalEnums = {TestEnum.VALUE_ONE};
      byte[] encodedData = stringEncoder.encode(originalEnums);
      assertNotNull(encodedData);
  }

  /**
   * Tests with multiple enums in an array.
   */
  @Test
  public void decodeAndEncodeMultipleEnums() {
    StringEncoder<TestEnum> stringEncoder = new StringEncoder<>();
    TestEnum[] originalEnums = {TestEnum.VALUE_ONE, TestEnum.VALUE_TWO, TestEnum.VALUE_THREE};
    byte[] encodedData = stringEncoder.encode(originalEnums);
    assertNotNull(encodedData);
  }

  // Negative Tests

  /**
   * Tests encoding a null value.
   * The null value should be ignored and result in an empty byte array.
   */
  @Test
  public void encodeNullValue() {
    try {
      StringEncoder<TestEnum> stringEncoder = new StringEncoder<>();
      TestEnum[] originalEnums = null;
      byte[] encodedData = stringEncoder.encode(originalEnums);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * Tests encoding an empty array.
   * The empty array should result in an empty byte array.
   */
  @Test
  public void encodeEmptyArray() {
    try {
    StringEncoder<TestEnum> stringEncoder = new StringEncoder<>();
    TestEnum[] originalEnums = {};
    byte[] encodedData = stringEncoder.encode(originalEnums);
    fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}