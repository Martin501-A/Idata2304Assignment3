package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.TVSubscriber;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the possible handling of TV server from a user.
 */
public class TVServerApp implements TVSubscriber {
  private TV tv;
  private TVServerController controller;

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
    this.tv.subscribe(this);
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
}
