package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.server.thread.TVTCPThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.List;

public class TVTCPServer extends Thread implements TVServer {
  private TV tv;
  private ServerSocket socket;
  private volatile boolean running = true;

  /**
   * Creates a new TV server that listens on the specified port using tcp.
   *
   * @param port the port number to listen on (must be between 1024 and 65535)
   */
  public TVTCPServer(int port, TV tv, IPAddress address) throws IOException {
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    List<Channel> channels = List.of(Channel.values());
    this.tv = tv;
    this.socket = new ServerSocket(port, 50,
            InetAddress.getByName(address.getAddress()));
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
   * Starts the TV server to accept incoming connections.
   */
  @Override
  public void run() {
    while (this.running) {
      try {
        new TVTCPThread(this.socket.accept(), this.tv).start();
      } catch (IOException e) {
        continue;
      }
    }
  }

  /**
   * Closes the server socket and stops the server.
   */
  @Override
  public void close() throws IOException {
    this.running = false;
    if (this.socket != null && !this.socket.isClosed()) {
      this.socket.close();
    }
  }
}
