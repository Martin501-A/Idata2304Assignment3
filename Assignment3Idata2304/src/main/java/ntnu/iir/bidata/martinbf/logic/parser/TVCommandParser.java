package ntnu.iir.bidata.martinbf.logic.parser;

/**
 * Represents a parser for TV commands.
 * Parses the byte data into TVCommand enums.
 */
public class TVCommandParser {
  private final int maxCommands; // Maximum number of commands to parse


  /**
   * Creates a new TVCommandParser with a maximum amount of commands to parse.
   *
   * @param maxCommands The maximum number of commands to parse. Must be greater than 0.
   */
  public TVCommandParser(int maxCommands) {
    if (maxCommands <= 0) {
      throw new IllegalArgumentException("maxCommands must be greater than 0");
    }
    this.maxCommands = maxCommands;
  }

  /**
   * Parses the given data.
   *
   * @param data The data to parse.
   * @return An array of TVCommand enums.
   */
  public TVCommand[] parse(byte[] data) {
    String dataStr = new String(data).trim();
    String[] commandStrings = dataStr.split(" ");
    TVCommand[] commands = new TVCommand[this.maxCommands];
    int i = 0;
    for (String cmdStr : commandStrings) {
      if (TVCommand.hasValue(cmdStr) && i < this.maxCommands) {
        commands[i] = TVCommand.valueOf(cmdStr);
        i++;
      }
    }
    return commands; // Placeholder return
  }
}
