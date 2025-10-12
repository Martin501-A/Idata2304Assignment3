package ntnu.iir.bidata.martinbf.logic.services.decoder;


import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a decoder of messages which takes bytes array
 * of messages and turns it into messages by using strings and a charset.
 * Separators for each message \n, key and value are separated by colon(key:value).
 *
 * @author martin barth frøseth
 */
public class ByteDecoder implements Decoder<byte[] ,List<Message>> {
  private final Charset charset;
  private static final String separator = "\n"; // Message separator.
  private static final String kvSeparator = ":"; //Key-value separator.

  /**
   * Creates an instance of the decoder
   *
   * @param charset the charset used in decoding.
   */
  public ByteDecoder(Charset charset) {
    if (charset == null) {
      throw new IllegalArgumentException("charset cannot be null");
    }
    this.charset = charset;
  }

  /**
   * Creates and instance of messageDecoder with standard utf-8 charset.
   *
   * @return an instance of the decoder using utf-8 charset.
   */
  public static ByteDecoder getStandardInstance() {
    return new ByteDecoder(StandardCharsets.UTF_8);
  }

  /**
   * Returns the charset used by the decoder.
   *
   * @return the charset used in decoding.
   */
  public Charset getCharset() {
    return this.charset;
  }

  /**
   * Decodes byte into an array of messages.
   *
   * @param data The byte data to parse.
   * @return the Message array the byte decodes to.
   * @throws CorruptDataException if data cannot be represented as a Message.
   */
  @Override
  public List<Message> decode(byte[] data) throws CorruptDataException {
    if (data == null) {
      throw new IllegalArgumentException("Null data was put as argument");
    }
    final List<Message> messages = new ArrayList<>();
    String stringData = new String(data, this.charset);
    String[] stringArray = stringData.split(separator);
    for (String s : stringArray) {
      String[] kv = s.trim().split(kvSeparator, 2);
      if (kv.length != 2 || kv[0].isEmpty() || kv[1].isEmpty()) {
        throw new CorruptDataException("Transferred Message was not split into key value pair.");
      }
      if (kv[0].equals("null") || kv[1].equals("null")) {
        throw new CorruptDataException("Transferred Message had Illegal null values");
      }
      messages.add(new Message(kv[0].trim(), kv[1].trim()));
    }
    return messages;
  }
}
