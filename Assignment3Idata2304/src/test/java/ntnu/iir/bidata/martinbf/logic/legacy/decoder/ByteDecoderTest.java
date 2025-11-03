package ntnu.iir.bidata.martinbf.logic.legacy.decoder;

import ntnu.iir.bidata.martinbf.legacy.entity.Message;
import ntnu.iir.bidata.martinbf.legacy.logic.CorruptDataException;
import ntnu.iir.bidata.martinbf.legacy.logic.decoder.ByteDecoder;
import ntnu.iir.bidata.martinbf.legacy.logic.decoder.Decoder;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
 *   <li>That null charset is handled</li>
 *   <li>Null byte cannot be decoded</li>
 *   <li>Null or empty key when decoding is handled</li>
 *   <li>That null data cannot be decoded.</li>
 *   <li>That empty decoded data is handled.</li>
 *   <li>That an invalid message format is thrown by the decoder.</li>
 * </ul>
 */
public class ByteDecoderTest {

  /**
   * Decoder can decode message.
   */
  @Test
  public void decodeMessage() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      final String testString = new Message("Test", "Test").toString();
      byte[] encoded = testString.getBytes(StandardCharsets.UTF_8);
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
    try {
      final Message msg1 = new Message("Test1", "Test");
      final Message msg2 = new Message("Test2", "Test");
      //TODO Maybe have separator be stored a bit better This test location use is not that great.
      String combinedMessage = msg1.toString() + "\n" + msg2.toString();
      byte[] encodedMessage = combinedMessage.getBytes(StandardCharsets.UTF_8);
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      List<Message> decodedString = decoder.decode(encodedMessage);
      assertEquals(msg1.getKey(), decodedString.get(0).getKey());
      assertEquals(msg2.getKey(), decodedString.get(1).getKey());
      assertEquals(msg1.getValue(), decodedString.get(0).getValue());
      assertEquals(msg2.getValue(), decodedString.get(1).getValue());
    } catch (CorruptDataException e) {
      fail();
    }
  }

  //Negative tests

  /**
   * Tests null charset.
   */
  @Test
  public void createWithNullCharset() {
    try {
      Decoder<byte[], List<Message>> decoder = new ByteDecoder(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * That null data is thrown.
   */
  @Test
  public void nullMessageIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      decoder.decode(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    } catch (CorruptDataException e) {
      fail();
    }
  }

  /**
   * Null Key is thrown.
   */
  @Test
  public void nullKeyIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      String data = null + ":" + "Test";
      byte[] encoded = data.getBytes(StandardCharsets.UTF_8);
      decoder.decode(encoded);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * Empty key is thrown.
   */
  @Test
  public void emptyKeyIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      String data = ":Test";
      byte[] encoded = data.getBytes(StandardCharsets.UTF_8);
      decoder.decode(encoded);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * null value is thrown.
   */
  @Test
  public void nullValueIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      String data = "Test:" + null;
      byte[] encoded = data.getBytes(StandardCharsets.UTF_8);
      decoder.decode(encoded);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * Empty value is thrown.
   */
  @Test
  public void emptyValueIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      String data = "Test:";
      byte[] encoded = data.getBytes(StandardCharsets.UTF_8);
      decoder.decode(encoded);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }

  /**
   * Invalid message type is thrown.
   * An invalid message is a message without a colon separator :.
   * Expected behavior is a corruptDataException since that separator can be gone through corruption of data.
   */
  @Test
  public void invalidMessageIsNotDecoded() {
    try {
      Decoder<byte[], List<Message>> decoder = ByteDecoder.getStandardInstance();
      String invalidData = "Test";
      byte[] encoded = invalidData.getBytes(StandardCharsets.UTF_8);
      decoder.decode(encoded);
      fail();
    } catch (CorruptDataException e) {
      assertTrue(true);
    }
  }
}

