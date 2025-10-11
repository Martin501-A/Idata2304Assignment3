package ntnu.iir.bidata.martinbf.logic.services.decoder;

import ntnu.iir.bidata.martinbf.entity.Message;

import java.util.List;

/**
 * Represents a DecoderService that delegates inputs to their respective decoders.
 * A Decoder can be registered to this class for it to handle, with respective key input.
 * This class can also resolve the decoder to use from the key input.
 */
public interface DecoderService {

  /**
   * Decodes byte data
   *
   * @param data the data to decode
   * @return the messages of the decoded data.
   */
  List<Message> decode(byte[] data);
}
