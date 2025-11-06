package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.connection.ConnectionHandler;
import ntnu.iir.bidata.martinbf.logic.iodata.DataBroadcaster;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;
import ntnu.iir.bidata.martinbf.logic.iodata.IOEvent;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Represents a client in the system.
 * The client delegates the order of operations for the system when it comes to internet connections.
 *
 */
public class Client implements DataBroadcaster, ConnectionHandler {
  private final BlockingQueue<IOEvent> eventsQueue;
  private final List<Connection> connections;
  private final DataHandler receiver;

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
    this.eventsQueue = new PriorityBlockingQueue<>();
    this.receiver = receiver;
    connections.forEach((Connection connection) -> {
      connection.setHandler(this);
    });
  }

  /**
   * Starts the client by starting all connections.
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
   * Sends data to all connections.
   */
  @Override
  public void broadcast(byte[] data) {
    for (Connection connection : connections) {
      connection.send(data);
    }
  }

  /**
   * Handles a connections received data.
   */
  @Override
  public void handle(IOEvent event) {
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
   * Adds an event to the Queue.
   */
  public void addEvent(IOEvent event) {
    if (event == null) {
      throw new IllegalArgumentException("event cannot be null");
    }
    this.eventsQueue.add(event);
  }
}
