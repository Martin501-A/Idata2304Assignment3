package ntnu.iir.bidata.martinbf.logic.connection;

/**
 * Represents a handler of connection.
 */
public interface ConnectionHandler {

  /**
   * Handles a connection when a connection receives data.
   *
   * @param connection the connection to update from.
   */
  void handle(Connection connection);
}
