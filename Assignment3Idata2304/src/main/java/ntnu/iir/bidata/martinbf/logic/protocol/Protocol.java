package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

/**
 * Represents a protocol for communication on the application.
 * @param <I> the type of input messages.
 * @param <O> the type of output messages.
 */
public interface Protocol<I, O> {

  /**
   * Processes the given data according to the protocol's rules.
   */
  public void processData(I[] data);

  /**
   * Checks whether the protocol is complete.
   */
  public boolean isComplete();
  /**
   * Checks whether the protocol has failed.
   */
  public boolean hasFailed();

  /**
   * Returns the current response of the protocol.
   */
  public O[] getResponse();

  /**
   * Resets the protocol to its initial state.
   */
  public void reset();

  /**
   * Finishes the protocol and returns the result.
   */
  public O[] finish() throws IllegalFinishException;
}
