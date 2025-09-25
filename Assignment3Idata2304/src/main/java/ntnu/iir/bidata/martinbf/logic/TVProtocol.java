package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;

/**
 * Represents the TV protocol used for communication between a TV and a remote control.
 */
public class TVProtocol {
  private final TV tv;

  /**
   * Constructs a new TVProtocol instance.
   */
  public TVProtocol(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.tv = tv;
  }

  /**
   * Processes a command received from the remote control.
   * Sending back current channel.
   */
  public String process(Command command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    if (tv.getPowerStatus()) {
      return handleOn(command);
    } else {
      return handleOff(command);
    }
  }

  /**
   * Handles commands when tv is off.
   */
  private String handleOff(Command command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    if (command == Command.POWER) {
      tv.power();
      return tv.getCurrentChannel().toString();
    } else {
      return Channel.NONE.toString();
    }
  }

  /**
   * Handles commands when tv is on.
   */
   private String handleOn(Command command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
      return switch (command) {
        case POWER -> {
          tv.power();
          yield tv.getCurrentChannel().toString();
        }
        case CHANNEL_UP -> {
          tv.nextChannel();
          yield tv.getCurrentChannel().toString();
        }
        case CHANNEL_DOWN -> {
          tv.previousChannel();
          yield tv.getCurrentChannel().toString();
        }
      };
  }
}
