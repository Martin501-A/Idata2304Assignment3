package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.server.TVServer;
import ntnu.iir.bidata.martinbf.logic.server.TVTCPServer;
import ntnu.iir.bidata.martinbf.logic.server.TVUDPServer;
import ntnu.iir.bidata.martinbf.presentation.TVServerApp;
import ntnu.iir.bidata.martinbf.presentation.TVServerController;

import java.io.IOException;
import java.util.List;

/**
 * Runs the TCPServerFinderProtocol TV server application.
 */
public class Main {
  public static void main(String[] args) {
    while (true) {
      int port = TVServerApp.getPortFromConsole();
      System.out.println("Starting server on port " + port);
      List<Channel> channels = new java.util.ArrayList<>(List.of(Channel.values()));
      channels.remove(Channel.NONE);
      TV tv = new TV(channels);
      try (TVServer server = new TVTCPServer(port, tv)) {
        TVServerApp app = new TVServerApp(tv);
        TVServerController controller = new TVServerController(server, app);
        app.Start();
      } catch (IOException e) {
        System.out.println(e.getMessage());
        TVServerApp.printErrorMessage();
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a valid port number between 1024 and 65535.");
      }
    }
  }
}