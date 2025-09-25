package ntnu.iir.bidata.martinbf;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.server.TVServer;
import ntnu.iir.bidata.martinbf.logic.server.TVTCPServer;
import ntnu.iir.bidata.martinbf.presentation.TVServerApp;
import ntnu.iir.bidata.martinbf.presentation.TVServerController;

import java.io.IOException;
import java.util.List;

/**
 * Runs the TCP TV server application.
 */
public class Main {
  public static void main(String[] args) {
    while (true) {
      int port = TVServerApp.getPortFromConsole();
      TV tv = new TV(List.of(new Channel[]{Channel.NRK1,
                              Channel.NRK2,
                              Channel.TV2,
                              Channel.TVNorge,}));
      try (TVServer server = new TVTCPServer(port, tv)) {
        TVServerApp app = new TVServerApp(tv);
        TVServerController controller = new TVServerController(server, app);
        app.Start();
      } catch (IOException e) {
        System.out.println(e.getMessage());
        TVServerApp.printErrorMessage();
      }
    }
  }
}