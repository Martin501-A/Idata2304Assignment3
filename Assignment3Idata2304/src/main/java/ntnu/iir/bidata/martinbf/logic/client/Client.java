package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.iodata.DataBroadcaster;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;

import java.io.IOException;
import java.util.List;

/**
 * Represents a client in the system.
 * The client delegates the order of operations for the system when it comes to internet connections.
 *
 */
public class Client implements DataBroadcaster, Runnable {
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
    this.connections = connections;
    this.receiver = receiver;
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
    new Thread(this).start();
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

  /**
   * Runs this client to start handling received data.
   */
  @Override
  public void run() {
    while (running) {
      for (Connection conn : connections) {
        byte[] data;
        while ((data = conn.receive()) != null) {
          receiver.handleReceivedData(data);
        }
      }
    }
  }
}
