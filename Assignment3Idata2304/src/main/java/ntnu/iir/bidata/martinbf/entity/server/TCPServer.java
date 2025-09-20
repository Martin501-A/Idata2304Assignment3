package ntnu.iir.bidata.martinbf.entity.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represents a TCP server in a computer network.
 * The TCP server can accept connections from clients and communicate over a TCP socket.
 */
public abstract class TCPServer implements Server {
  private Socket socket;

  /**
   * Instantiates a new TCP server.
   */
  public TCPServer(InetAddress address, int port) throws IOException {
    createSocket(address, port);
  }

  /**
   * Creates a TCP socket Bound to the specified address and port.
   */
  @Override
  public void createSocket(InetAddress address, int port) throws IOException {
    try (Socket socket = new Socket(address, port) ) {;
      this.socket = socket;
    }
  }
  
  /**
   * Closes the TCP socket.
   */
  @Override
  public void closeSocket() {
    try {
      this.socket.close();
    } catch (IOException e) {
      //TODO handle exception
    }
  }



  /**
   * Gets the TCP socket.
   *
   * @return the TCP socket
   */
  public Socket getSocket() {
    return this.socket;
  }

}
