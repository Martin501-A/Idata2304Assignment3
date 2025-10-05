package ntnu.iir.bidata.martinbf.logic.protocol;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.TVCommand;
import ntnu.iir.bidata.martinbf.logic.encoding.StringDecoder;
import ntnu.iir.bidata.martinbf.logic.encoding.StringEncoder;
import ntnu.iir.bidata.martinbf.logic.protocol.exception.IllegalFinishException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


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

  /**
   * Creates a Test Instance for the protocol.
   */
  private TVProtocol createProtocol() {
    return new TVProtocol(createTV(), new StringEncoder<>(), new StringDecoder<>(TVCommand.class));
  }
  // Positive tests

  /**
   * Tests that a protocol can be completed.
   */
  @Test
  public void protocolCanSucceed() {
    try {
      TVProtocol protocol = createProtocol();
      protocol.processData(new StringEncoder<TVCommand>().encode(new TVCommand[]{TVCommand.POWER}));
      protocol.finish();
      assertTrue(protocol.isComplete());
    } catch (IllegalFinishException e) {
      fail();
    }
  }

  /**
   * Tests that a protocol can fail.
   */
  @Test
  public void protocolCanFail() {
    TVProtocol protocol = createProtocol();
    protocol.fail();
    assertTrue(protocol.hasFailed());
  }

}