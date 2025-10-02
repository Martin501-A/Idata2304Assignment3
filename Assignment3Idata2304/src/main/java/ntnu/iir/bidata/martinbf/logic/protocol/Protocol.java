package ntnu.iir.bidata.martinbf.logic.protocol;

/**
 * represents a protocol for communication on the application.
 */
public interface Protocol {

  /**
   * Processes the given data according to the protocol's rules.
   */
  void processData(byte[] data);

  /**
   * Checks whether the protocol is complete.
   */
  boolean isComplete();

  /**
   * Fails the protocol.
   */
  void fail();

  /**
   * Resets the protocol to its initial state.
   */
  void reset();
}
