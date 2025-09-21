package ntnu.iir.bidata.martinbf.entity.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a TCP server in a computer network.
 * The TCP server can accept connections from clients and communicate over a TCP socket.
 */
public class TCPServer implements Server {
  private ServerSocket socket;

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
    try (ServerSocket socket = new ServerSocket(port) ) {;
      this.socket = socket;
    }
  }

  @Override
  public Socket acceptConnection() throws IOException {
    return this.socket.accept();
  }

  /**
   * Closes the TCP socket.
   */
  @Override
  public void closeSocket() throws IOException {
      this.socket.close();
  }

  @Override
  public void sendData(String data) {

  }

  @Override
  public void recieveData(Integer bufferSize) {

  }

  @Override
  public void handleClient() {

  }

  @Override



  /**
   * Gets the TCP socket.
   *
   * @return the TCP socket
   */
  public Socket getSocket() {
    return this.socket;
  }

}
