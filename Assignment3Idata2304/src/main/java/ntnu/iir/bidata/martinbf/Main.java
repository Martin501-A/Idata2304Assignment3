package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.logic.server.TVServer;
import ntnu.iir.bidata.martinbf.logic.server.TVTCPServer;
import ntnu.iir.bidata.martinbf.presentation.TVServerApp;

import java.io.IOException;

/**
 * Runs the TCP TV server application.
 */
public class Main {
  public static void main(String[] args) {
    while (true) {

      TVServerApp app = new TVServerApp();
      int port = app.getPortFromConsole();
      System.out.println("Server starting on port: " + port);
      try (TVTCPServer tvServer = new TVTCPServer(port)) {
        tvServer.start();
      } catch (IOException e) {
        System.out.println(e.getMessage());
        TVTCPServer.printErrorMessage();
      }
    }
  }
}