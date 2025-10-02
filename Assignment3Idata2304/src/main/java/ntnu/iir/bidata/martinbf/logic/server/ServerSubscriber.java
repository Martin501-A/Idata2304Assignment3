package ntnu.iir.bidata.martinbf.logic.server;

/**
 * Represents a subscriber to the server.
 */
public interface ServerSubscriber {

  /**
   * Called when the amount of servers changes.
   */
  public void updateAmountOfServers();

  /**
   * Adds a server to the list of servers.
   */
  public void addServer(TVServer server);
}
