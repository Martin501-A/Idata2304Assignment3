package ntnu.iir.bidata.martinbf.logic.parser;

/**
 * Represents a parser that can parse data.
 */
public interface Parser {

  /**
   * Parses the given data.
   *
   * @param data The data to parse.
   */
  void parse(byte[] data);
}
