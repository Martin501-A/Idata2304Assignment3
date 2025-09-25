package ntnu.iir.bidata.martinbf.logic;

/**
 * Represents the available commands for the TV protocol.
 */
public enum Command {
  POWER("POWER"),
  CHANNEL_UP("CHANNEL_UP"),
  CHANNEL_DOWN("CHANNEL_DOWN");



  private final String command;

  Command(String command) {
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
