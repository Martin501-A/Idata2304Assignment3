package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.TVPMessage;
import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the TV protocol used for communication.
 */
public class TVProtocol implements Protocol<TVPMessage, Channel> {
  private boolean fail;
  private boolean complete;
  private final TV tv;
  private List<TVPMessage> messages;

  /**
   * Constructs a new TVProtocol instance.
   *
   * @param tv The TV associated with this protocol.
   * @throws IllegalArgumentException if the TV is null.
   */
  public TVProtocol(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.fail = false;
    this.complete = false;
    this.tv = tv;
    this.messages = new ArrayList<>();
  }

  /**
   * Processes the given data according to the TV protocol.
   *
   * @param data The data to process.
   * @throws IllegalArgumentException if the data is null.
   * @throws IllegalArgumentException if any message in the data is null.
   */
  //Maybe have a service that handles the commands instead of the protocol directly?
  @Override
  public void processData(TVPMessage[] data) {
  }

  /**
   * Processes a single TVPMessage according to the TV protocol.
   */
  private void processMessage(TVPMessage message) {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null");
    }
    switch (message) {
    }
  }

  /**
   * Finishes the protocol processing and returns the result.
   *
   * @return The result of the protocol processing.
   * @throws IllegalFinishException if the protocol is not complete or has failed.
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
      this.messages = null;
    } else if (this.fail) {
      this.fail = false;
      this.messages = null;
    }
  }

  /**
   * Returns the TV associated with this protocol.
   *
   * @return The TV associated with this protocol.
   */
  public TV getTV() {
    return this.tv;
  }

  /**
   * Returns the messages yet to be processed by the protocol.
   *
   * @return The messages yet to be processed by the protocol.
   */
  public TVPMessage[] getMessages() {
    throw new IllegalArgumentException("TVProtocol.getMessages() not implemented yet");
  }
}