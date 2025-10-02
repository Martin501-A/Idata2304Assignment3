package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;

/**
 * Represents a client in the system.'
 * A client has a connection with a server using a specific protocol.
 */
public class Client {
  private Connection connection;

  /**
   * Constructs a Client with the specified connection.
   *
   * @param connection the connection to be used by the client
   */
  public Client(Connection connection) {
    if (connection == null) {
      throw new IllegalArgumentException("Connection cannot be null");
    }
    this.connection = connection;
  }

  /**
   * Starts the client by establishing the connection.
   */
  public void start() {
    connection.connect();
  }

  /**
   * Stops the client by disconnecting the connection.
   */
  public void stop() {
    connection.disconnect();
  }

  /**
   * Returns the connection used by the client.
   *
   * @return the connection
   */
  public Connection getConnection() {
    return connection;
  }
}
