package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.entity.entities.Channel;
import ntnu.iir.bidata.martinbf.entity.entities.IPAddress;
import ntnu.iir.bidata.martinbf.entity.entities.TV;
import ntnu.iir.bidata.martinbf.logic.TVTCPThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.List;
import java.util.Scanner;

public class TVTCPServer implements TVServer {
  private TV tv;
  private ServerSocket socket;
  private volatile boolean running = true;

  /**
   * Creates a new TV server that listens on the specified port.
   *
   * @param port the port number to listen on (must be between 1024 and 65535)
   */
  public TVTCPServer(int port) throws IOException {
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    List<Channel> channels = List.of(Channel.values());
    this.tv = new TV(channels);
    this.socket = new ServerSocket(port, 50,
            InetAddress.getByName(IPAddress.SOCKET_ADDRESS.getAddress()));
  }

  /**
   * Gets the TV instance associated with this server.
   */
  public TV getTV() {
    return this.tv;
  }

  /**
   * Gets the ServerSocket instance associated with this server.
   */
  public ServerSocket getSocket() {
    return this.socket;
  }

  /**
   * Print error message to console.
   */
  public static void printErrorMessage() {
    System.err.println("Error has happened please try to input port again to restart server.");
  }

  /**
   * Starts the TV server to accept incoming connections.
   */
  public void start() throws IOException {
    System.out.println("Server started, waiting for connections...");
    while (running) {
        new TVTCPThread(socket.accept(), tv).start();
    }
  }

  /**
   * Closes the server socket and stops the server.
   */
  @Override
  public void close() throws IOException {
    running = false;
    if (socket != null && !socket.isClosed()) {
      socket.close();
    }
  }
}
