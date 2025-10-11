package ntnu.iir.bidata.martinbf.logic.services.encoder;

import ntnu.iir.bidata.martinbf.entity.Message;

import java.util.List;

/**
 * Encodes a list of messages.
 */
public interface EncoderService {


  /**
   * Encodes the messages.
   *
   * @param messages the messages to encode.
   * @return the byte representation of the messages.
   */
  byte[] encode(List<Message> messages);
}
