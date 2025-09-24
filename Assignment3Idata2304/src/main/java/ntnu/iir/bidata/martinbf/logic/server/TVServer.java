package ntnu.iir.bidata.martinbf.logic.server;

import java.io.IOException;

public interface TVServer extends AutoCloseable {

  /**
   * Starts the server.
   */
  void start() throws IOException;

  /**
   * Closes the server.
   */
  void close() throws IOException;

}
