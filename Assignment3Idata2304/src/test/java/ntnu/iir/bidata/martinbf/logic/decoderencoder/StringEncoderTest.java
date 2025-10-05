package ntnu.iir.bidata.martinbf.logic.decoderencoder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Tests the StringEncoder class. </p>
 * <p>The Following is tested: </p>
 * <p>Positive Tests: </p>
 * <ul>
 *   <li>Encoding a simple Enum</li>
 *   <li>decoding a simple enum</li>
 * </ul>
 * <p>Negative Tests: </p>
 * <ul>
 *   <li>Encoding a null value</li>
 *   <li>Decoding a null value</li>
 *   <li>Decoding an invalid enum value</li>
 * </ul>
 */
class StringEncoderTest {

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
    try {
      StringEncoder<TestEnum, TestEnum> stringEncoder = new StringEncoder<>(TestEnum.class);
      TestEnum[] originalEnums = {TestEnum.VALUE_ONE};
      byte[] encodedData = stringEncoder.encode(originalEnums);
      assertNotNull(encodedData);
      TestEnum[] decodedEnums = stringEncoder.decode(encodedData);
      assertNotNull(decodedEnums);
      assertArrayEquals(originalEnums, decodedEnums);
    } catch (CorruptDataException e) {
      fail("Decoding failed with exception: ");
    }
  }

  /**
   * Tests with multiple enums in an array.
   */
  @Test
  public void decodeAndEncodeMultipleEnums() {
    try {
    StringEncoder<TestEnum, TestEnum> stringEncoder = new StringEncoder<>(TestEnum.class);
    TestEnum[] originalEnums = {TestEnum.VALUE_ONE, TestEnum.VALUE_TWO, TestEnum.VALUE_THREE};
    byte[] encodedData = stringEncoder.encode(originalEnums);
    assertNotNull(encodedData);
    TestEnum[] decodedEnums = stringEncoder.decode(encodedData);
    assertNotNull(decodedEnums);
    assertArrayEquals(originalEnums, decodedEnums);
  } catch (CorruptDataException e) {
      fail("Decoding failed with exception: ");
    }
  }

  // Negative Tests

  /**
   * Tests encoding a null value.
   * The null value should be ignored and result in an empty byte array.
   */
  @Test
  public void encodeNullValue() {
    StringEncoder<TestEnum, TestEnum> stringEncoder = new StringEncoder<>(TestEnum.class);
    TestEnum[] originalEnums = {null};
    byte[] encodedData = stringEncoder.encode(originalEnums);
    assertNotNull(encodedData);
    assertEquals(0, encodedData.length);
  }

  /**
   * Tests decoding a null value.
   * The null value should result in an empty array.
   */
  @Test
  public void decodeNullValue() {
    try {
      StringEncoder<TestEnum, TestEnum> stringEncoder = new StringEncoder<>(TestEnum.class);
      byte[] encodedData = new byte[0];
      TestEnum[] decodedEnums = stringEncoder.decode(encodedData);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }
}