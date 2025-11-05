package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.TV;

/**
 * Is Responsible for doing the command sent from the remote to the TV.
 */
public class TVCommandActor {
  private TV tv;

  /**
   * Creates a TVCommandActor for a TV.
   *
   * @param tv the tv to receive commands.
   */
  public TVCommandActor(TV tv) {
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.tv = tv;
  }

  /**
   * Handles the given command given by the Remote
   */
  public void HandleCommand(TVMessage command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    switch (command) {
      case POWER:
        this.tv.power();
        break;
      case CHANNEL_UP:
        this.tv.nextChannel();
        break;
      case CHANNEL_DOWN:
        this.tv.previousChannel();
        break;
    }
  }
}
