package ntnu.iir.bidata.martinbf.logic.services.decoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a register of decoders where they are stored with a key.
 *
 * @author martin barth frøseth
 */
public class DecoderRegistry {
  private static DecoderRegistry instance;
  private final Map<String, Decoder<?, ?>> registry;

  /**
   * Constructs the singleton instance.
   */
  private DecoderRegistry() {
    this.registry = new HashMap<>();
  }

  /**
   * Register a decoder to the registry.
   *
   * @param key the identifier for the decoder.
   * @param decoder the decoder to add.
   * @param erase erase existing decoder linked to the key if true.
   * @throws IllegalArgumentException if key, decoder is null, key is empty, or key already exists.
   */
  public void registerDecoder(String key, Decoder<?,?> decoder, boolean erase) {
    if (key == null || key.isEmpty()) {
      throw new IllegalArgumentException("Key cannot be null or empty");
    }
    if (decoder == null) throw new IllegalArgumentException("Invalid null decoder."); //TODO Discuss.
    if (this.registry.containsKey(key)) {
      throw new IllegalArgumentException("Already has element associated with key.");
    }
    this.registry.put(key, decoder);
  }

  /**
   * Registers aliases for existing decoders.
   *
   * @param currentKey the key already used as identifier.
   * @param alias the alias that the decoder is also supposed to be identified by.
   * @throws IllegalArgumentException if currentKey, alias are null or empty or currentKey does not exist, alias already exists.
   */
  public void registerAlias(String currentKey, String alias) {

    if (currentKey == null || currentKey.isEmpty()) {
      throw new IllegalArgumentException("currentKey is empty");
    }
    if (alias == null || alias.isEmpty()) {
      throw new IllegalArgumentException("the new alias is empty");
    }
    if (!this.registry.containsKey(currentKey)) {
      throw new IllegalArgumentException("currentKey does not exist in the system");
    }
    if (this.registry.containsKey(alias)) {
      throw new IllegalArgumentException("Alias was already in use by teh registry");
    }
    Decoder<?, ?> decoder = this.registry.get(currentKey);
    this.registry.put(alias, decoder);
  }

  /**
   * Get access to a decoder by its key.
   *
   * @param key the identifier for the decoder.
   * @throws IllegalArgumentException if the registry does not contain the key.
   */
  public Decoder<?, ?> getDecoder(String key) {
    if (!this.registry.containsKey(key)) {
      throw new IllegalArgumentException("Registry has no decoder by key:" + key);
    }
    return this.registry.get(key);
  }

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
