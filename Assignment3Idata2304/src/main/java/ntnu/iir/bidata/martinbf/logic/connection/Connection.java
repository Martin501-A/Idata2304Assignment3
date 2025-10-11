package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a network connection. It automatically handles data transmission.
 *
 * @author martin barth frøseth
 */
public abstract class Connection implements Runnable, AutoCloseable {
  protected final Queue<byte[]> outgoingQueue;
  protected final Queue<byte[]> incomingQueue;
  protected SocketAddress address;

  /**
   * Constructs a Connection with specified input and output queues.
   *
   * @param address the socket address to connect to.
   */
  public Connection(SocketAddress address) {
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    this.outgoingQueue = new ConcurrentLinkedQueue<>();
    this.incomingQueue = new ConcurrentLinkedQueue<>();
    this.address = address;
  }

  /**
   * Connects to the network.
   */
  protected abstract void connect() throws ConnectException;

  /**
   * Disconnects from the network.
   */
  protected abstract void disconnect() throws IOException;

  /**
   * Returns the socket address of the connection.
   *
   * @return the socket address
   */
  public SocketAddress getAddress() {
    return address;
  }

  /**
   * Handles incoming data from the connection.
   */
  protected abstract void handleIncomingData();

  /**
   * Handles outgoing data to the connection.
   */
  protected abstract void handleOutgoingData();

  /**
   * Returns whether the connection is connected or not.
   */
  public abstract boolean isConnected();

  /**
   * Runs the connection loop while connected.
   */
  @Override
  public void run() {
    try {
      while (isConnected()) {
        step();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Receives data from the connection.
   *
   * @return the received data, or null if no data is available
   */
  public byte[] receive() {
    return incomingQueue.poll();
  }

  /**
   * Sends data to the senders queue.
   */
  public void send(byte[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Data to send cannot be null");
    }
    outgoingQueue.add(data);
  }

  /**
   * Sets a new socket address for the connection.
   *
   * @param address the new socket address.
   */
  public void setAddress(SocketAddress address) {
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    if (isConnected()) {
      throw new IllegalCallerException("Cannot change address while connection is running");
    }
    this.address = address;
  }

  /**
   * Runs one step of the connection loop.
   * Handles input and output.
   */
  protected void step() throws IOException {
    //Maybe handle exceptions here.
    if (!isConnected()) {
      throw new IllegalCallerException("Cannot step whilst not connected");
    }
      handleIncomingData();
      handleOutgoingData();

  }
}
