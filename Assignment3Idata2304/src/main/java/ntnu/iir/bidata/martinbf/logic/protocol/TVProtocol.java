package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.parser.TVCommand;
import ntnu.iir.bidata.martinbf.logic.parser.TVCommandParser;
import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;

/**
 * Represents the TV protocol used for communication.
 */
public class TVProtocol implements Protocol {
  private final TVCommandParser parser;
  private boolean fail;
  private boolean complete;
  private final TV tv;
  private TVCommand[] commands;

  /**
   * Constructs a new TVProtocol instance.
   */
  public TVProtocol(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.parser = new TVCommandParser(3);
    this.fail = false;
    this.complete = false;
    this.tv  = tv;
  }

  /**
   * Processes the given data according to the TV protocol.
   *
   * @param data The data to process.
   */
  //Maybe have a service that handles the commands instead of the protocol directly?
  @Override
  public void processData(byte[] data) {
    this.commands = this.parser.parse(data);
    for (TVCommand command : commands) {
      if (command != null) {
        process(command);
        command = null;
      }
    }
  }

  /**
   * Finishes the protocol processing and returns the result.
   *
   * @return The result of the protocol processing.
   * @Throws IllegalFinishException if the protocol has failed or is not complete.
   */
  @Override
  public byte[] finish() throws IllegalFinishException {
    if (this.fail) {
      throw new IllegalFinishException("Protocol failed during processing");
    } if (!this.complete) {
      throw new IllegalFinishException("Protocol is not complete");
    } else {
      return this.tv.getCurrentChannel().toString().getBytes();
    }
  }

  /**
   * Processes a single TV command.
   * Fails the protocol if the command is unrecognized.
   */
  private void process(TVCommand command) {
    switch (command) {
      case POWER -> this.tv.power();
      case CHANNEL_UP -> this.tv.nextChannel();
      case CHANNEL_DOWN -> this.tv.previousChannel();
      case CONNECT -> {
        // No action needed for CONNECT command
      }
      default -> fail();
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
    if (this.commands == null) {
      return false;
    }
    boolean complete = true;
    for (TVCommand command : commands) {
      if (command != null) {
        complete = false;
      }
    }
    return complete;
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

  /**
   * Fails the protocol.
   */
  @Override
  public void fail() {
    this.fail = true;
  }

  /**
   * Resets the protocol to its initial state.
   * If the protocol is complete, it resets all states.
   * If the protocol has failed, it only resets the fail state and commands.
   */
  @Override
  public void reset() {
    if (this.complete){
      this.complete = false;
      this.fail = false;
      this.commands = null;
    } else if (this.fail) {
      this.fail = false;
      this.commands = null;
    }
  }
}
