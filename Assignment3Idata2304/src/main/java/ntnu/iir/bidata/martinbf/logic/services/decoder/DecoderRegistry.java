package ntnu.iir.bidata.martinbf.logic.services.decoder;

import java.util.Map;

/**
 * Represents a register of decoders where they are stored with a key.
 */
public class DecoderRegistry {
  private static DecoderRegistry instance;
  private Map<String, Decoder<?,?>> decoders;

  /**
   * Constructs the singleton instance.
   */
  private DecoderRegistry() {}

  /**
   * Returns the registry.
   *
   * @return the register of decoders.
   */
  public static DecoderRegistry getInstance() {
    if  (instance == null) {
      instance = new DecoderRegistry();
    }
    return instance;
  }
}
