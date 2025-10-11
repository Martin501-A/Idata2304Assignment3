package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * Represents a UDP connection which sends and receives bytes.
 *
 * @author martin barth frøseth
 */
public class UDPConnection extends Connection {
  private final DatagramSocket socket;

  /**
   * Creates the UDPConnection for a Client.
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
    if (isConnected()) {
      throw new IllegalCallerException();
    }
  }

  /**
   * Closes the connection.
   */
  @Override
  public void close() throws IOException {
    disconnect();
  }

  /**
   * Disconnects the connection.
   *
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

  /**
   * Receives data from the connection and adds it to incoming data.
   */
  @Override
  protected void handleIncomingData() {
    try {
      byte[] buffer = new byte[1024];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      this.socket.receive(packet);
      byte[] received = Arrays.copyOf(packet.getData(), packet.getLength());
      incomingQueue.offer(received);
    } catch (SocketTimeoutException e) {
      // This exception is more expected than others and should maybe have a special handling.
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Sends the next data in the outgoing queue to the receiver socket.
   */
  @Override
  protected void handleOutgoingData() {
    try {
      byte[] data = outgoingQueue.poll();
      if (data != null) {
        sendData(data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks whether the socket is connected or not.
   * Always returns true on UDP since socket is connectionless.
   */
  @Override
  public boolean isConnected() {
    return true;
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


}
