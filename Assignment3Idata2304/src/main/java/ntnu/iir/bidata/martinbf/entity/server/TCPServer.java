package ntnu.iir.bidata.martinbf.entity.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * Represents a TCP server in a computer network.
 * The TCP server can accept connections from clients and communicate over a TCP socket.
 */
public class TCPServer extends Thread {
  private final ServerSocket socket;

  /**
   * Instantiates a new TCP server.
   */
  public TCPServer() throws IOException {
    this.socket = new ServerSocket();
  }


  /**
   * Accepts a connection from a client.
   *
   * @return the accepted socket
   * @throws IOException if an I/O error occurs when waiting for a connection
   */
  public Socket acceptConnection() throws IOException {
    return this.socket.accept();
  }



  /**
   * Closes the TCP socket.
   */
  public void closeSocket() throws IOException {
      this.socket.close();
  }

  /**
   * Sends data to a client over TCP.
   *
   * @param socket the socket to send data to
    * @param data the data to send
   */
  public void sendData(Socket socket, byte[] data) {
    try (OutputStream outputStream = socket.getOutputStream()) {
      outputStream.write(data);
      outputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Receives data from a client over TCP.
   *
   * @param socket the socket to receive data from
   * @return the received data
   */
  public byte[] receiveData(Socket socket) {

    }

  /**
   * Gets the TCP socket.
   *
   * @return the TCP socket
   */
  public ServerSocket getSocket() {
    return this.socket;
  }

}
