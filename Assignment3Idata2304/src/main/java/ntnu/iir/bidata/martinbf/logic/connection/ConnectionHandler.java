package ntnu.iir.bidata.martinbf.logic.connection;

import ntnu.iir.bidata.martinbf.logic.iodata.IOEvent;

/**
 * Represents a handler of connection.
 */
public interface ConnectionHandler {

  /**
   * Handles a connection when a connection receives data.
   *
   * @param event the event to handle
   */
  void handle(IOEvent event);
}
