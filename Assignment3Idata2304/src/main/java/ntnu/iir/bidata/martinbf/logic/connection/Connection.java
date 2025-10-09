package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a network connection. It automatically handles data transmission.
 */
public abstract class Connection implements Runnable, AutoCloseable {
  protected final Queue<byte[]> inputQueue;
  protected final Queue<byte[]> outputQueue;
  protected SocketAddress address;
  protected volatile boolean connected;


  /**
   * Constructs a Connection with specified input and output queues.
   *
   * @param address the socket address to connect to.
   */
  public Connection(SocketAddress address) {
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    this.inputQueue = new ConcurrentLinkedQueue<>();
    this.outputQueue = new ConcurrentLinkedQueue<>();
    this.address = address;
    this.connected = false;
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
  protected abstract void handleInput();

  /**
   * Handles outgoing data to the connection.
   */
  protected abstract void handleOutput();

  /**
   * Returns whether the connection is connected.
   */
  public boolean isConnected() {
    return this.connected;
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
   * Runs the connection.
   */
  @Override
  public abstract void run();

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
   * Sends data to the senders queue.
   */
  public void send(byte[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Data to send cannot be null");
    }
    outputQueue.add(data);
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
    if (this.connected) {
      throw new IllegalCallerException("Cannot change address while connection is running");
    }
    this.address = address;
  }

  /**
   * The step handles a runloop of input and output from the connection.
   */
  protected abstract void step();

}
