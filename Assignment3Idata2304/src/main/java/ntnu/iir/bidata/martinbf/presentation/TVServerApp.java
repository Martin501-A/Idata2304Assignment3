package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.TVSubscriber;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;
import ntnu.iir.bidata.martinbf.logic.server.ServerSubscriber;
import ntnu.iir.bidata.martinbf.logic.server.TVServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the possible handling of TV server from a user.
 */
public class TVServerApp implements TVSubscriber, ServerSubscriber {
  private TV tv;
  private TVServerController controller;
  private List<TVServer> servers;

  /**
   * Instantiates the TV server for a TV.
   *
   * @param tv the tv to get responses for.
   */
  public TVServerApp(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.tv = tv;
    this.servers = new ArrayList<>();
    this.tv.subscribe(this);
  }

  /**
   * Handles the amount of servers the user wants in the network.
   */
  public static List<IPAddress> getAmountOfServers() {
    try {
      int lowerLimit = 1;
      int maxLimit = IPAddress.values().length;
      List<IPAddress> addresses = new ArrayList<>();
      System.out.println("Enter amount of servers to start (" + lowerLimit + "-" + maxLimit + "): ");
      Scanner scanner = new Scanner(System.in);
      int amount = Integer.parseInt(scanner.nextLine());
      if (amount < lowerLimit || amount > maxLimit) {
        throw new IllegalArgumentException("Invalid amount put in");
      }
      for (int i = lowerLimit; i <= amount; i++) {
        addresses.add(IPAddress.values()[i - 1]);
      }
      System.out.println("Starting " + amount + " servers on addresses: " + Arrays.toString(addresses.toArray()));
      return addresses;
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input. Please enter a numeric value.");
      return getAmountOfServers();
    }
  }

  /**
   * Starts the TV server application.
   */
  public void Start() throws IOException {
    if (controller == null) {
      throw new IllegalStateException("Controller cannot be null");
    }
    System.out.println("Server starting, waiting for connections...");
    this.controller.startServer();
  }

  /**
   * Prints the TV status to the console.
   */
  public void printTVStatus() {
    System.out.println("TV is " + (tv.getPowerStatus() ? "ON" : "OFF") +
            ", Current Channel: " + tv.getCurrentChannel());
    System.out.println("Channels available: " + tv.getChannels());
  }

  /**
   * Gets the TV instance associated with this server.
   */
  public TV getTV() {
    return this.tv;
  }

  /**
   * Takes a console input for the port number.
   * If no number is given, the default port 1238 is used.
   */
  public static int getPortFromConsole() {
    boolean success = false;
    int port = 1238;
    Scanner scanner = new Scanner(System.in);
    while (!success) {
      System.out.print("Enter port number (1024-65535) or press Enter for default (1238): ");
      String input = scanner.nextLine();
      if (input.isEmpty()) {
        success = true;
      } else {
        try {
          port = Integer.parseInt(input);
          if (port < 1024 || port > 65535) {
            System.out.println("Please enter a valid port number between 1024 and 65535.");

          } else {
            success = true;
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a numeric port number.");
        }
      }
    }
    System.out.println("Servers will start on port: " + port);
    return port;
  }

  /**
   * Print error message to console.
   */
  public static void printErrorMessage() {
    System.err.println("Error has happened please try to input port again to restart server.");
  }

  /**
   * Sets the controller for this app.
   */
  public void setController(TVServerController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.controller = controller;
  }

  /**
   * Gets the controller for this app.
   */
  public TVServerController getController() {
    return this.controller;
  }

  /**
   * Updates the TV status when notified.
   */
  @Override
  public void update() {
    printTVStatus();
  }

  /**
   * Updates the amount of servers when notified.
   */
  @Override
  public void updateAmountOfServers() {
    System.out.println("Number of servers: " + this.servers.size());
  }

  /**
   * Adds a server to the list of servers.
   */
  public void addServer(TVServer server) {
    if (server == null) {
      throw new IllegalArgumentException("Server cannot be null");
    }
    this.servers.add(server);
    updateAmountOfServers();
  }
}
