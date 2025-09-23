package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.entities.Command;

/**
 * Client for sending commands to a TV.
 */
public interface TVRemoteClient {

  /**
   * Sends a command to the TV.
   *
   * @param command the command to send.
   */
  void protocol(Command command);
}
