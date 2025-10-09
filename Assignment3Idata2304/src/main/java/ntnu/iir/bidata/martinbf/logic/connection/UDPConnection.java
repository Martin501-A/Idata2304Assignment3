package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a UDP connection which sends and receives bytes.
 */
public class UDPConnection extends Connection {
  private final DatagramSocket socket;

  /**
   * Creates the UDPConnection for a Client
   *
   * @param address the address for the remote socket.
   * @throws SocketException if a general socket cannot connect to any address and port.
   */
  public UDPConnection(SocketAddress address) throws SocketException {
    super(address);
    this.socket = new DatagramSocket();
    this.socket.setSoTimeout(10);
  }

  /**
   * Connects to the remote.
   * UDP does not connect to other sockets.
   * This method only switches a variable if it is false.
   *
   * @throws ConnectException if already connected.
   */
  @Override
  protected void connect() throws ConnectException {
    if (this.connected) {
      throw new  ConnectException("Already connected");
    }
    this.connected = true;
  }

  @Override
  protected void disconnect() throws IOException {
    if (!isConnected()) {
      throw new  ConnectException("Not connected");
    }
    this.connected = false;
    if (!this.socket.isClosed()) {
      this.socket.close();
    }
  }

  /**
   * Handles input for sending data which takes a peek at input and
   * sends the data if it exists..
   */
  @Override
  protected void handleInput() {
    byte[] data = inputQueue.poll();
    if (data != null) {
      try {
        sendData(data);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Sends Data over the connection.
   *
   * @param data the bytes to send.
   */
  private void sendData(byte[] data) throws IOException {
    if (data == null) {
      throw new IllegalArgumentException("data is null");
    }
    DatagramPacket packet = new DatagramPacket(data, data.length, getAddress());
    socket.send(packet);
  }

  /**
   * Receives data from the connection and puts it into outputQueue.
   */
  @Override
  protected void handleOutput() {
    try {
      byte[] buffer = new byte[1024];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      this.socket.receive(packet);
      byte[] received = Arrays.copyOf(packet.getData(), packet.getLength());
      outputQueue.offer(received);
    } catch (SocketTimeoutException e) {
      // This exception is more expected than others and should maybe have a special handling.
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs a runTimeLoop for the connection if it is connected.
   * Does 1 step each time it is called.
   */
  @Override
  public void run() {
    try {
      if (isConnected()) {
        step();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Handles input and output.
   */
  @Override
  protected void step() {
    //Maybe handle exceptions here.
    handleInput();
    handleOutput();
  }

  /**
   * Closes the connection.
   *
   * @throws Exception Exceptions happen when closing connection.
   */
  @Override
  public void close() throws Exception {
    disconnect();
  }
}
