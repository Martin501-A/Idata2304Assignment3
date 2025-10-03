package ntnu.iir.bidata.martinbf.logic.parser;

/**
 * Represents the available commands for the TV protocol.
 */
public enum TVCommand {
  POWER("POWER"),
  CHANNEL_UP("CHANNEL_UP"),
  CHANNEL_DOWN("CHANNEL_DOWN"),
  CONNECT("CONNECT");


  private static final int COMMANDLENGTH = calculateCommandLength();
  private final String command;

  /**
   * Calculates the length of the longest command.
   *
   * @return the length in bytes.
   */
  private static int calculateCommandLength() {
    int maxLength = 0;
    for (TVCommand cmd : TVCommand.values()) {
      if (cmd.command.length() > maxLength) {
        maxLength = cmd.command.length();
      }
    }
    return maxLength;
  }

  /**
   * Checks if the enum values contains the given string.
   *
    * @param value the string to check.
   */
  public static boolean hasValue(String value) {
    boolean hasValue = false;
    for (int i = 0; i < TVCommand.values().length && !hasValue; i++) {
      if (TVCommand.values()[i].command.equals(value)) {
        hasValue = true;
      }
    }
    return hasValue;
  }

  TVCommand(String command) {
    this.command = command;
  }

  /**
   * Returns the amount of byte required to represent the longest command.
   *
   * @return the length in bytes.
   */
  public static int lengthAsByte() {
    return COMMANDLENGTH;
  }

  /**
   * Returns the command as a string.
   */
  @Override
  public String toString() {
    return command;
  }
}
