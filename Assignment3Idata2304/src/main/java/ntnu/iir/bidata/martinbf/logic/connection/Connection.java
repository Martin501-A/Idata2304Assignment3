package ntnu.iir.bidata.martinbf.logic.connection;

import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a network connection. It automatically handles data transmission.
 */
public abstract class Connection implements Runnable, AutoCloseable {
  private final Queue<byte[]> inputQueue;
  private final Queue<byte[]> outputQueue;
  private SocketAddress address;
  private volatile boolean running;


  /**
   * Constructs a Connection with specified input and output queues.
   *
   * @param address the socket address to connect to
   */
  public Connection(SocketAddress address) {
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    this.inputQueue = new ConcurrentLinkedQueue<>();
    this.outputQueue = new ConcurrentLinkedQueue<>();
    this.address = address;
    this.running = false;
  }

  /**
   * Connects to the network.
   */
  protected abstract void connect();

  /**
   * Disconnects from the network.
   */
  protected abstract void disconnect();

  /**
   * Sends data through the connection.
   */
  public void send(byte[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Data to send cannot be null");
    }
    outputQueue.add(data);
  }

  /**
   * Places received data into the input queue.
   *
   * @param data the data to place in the input queue
   */
  protected void placeInInputQueue(byte[] data) {
    inputQueue.add(data);
  }

  /**
   * Retrieves and removes data from the output queue.
   *
   * @return the data to be sent, or null if no data is available
   */
  protected byte[] retrieveFromOutputQueue() {
    return outputQueue.poll();
  }

  /**
   * Receives data from the connection.
   *
   * @return the received data, or null if no data is available
   */
  public byte[] receive() {
    return inputQueue.poll();
  }

  /**
   * Returns the socket address of the connection.
   *
   * @return the socket address
   */
  public SocketAddress getAddress() {
    return address;
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
    if (this.running) {
      throw new IllegalCallerException("Cannot change address while connection is running");
    }
    this.address = address;
  }
}
