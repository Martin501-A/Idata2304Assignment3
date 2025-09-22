package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.logic.TVServer;

import java.io.IOException;

/**
 * Runs the TCP TV server application.
 */
public class Main {
  public static void main(String[] args) {
    while (true) {
      int port = TVServer.getPortFromConsole();
      System.out.println("Server starting on port: " + port);
      try (TVServer tvServer = new TVServer(port)) {
        tvServer.start();
      } catch (IOException e) {
        TVServer.printErrorMessage();
      }
    }
  }
}