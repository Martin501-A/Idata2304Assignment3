package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.parser.TVCommand;
import ntnu.iir.bidata.martinbf.logic.parser.TVCommandParser;

/**
 * Represents the TV protocol used for communication.
 */
public class TVServerProtocol implements Protocol {
  private final TVCommandParser parser;
  private boolean fail;
  private boolean complete;
  private final TV tv;
  private TVCommand[] commands;

  /**
   * Constructs a new TVServerProtocol instance.
   */
  public TVServerProtocol(TV tv) {
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


  @Override
  public byte[] finish() {
    if (this.complete && !this.fail) {
      return tv.getCurrentChannel().toString().getBytes();
    }
    return null;
  }

  /**
   * Processes a single TV command.
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

  @Override
  public boolean hasFailed() {
    return this.fail;
  }

  @Override
  public void fail() {
    this.fail = true;
  }

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
