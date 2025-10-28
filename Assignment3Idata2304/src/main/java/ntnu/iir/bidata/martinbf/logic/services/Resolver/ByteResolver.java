package ntnu.iir.bidata.martinbf.logic.services.Resolver;

/**
 * Resolves messages given to their respective handlers.
 */
public interface ByteResolver {

  /**
   * Resolves all messages in the list.
   *
   * @param message the byte message to resolve.
   */
  byte[] resolve(byte[] message);
}
