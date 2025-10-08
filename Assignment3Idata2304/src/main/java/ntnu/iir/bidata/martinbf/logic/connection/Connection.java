package ntnu.iir.bidata.martinbf.logic.connection;

/**
 * Represents a network connection. It automatically handles data transmission.
 */
public interface Connection {

  /**
   * Connects to the network.
   */
  void connect();

  /**
   * Connects to the network with an address.
   */
  void connect(String address, int port);

  /**
   * Sends data over the connection.
   */
  void send(byte[] data);

  /**
   * Receives data from the connection.
   */
  byte[] receive();

  /**
   * Disconnects from the network.
   */
  void disconnect();
}
