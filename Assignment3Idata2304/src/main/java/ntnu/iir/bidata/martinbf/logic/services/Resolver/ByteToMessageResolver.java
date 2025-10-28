package ntnu.iir.bidata.martinbf.logic.services.Resolver;

import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.logic.services.CorruptDataException;
import ntnu.iir.bidata.martinbf.logic.services.decoder.ByteDecoder;
import ntnu.iir.bidata.martinbf.logic.services.decoder.Decoder;
import ntnu.iir.bidata.martinbf.logic.services.encoder.Encoder;
import ntnu.iir.bidata.martinbf.logic.services.encoder.StringEnumEncoder;

import java.util.List;

/**
 * Resolves bytes through messages.
 * A message is a standard unit of transfer through the application protocol.
 */
public class ByteToMessageResolver implements ByteResolver {
  private static ByteToMessageResolver instance;
  private final MessageResolver msgResolver;
  private final Decoder<byte[], List<Message>> decoder;
  private final Encoder<List<Message>> encoder;

  /**
   * Initialises this object and its dependencies.
   * Its dependencies are a way to handle messages,
   * A way to encode and decode between messages and bytes.
   */
  private ByteToMessageResolver() {
    this.decoder = ByteDecoder.getStandardInstance();
    this.msgResolver = null;
    this.encoder = null;
  }

  /**
   * Resolves the bytes and returns the byte responses.
   *
   * @param messages the byte messages to resolve.
   * @return the response messages a bytes, if data is corrupted returns null.
   * @throws NullPointerException if null is not handled in case of corrupt data.
   */
  @Override
  public byte[] resolve(byte[] messages) {
    try {
      if (messages == null || messages.length == 0) {
        throw new IllegalArgumentException();
      }
      List<Message> decodedMessages = decoder.decode(messages);
      List<Message> responses = msgResolver.resolveMessages(decodedMessages);
      return encoder.encode(responses);
    }  catch (CorruptDataException e) {
      return null;
    }
  }

  /**
   * Returns the instance of the resolver.
   */
  public static ByteToMessageResolver getInstance() {
    if (instance == null) {
      instance = new ByteToMessageResolver();
    }
    return instance;
  }
}
