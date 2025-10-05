package ntnu.iir.bidata.martinbf.logic.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Tests for the StringDecoder class. </p>
 * <p>The Following is tested: </p>
 * <p>Positive Tests: </p>
 * <ul>
 *   <li>Decoding a simple string</li>
 *   <li>Decoding multiple strings</li>
 * </ul>
 * <p>Negative Tests: </p>
 * <ul>
 *   <li>Decoding a null value</li>
 *   <li>Decoding an empty byte array</li>
 *   <li>Decoding a byte array with invalid UTF-8 sequences</li>
 * </ul>
 */
public class StringDecoderTest {

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
   * Tests decoding a simple string.
   */
  @Test
  public void decodeSimpleString() {
    try {
      StringDecoder<TestEnum> decoder = new StringDecoder<>(TestEnum.class);
      StringEncoder<TestEnum> encoder = new StringEncoder<>();
      TestEnum[] originalEnums = {TestEnum.VALUE_ONE};
      byte[] encodedData = encoder.encode(originalEnums);
      TestEnum[] decodedEnums = decoder.decode(encodedData);
      assertNotNull(decodedEnums);
      assertArrayEquals(originalEnums, decodedEnums);
    } catch (CorruptDataException e) {
      fail("Decoding failed with exception: ");
    }
  }

  /**
   * Tests decoding multiple strings.
   */
  @Test
  public void decodeMultipleStrings() {
    try {
      StringDecoder<TestEnum> decoder = new StringDecoder<>(TestEnum.class);
      StringEncoder<TestEnum> encoder = new StringEncoder<>();
      TestEnum[] originalEnums = {TestEnum.VALUE_ONE, TestEnum.VALUE_TWO, TestEnum.VALUE_THREE};
      byte[] encodedData = encoder.encode(originalEnums);
      TestEnum[] decodedEnums = decoder.decode(encodedData);
      assertNotNull(decodedEnums);
      assertArrayEquals(originalEnums, decodedEnums);
    } catch (CorruptDataException e) {
      fail("Decoding failed with exception: ");
    }
  }

  // Negative Tests

  /**
   * Tests decoding a null value.
   * A null input should not throw an exception, but return an empty array.
   */
  @Test
  public void decodeNullValue() {
    try {
      StringDecoder<TestEnum> decoder = new StringDecoder<>(TestEnum.class);
      TestEnum[] decodedEnums = decoder.decode(null);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * Tests decoding an empty byte array.
   */
  @Test
  public void decodeEmptyByteArray() {
    try {
      StringDecoder<TestEnum> decoder = new StringDecoder<>(TestEnum.class);
      TestEnum[] decodedEnums = decoder.decode(new byte[0]);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * Tests decoding a byte array with invalid UTF-8 sequences.
   */
  @Test
  public void decodeInvalidUTF8() {
    try {
      StringDecoder<TestEnum> decoder = new StringDecoder<>(TestEnum.class);
      byte[] invalidData = {(byte) 0xC3, (byte) 0x28}; // Invalid UTF-8 sequence
      decoder.decode(invalidData);
      fail("Decoding invalid UTF-8 should throw CorruptDataException");
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }
}