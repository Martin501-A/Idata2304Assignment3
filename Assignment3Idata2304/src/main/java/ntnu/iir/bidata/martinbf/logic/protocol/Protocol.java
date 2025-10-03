package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

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
   * Checks whether the protocol has failed.
   */
  boolean hasFailed();

  /**
   * Fails the protocol.
   */
  void fail();

  /**
   * Resets the protocol to its initial state.
   */
  void reset();

  /**
   * Finishes the protocol and returns the result.
   */
  byte[] finish() throws IllegalFinishException;
}
