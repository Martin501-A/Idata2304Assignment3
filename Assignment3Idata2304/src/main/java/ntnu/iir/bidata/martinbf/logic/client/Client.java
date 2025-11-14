package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.connection.ConnectionHandler;
import ntnu.iir.bidata.martinbf.logic.iodata.DataBroadcaster;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Represents a client in the system.
 * The client delegates the order of operations for the system when it comes to internet connections.
 *
 */
public class Client implements DataBroadcaster, ConnectionHandler {
  private final List<Connection> connections;
  private final DataHandler receiver;
  private volatile boolean running = false;

  /**
   * Constructs a Client with the specified connections.
   *
   * @param connections the list of connections.
   * @param receiver handles received data.
   */
  public Client(List<Connection> connections,  DataHandler receiver) {
    if  (connections == null || receiver == null) {
      throw new IllegalArgumentException("connections or messageHandler cannot be null");
    }
    if (connections.isEmpty()) {
      throw new IllegalArgumentException("connections cannot be empty");
    }
    this.connections = Collections.synchronizedList(connections);
    this.receiver = receiver;
    for (Connection connection : connections) {
      connection.setHandler(this);
    }
  }

  /**
   * Starts the client by starting all connections.
   */
  public void start() {
    this.running = true;
    for (Connection connection : connections) {
      try {
        if (!connection.isConnected()) {
          connection.connect();
        }
        Thread thread = new Thread(connection);
        thread.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Sends data to all connections.
   */
  @Override
  public void broadcast(byte[] data) {
    for (Connection connection : connections) {
      connection.send(data);
    }
  }

  /**
   * Handles a Connections request.
   */
  @Override
  public void handle(Connection connection) {
    try {
      if (connection == null) {
        throw new IllegalArgumentException("connection cannot be null");
      }
      if (!connection.isConnected()) {
        throw new IllegalArgumentException("connection is not connected");
      }
      this.receiver.handleReceivedData(connection.receive());
    } catch (InterruptedException e) {
      throw new RuntimeException("Not implemented handling");
    }
  }

  /**
   * Stops the client and stops its connections.
   */
  public void stop() {
    for (Connection connection : connections) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
