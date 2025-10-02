package ntnu.iir.bidata.martinbf.logic.parser;

/**
 * Represents the available commands for the TV protocol.
 */
public enum TVCommand {
  POWER("POWER"),
  CHANNEL_UP("CHANNEL_UP"),
  CHANNEL_DOWN("CHANNEL_DOWN"),
  CONNECT("CONNECT");



  private final String command;

  TVCommand(String command) {
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
