package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.Channel;

/**
 * Represents the available messages in the TV protocol.
 */
public enum TVPMessage {
  POWER("POWER"),
  CHANNEL_UP("CHANNEL_UP"),
  CHANNEL_DOWN("CHANNEL_DOWN"),
  CONNECT("CONNECT");

  private final String command;

  /**
   * Checks if the enum values contains the given string.
   *
    * @param value the string to check.
   */
  public static boolean hasValue(String value) {
    boolean hasValue = false;
    for (int i = 0; i < TVPMessage.values().length && !hasValue; i++) {
      if (TVPMessage.values()[i].command.equals(value)) {
        hasValue = true;
      }
    }
    return hasValue;
  }

  TVPMessage(String command) {
    this.command = command;
  }

  /**
   * Returns the command as a string.
   */
  @Override
  public String toString() {
    return command;
  }
}
