package ntnu.iir.bidata.martinbf.entity;

/**
 * Represents a server in a computer network.
 * The server can accept connections from clients and communicate over a socket.
 * The server can be implemented for different protocols (e.g., TCP, UDP).
 */
public interface Server<S> {

  /**
   * Creates a socket.
   */
  void createSocket();

  /**
   * Binds the socket to a specific port.
   *
   * @param port the port number to bind the socket to
   */
  void bind(int port);

  /**
   * Listens for incoming connections (for TCP).
   *
   */
  void listen();

  /**
   * Accepts a connection from a client (for TCP).
   */
  void acceptConnection();

  /**
   * Closes the socket.
   */
  void closeSocket();

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

  /**
   * Gets the socket used by the server.
   *
   * @return the socket
   */
  S getSocket();


}
