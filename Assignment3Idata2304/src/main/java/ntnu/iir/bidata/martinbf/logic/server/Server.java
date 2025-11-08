package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.connection.ConnectionFactory;
import ntnu.iir.bidata.martinbf.logic.connection.ConnectionHandler;
import ntnu.iir.bidata.martinbf.logic.iodata.DataBroadcaster;
import ntnu.iir.bidata.martinbf.logic.iodata.DataHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a server in the system.
 * A server can have multiple clients connected to it using specific protocols.
 */
public class Server implements DataBroadcaster, Runnable, ConnectionHandler {
  private final List<Connection> connections;
  private final DataHandler handler;
  private final ServerSocket serverSocket;
  private volatile boolean running = false;

  public Server(InetSocketAddress address, DataHandler receiver) throws IOException {
    if  (receiver == null) {
      throw new IllegalArgumentException("receiver cannot be null");
    }
    this.connections = Collections.synchronizedList(new ArrayList<>());
    this.handler = receiver;
    this.serverSocket = new ServerSocket(address.getPort(), 50, address.getAddress());
  }

  /**
   * Starts the server on its own thread.
   */
  public void start() {
    Thread t = new Thread(this);
    t.start();
  }

  /**
   * Runs the run loop of the server which accepts connections,
   * and delegates them to each thread. Also cleans up finished connections.
   */
  @Override
  public void run() {
    running = true;
    while (running) {
      try {
        Socket conn = serverSocket.accept();
        Connection tConn = ConnectionFactory.getInstance().createSocketConnection(conn);
        tConn.setHandler(this);
        connections.add(tConn);
        Thread t = new Thread(tConn);
        t.start();
      } catch  (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Broadcasts the data to all connections.
   *
   * @param data the data to broadcast.
   */
  @Override
  public void broadcast(byte[] data) {
    if (data == null) {
      throw new IllegalArgumentException("data cannot be null");
    }
    for  (Connection connection : connections) {
      connection.send(data);
    }
  }

  /**
   * Handles a connection.
   * A connections needs to be handled when they receive data.
   *
   * @param connection the connection to handle.
   */
  @Override
  public synchronized void handle(Connection connection) {
    handler.handleReceivedData(connection.receive());
  }
}
