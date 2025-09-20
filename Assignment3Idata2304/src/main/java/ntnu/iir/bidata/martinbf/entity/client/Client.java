package ntnu.iir.bidata.martinbf.entity.client;

/**
 * Represents a client in a computer network.
 * S is the type of socket used by the client (e.g., Socket for TCP, DatagramSocket for UDP).
 * The client can connect to a server and communicate over a socket.
 */
public interface Client {

  /**
   * Creates a socket.
   */
  void createSocket();

  /**
   * Connects to a server.
   *
   * @param host the server's hostname or IP address
   * @param port the server's port number
   */
  void connect(String host, int port);

  /**
   * Closes the socket.
   */
  void closeSocket();

  /**
   * Sends data to the server.
   */
  void sendData(String data);

  /**
   * Receives data from the server.
   *
   * @param bufferSize the size of the buffer to receive data
   */
  void recieveData(Integer bufferSize);

}
