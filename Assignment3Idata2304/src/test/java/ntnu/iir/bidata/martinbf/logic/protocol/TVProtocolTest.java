package ntnu.iir.bidata.martinbf.logic.protocol;

import static org.junit.jupiter.api.Assertions.*;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


/**
 * Tests the TVProtocol class.
 *
 * <p>The following aspects are tested:<p>
 *
 * <p>Positive tests:</p>
 * <ul>
 *   <li>That a protocol can be completed</li>
 *   <li>That a protocol can fail</li>
 *   <li>That a protocol can be cancelled</li>
 *   <li>That a protocol can be retried</li>
 * </ul>
 * <p>Negative tests:</p>
 * <ul>
 *   <li>That a protocol cannot be retried if it is not failed or completed</li>
 *   <li>That the protocol cannot process if it has failed or completed</li>
 *   <li>That the protocol cannot fail if completed</li>
 *   <li>That the protocol cannot complete if failed</li>
 * </ul>
 */
public class TVProtocolTest {

  /**
   * Creates a Test Instance for the protocol.
   *
   * @return a TV instance with one channel
   */
  private TV createTV() {
    ArrayList<Channel> channels = new ArrayList<>();
    channels.add(Channel.NRK1);
    return new TV(channels);
  }

  // Positive tests
  @Test
  public void protocolCanSucceed() {
    TVProtocol protocol = new TVProtocol(createTV());
    //TODO Testing TVProtocol
  }

}