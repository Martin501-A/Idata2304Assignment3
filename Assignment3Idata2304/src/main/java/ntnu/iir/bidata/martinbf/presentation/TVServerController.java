package ntnu.iir.bidata.martinbf.presentation;

import ntnu.iir.bidata.martinbf.logic.server.TVServer;

import java.io.IOException;

/**
 * Handles User input for the TVServerApp.
 */
public class TVServerController {
  private final TVServer server;
  private TVServerApp app;

  /**
   * Creates a new TVServerController.
   *
   * @param server the TV server to control.
   * @param app    the TV server app to control.
   */
  public TVServerController(TVServer server, TVServerApp app) {
    if (server == null) {
      throw new IllegalArgumentException("TV, server and app cannot be null");
    }
    if (app == null) {
      throw new IllegalArgumentException("TV, server and app cannot be null");
    }
    this.server = server;
    this.app = app;
    this.app.setController(this);
  }

  /**
   * Starts the TV server.
   */
  public void startServer() throws IOException {
    this.server.start();
  }
}
