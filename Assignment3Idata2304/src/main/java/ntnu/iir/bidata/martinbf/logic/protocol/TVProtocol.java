package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.TVPMessage;
import ntnu.iir.bidata.martinbf.logic.encoding.CorruptDataException;
import ntnu.iir.bidata.martinbf.logic.encoding.Decoder;
import ntnu.iir.bidata.martinbf.logic.encoding.Encoder;
import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

/**
 * Represents the TV protocol used for communication.
 */
public class TVProtocol implements Protocol<TVPMessage, Channel> {
  private boolean fail;
  private boolean complete;
  private final TV tv;
  private TVPMessage[] commands;

  /**
   * Constructs a new TVProtocol instance.
   */
  public TVProtocol(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.fail = false;
    this.complete = false;
    this.tv = tv;
  }

  /**
   * Processes the given data according to the TV protocol.
   *
   * @param data The data to process.
   */
  //Maybe have a service that handles the commands instead of the protocol directly?
  @Override
  public void processData(TVPMessage[] data) {
  }

  /**
   * Finishes the protocol processing and returns the result.
   *
   * @return The result of the protocol processing.
   * @Throws IllegalFinishException if the protocol has failed or is not complete.
   */
  @Override
  public Channel[] finish() {
    if (this.fail) {
      throw new IllegalFinishException("Protocol failed during processing");
    }
    if (!this.complete) {
      throw new IllegalFinishException("Protocol is not complete");
    } else {
      return new Channel[]{Channel.NONE};
    }
  }

  /**
   * Checks whether the protocol processing is complete.
   * The protocol is considered complete if all commands have been processed.
   *
   * @return true if the protocol is complete, false otherwise.
   */
  @Override
  public boolean isComplete() {
    return this.complete;
  }

  /**
   * Checks whether the protocol has failed.
   *
   * @return true if the protocol has failed, false otherwise.
   */
  @Override
  public boolean hasFailed() {
    return this.fail;
  }

  @Override
  public Channel[] getResponse() {
    return new Channel[0];
  }

  /**
   * Fails the protocol.
   */
  private void fail() {
    this.fail = true;
  }

  /**
   * Resets the protocol to its initial state.
   * If the protocol is complete, it resets all states.
   * If the protocol has failed, it only resets the fail state and commands.
   */
  @Override
  public void reset() {
    if (this.complete) {
      this.complete = false;
      this.fail = false;
      this.commands = null;
    } else if (this.fail) {
      this.fail = false;
      this.commands = null;
    }
  }
}