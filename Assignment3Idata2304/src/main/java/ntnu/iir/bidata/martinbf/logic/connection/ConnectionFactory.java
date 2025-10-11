package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Represents a factory for creating connection instances.
 * The connection instances are connections which represent a connection to a remote address.
 */
public class ConnectionFactory {
  private static ConnectionFactory instance;

  /**
   * The private constructor for singleton.
   */
  private ConnectionFactory() {}

  /**
   * Creates a UDP Connection for to connect to a remote address
   *
   * @param address the remote address to connect to
   * @return a new Connection of UDP that needs to connect.
   */
  public Connection createUDPConnection(SocketAddress address) throws IOException {
    return new UDPConnection(address);
  }

  /**
   * Creates a TCP Connection from address.
   *
   * @param address the remote address this connection connects to.
   * @return a new Connection of TCP that needs to connect.
   */
  public Connection createTCPConnection(SocketAddress address) throws IOException {
    return new TCPConnection(address);
  }

  /**
   * Creates a TCP Connection from a socket. (Server usage with ServerSocket)
   *
   * @param socket an already connected socket.
   * @return a new connection of TCP that is already connected.
   */
  public Connection createSocketConnection(Socket socket) throws IOException {
    return new TCPConnection(socket);
  }


  /**
   * Returns the instance of the factory
   */
  public static ConnectionFactory getInstance() {
    if (instance == null) {
      instance = new ConnectionFactory();
    }
    return instance;
  }
}

