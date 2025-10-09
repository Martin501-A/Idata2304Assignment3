package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Represents a TCPConnection that connects to a remote server.
 * If used by a client the connection needs to connect first before running.
 * If used by a server connection is already connected given that ServerSocket.accept is used.
 */
public class TCPConnection extends Connection {
  private Socket socket;

  /**
   * Creates a new TCPConnection from a socket.
   * If socket is already connected the connection is set as connected.
   */
  public TCPConnection(SocketAddress address, Socket socket) {
    super(address);
    if (socket == null) {
      throw new IllegalArgumentException("Socket cannot be null");
    }
    this.socket = socket;
    super.connected = socket.isConnected();
  }

  /**
   * Connects to the remote socket.
   *
   * @throws ConnectException if already connected or cannot connect.
   */
  @Override
  protected void connect() throws ConnectException {
    if (super.connected) {
      throw new ConnectException("Already connected");
    }
    try {
      this.socket.connect(super.address);
    } catch (IOException e) {
     throw new ConnectException("Could not connect");
    }
  }

  /**
   * Disconnects the connection.
   *
   * @throws IOException if an error happens whilst closing.
   */
  @Override
  protected void disconnect() throws IOException {
    if (!isConnected()) {
      throw new IllegalArgumentException("Not connected");
    }
    if (!this.socket.isClosed()) {
      this.socket.close();
    }
  }

  @Override
  protected void handleInput() {

  }

  @Override
  protected void handleOutput() {

  }

  @Override
  public void run() {

  }

  @Override
  protected void step() {

  }

  @Override
  public void close() throws Exception {

  }
}
