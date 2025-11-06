package ntnu.iir.bidata.martinbf.logic.iodata;

import ntnu.iir.bidata.martinbf.logic.TVCommandActor;
import ntnu.iir.bidata.martinbf.logic.TVMessage;

import java.util.Optional;

/**
 * Is responsible for handling the byte input from the server.
 */
public class TVHandler implements DataHandler {
  private TVCommandActor actor;

  /**
   * Instantiates the receiver of TVData.
   */
  public TVHandler(TVCommandActor actor) {
    if (actor == null) {
      throw new IllegalArgumentException("Actor cannot be null");
    }
    this.actor = actor;
  }


  /**
   * Receives the data, turns it into commands and sends it into the actor.
   *
   * @param data the data to receive from TCP/UDP.
   */
  @Override
  public void handleReceivedData(byte[] data) {
    if (data.length == 0) {
      throw new IllegalArgumentException("empty data was sent");
    }
    for (byte bMsg: data) {
      Optional<TVMessage> msg = this.getMessage(bMsg);
      msg.ifPresent(tvMessage -> this.actor.HandleCommand(tvMessage));
    }
  }

  /**
   * Returns the message corresponding to the byte number.
   *
   * @param bMsg a number representing a TVMessage.
   */
  public Optional<TVMessage> getMessage(byte bMsg) {
    return switch (bMsg) {
      case 1 -> {
        yield Optional.of(TVMessage.POWER);
      }
      case 2 -> {
        yield Optional.of(TVMessage.CHANNEL_UP);
      }
      case 3 -> {
        yield Optional.of(TVMessage.CHANNEL_DOWN);
      }
      default -> {
        yield Optional.empty();
      }
    };
  }
}
