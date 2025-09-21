package ntnu.iir.bidata.martinbf.entity.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represents a server in a computer network.
 * The server can accept connections from clients and communicate over a socket.
 * The server can be implemented for different protocols (e.g., TCP, UDP).
 */
public interface Server {

  /**
   * Creates a socket.
   */
  void createSocket(InetAddress address, int port) throws IOException;

  /**
   * Accepts a connection from a client (for TCP).
   */
  Socket acceptConnection() throws IOException;

  /**
   * Closes the socket.
   */
  void closeSocket() throws IOException;

  /**
   * Sends data to a client.
   *
   * @param data the data to send
   */
  void sendData(String data);

  /**
   * Receives data from a client.
   *
   * @param bufferSize the size of the buffer to receive data
   */
  void recieveData(Integer bufferSize);

  /**
    * Handles client connections.
   */
  void handleClient();

}
