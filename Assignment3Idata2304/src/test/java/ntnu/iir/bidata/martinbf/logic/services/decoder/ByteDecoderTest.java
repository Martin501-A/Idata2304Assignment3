package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * <p>Tests the ByteDecoder Class</p>
 * <p>The following is Tested: </p>
 * <p>Positive Tests: </p>
 * <ul>
 *   <li>That a decoder can be constructed.</li>
 *   <li>That the decoder can decode a message.</li>
 *   <li>That the decoder can decode multiple messages.</li>
 * </ul>
 *
 * <p>Negative Tests:</p>
 * <ul>
 *   <li>That null data cannot be decoded.</li>
 *   <li>That corrupt empty data handled.</li>
 *   <li>That an invalid message format is thrown by the decoder.</li>
 * </ul>
 */
public class ByteDecoderTest {

  /**
   * Decoder can decode message.
   */
  @Test
  public void decodeMessage() {
    //TODO Ask about such testing with string and other datatype.
    try {
      ByteDecoder decoder = ByteDecoder.getStandardInstance();
      final String testString = new Message("Test", "Test").toString();
      byte[] encoded = testString.getBytes(decoder.getCharset());
      List<Message> decodedString = decoder.decode(encoded);
      assertEquals(testString, decodedString.getFirst().toString());
    } catch (CorruptDataException e) {
      fail();
    }
  }

  /**
   * Decoder can decode many messages
   */
  @Test
  public void decodeManyMessages() {
    final Message msg1 = new Message("Test1", "Test");
    final Message msg2 = new Message("Test2","Test");
    byte[] encodedMessages = msg1.toString() +
  }
}