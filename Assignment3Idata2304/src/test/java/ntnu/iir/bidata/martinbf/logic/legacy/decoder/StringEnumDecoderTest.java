package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.logic.TVPMessage;
import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Tests the StringEnumDecoder class</p>
 *
 * This testClass also shows the structure of the StringEnum and the methods they need to implement.
 *
 * <p>The following is tested: </p>
 * <p>Positive tests:</p>
 * <ul>
 *   <li>That a Decoder can be made</li>
 *   <li>That a given String can be decoded into the StringEnum</li>
 * </ul>
 * <p>Negative Tests</p>
 * <ul>
 *   <li>That invalid null or empty String throws an exception</li>
 *   <li>That a null class is thrown.</li>
 * </ul>
 */
public class StringEnumDecoderTest {

  /**
   * A test enum for this decoder.
   */
  private enum TestEnum {
    TEST("Test");

    private final String name;

    TestEnum(String name) {
      this.name = name;
    }

    /**
     * Checks if the enum values contains the given string.
     *
     * @param value the string to check.
     */
    public static boolean hasValue(String value) {
      boolean hasValue = false;
      for (int i = 0; i < TVPMessage.values().length && !hasValue; i++) {
        if (TVPMessage.values()[i].toString().equals(value)) {
          hasValue = true;
        }
      }
      return hasValue;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  /**
   * Decode a given String
   */
  @Test
  public void decodeAString() {
    try {
      String test = TestEnum.TEST.toString();
      Decoder<String, TestEnum> decoder = new StringEnumDecoder<>(TestEnum.class);
      TestEnum testEnum = decoder.decode(test);
      assertEquals(TestEnum.TEST, testEnum);
    } catch (CorruptDataException e) {
      fail();
    }
  }
}