package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.services.MessageHandler;
import ntnu.iir.bidata.martinbf.logic.services.decoder.DecoderService;
import ntnu.iir.bidata.martinbf.logic.services.encoder.EncoderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Represents a client in the system.'
 *
 */
public class Client {
  private final List<Connection> connections;
  private final MessageHandler messageHandler;
  private boolean isRunning;


  /**
   * Constructs a Client with the specified connection.
   *
   * @param connections the map of available connections.
   *
   */

  public Client(List<Connection> connections,
                MessageHandler messageHandler) {
    this.connections = connections;
    this.messageHandler = messageHandler;
  }

  /**
   * Starts the client and begins processing Connections.
   */
  public void start() {
    for (Connection connection : connections) {
      try {
        if (!connection.isConnected()) {
          connection.connect();
        }
        Thread thread = new Thread(connection);
        thread.start();
      } catch (IOException e) {
        throw new RuntimeException("Not Implemented Exception handling");
      }
    }
  }

  /**
   * Stops the client and stops its connections.
   */
  public void stop() {
    this.isRunning = false;
    for (Connection connection : connections) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
