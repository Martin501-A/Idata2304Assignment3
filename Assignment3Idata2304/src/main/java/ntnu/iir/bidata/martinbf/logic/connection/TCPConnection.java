package ntnu.iir.bidata.martinbf.logic.connection;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * Represents a TCPConnection that connects to a remote server.
 * If used by a client the connection needs to connect first before running.
 * If used by a server connection is already connected given that ServerSocket.accept is used.
 *
 * @author martin barth frøseth
 */
public class TCPConnection extends Connection {
  private final Socket socket;
  private OutputStream out;
  private InputStream in;
  private Thread readerThread;
  private Thread writerThread;
  private volatile boolean running;

  /**
   * Creates a new TCPConnection from an address.
   */
  public TCPConnection(SocketAddress address) {
    super(address);
    this.socket = new Socket();
  }

  /**
   * Creates a new TCPConnection from a socket.
   * If socket is already connected the connection is set as connected.
   */
  public TCPConnection(Socket socket) {
    super(socket.getRemoteSocketAddress());
    this.socket = socket;
  }

  /**
   * Connects to the remote socket.
   *
   * @throws ConnectException if already connected or cannot connect.
   */
  @Override
  public void connect() throws ConnectException {
    try {
      if (!isConnected()) {
        this.socket.connect(super.address);
      }
    } catch (IOException e) {
      try {
        this.socket.close();
      } catch (IOException ie) {
        //Add handling in case closing also fails.
        throw new ConnectException("Failed to Close connection.");
      }
      throw new ConnectException("Could not connect");
    }
  }

  /**
   * Closes the connection safely.
   *
   * @throws IOException if an error happens when closing socket.
   */
  @Override
  public void close() throws IOException {
    disconnect();
  }

  /**
   * Disconnects the connection.
   *
   * @throws IOException if an error happens whilst closing.
   */
  @Override
  public void disconnect() throws IOException {
    this.running = false;
    this.socket.close();
  }

  /**
   * Handles incoming data from the connection and adds it to our incomingQueue.
   */
  @Override
  protected void handleIncomingData() {
    try {
      byte[] buffer = new byte[1024];
      int bytesRead = in.read(buffer);
      if (bytesRead == -1) {
        running = false;
      }
      byte[] data = Arrays.copyOf(buffer, bytesRead);
      super.incomingQueue.add(data);
      super.handler.handle(this);
    } catch (SocketTimeoutException e) {
      //Handle this when applicable
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Handles outgoing data by taking the next member of the outgoing queue,
   */
  @Override
  protected void handleOutgoingData() {
    try {
      byte[] data = super.outgoingQueue.take();
      if (data != null) {
        sendData(data);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks whether the socket is connected or not.
   */
   @Override
   public boolean isConnected() {
     return this.socket.isConnected();
   }

  /**
   * Sends data over the connection.
   */
  private void sendData(byte[] data) throws IOException {
    try {
      if (data == null) {
        throw new IllegalArgumentException("Data is null");
      }
      out.write(data);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs the TCP connection.
   */
  @Override
  public void run() {
    try {
      this.in = new BufferedInputStream(
              this.socket.getInputStream());
      this.out = new BufferedOutputStream(
              this.socket.getOutputStream());
      this.running = true;

      readerThread = new Thread(this::readerLoop, "TCP-Reader");
      readerThread.start();

      writerThread = new Thread(this::writerLoop, "TCP-Writer");
      writerThread.start();

      readerThread.join();
      writerThread.join();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Runs the incoming data loop.
   */
  public void readerLoop() {
    while (this.running && this.socket.isConnected()) {
      handleIncomingData();
    }
  }

  /**
   * Runs the outgoing data loop.
   */
  public void writerLoop() {
    while (this.running && this.socket.isConnected()) {
      handleOutgoingData();
    }
  }
}
