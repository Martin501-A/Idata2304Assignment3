package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.iodata.DataBroadcaster;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;

import java.util.List;

/**
 * Represents a server in the system.
 * A server can have multiple clients connected to it using specific protocols.
 */
public class Server implements DataBroadcaster, Runnable {
  private volatile List<Connection> connections;
  private DataHandler handler;
  private volatile boolean running = false;

  public Server(List<Connection> connections, DataHandler receiver) {
    if (connections == null) {
      throw new IllegalArgumentException("connections cannot be null");
    }
    if  (receiver == null) {
      throw new IllegalArgumentException("receiver cannot be null");
    }
    this.connections = connections;
    this.handler = receiver;
  }

  @Override
  public void run() {
    running = true;
    while (running) {

    }
  }

  @Override
  public void broadcast(byte[] data) {

  }
}
