package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.entities.Channel;
import ntnu.iir.bidata.martinbf.entity.entities.IPAddress;
import ntnu.iir.bidata.martinbf.entity.entities.TV;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.List;
import java.util.Scanner;

public class TVServer implements AutoCloseable {
  private TV tv;
  private ServerSocket socket;
  private volatile boolean running = true;

  /**
   * Creates a new TV server that listens on the specified port.
   *
   * @param port the port number to listen on (must be between 1024 and 65535)
   */
  public TVServer(int port) throws IOException {
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    List<Channel> channels = List.of(Channel.values());
    this.tv = new TV(channels);
    this.socket = new ServerSocket(port, 50,
            InetAddress.getByName(IPAddress.SOCKET_ADDRESS.getAddress()));
  }

  /**
   * Gets the TV instance associated with this server.
   */
  public TV getTV() {
    return this.tv;
  }

  /**
   * Gets the ServerSocket instance associated with this server.
   */
  public ServerSocket getSocket() {
    return this.socket;
  }



  /**
   * Prints the TV status to the console.
   */
  public void printTVStatus() {
    System.out.println("TV is " + (tv.getPowerStatus() ? "ON" : "OFF") +
            ", Current Channel: " + tv.getCurrentChannel());
  }

  /**
   * Takes a console input for the port number.
   * If no number is given, the default port 1238 is used.
   */
  public static int getPortFromConsole() {
    System.out.print("Enter port number (1024-65535): ");
    Scanner s = new Scanner(System.in);
    if (s.nextLine().isEmpty()) {
      return 1238;
    }
    if (!s.hasNextLine()) {
      return 1238;
    }
    return s.nextInt();
  }

  /**
   * Print error message to console.
   */
  public static void printErrorMessage() {
    System.err.println("Error has happened please try to input port again to restart server.");
  }

  /**
   * Starts the TV server to accept incoming connections.
   */
  public void start() throws IOException {
    System.out.println("Server started, waiting for connections...");
    while (running) {
        new TVTCPThread(socket.accept(), tv).start();
        printTVStatus();
    }
  }

  /**
   * Closes the server socket and stops the server.
   */
  @Override
  public void close() throws IOException {
    running = false;
    if (socket != null && !socket.isClosed()) {
      socket.close();
    }
  }
}
