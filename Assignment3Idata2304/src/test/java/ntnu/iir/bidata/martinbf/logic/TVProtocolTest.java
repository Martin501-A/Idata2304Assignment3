package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.logic.parser.TVCommand;
import ntnu.iir.bidata.martinbf.entity.TV;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests for the TVProtocol class.
 *
 * <p> The following is tested: </p>
 * <ul>
 *   <li>Sending Commands to the TV</li>
 *   <li>That expected responses happen with a given command</li>
 * </ul>
 */
public class TVProtocolTest {

  /**
   * Test that sending commands to the TV yields expected responses.
   */
  @Test
  public void thatExpectedResponsesHappenWithGivenCommand() {
    TV tv = new TV(List.of(
            new Channel[]{Channel.NRK1, Channel.TV2}));
    TVProtocol protocol = new TVProtocol(tv);
    // TV is off, expect NONE for channel up and down
    assertEquals(Channel.NONE.toString(), protocol.process(TVCommand.CHANNEL_UP));
    assertEquals(Channel.NONE.toString(), protocol.process(TVCommand.CHANNEL_DOWN));
    // Power on, expect NRK1
    assertEquals(Channel.NRK1.toString(), protocol.process(TVCommand.POWER));
    // Channel up, expect TV2
    assertEquals(Channel.TV2.toString(), protocol.process(TVCommand.CHANNEL_UP));
    // Channel up, expect NRK1
    assertEquals(Channel.NRK1.toString(), protocol.process(TVCommand.CHANNEL_UP));
    // Channel down, expect TV2
    assertEquals(Channel.TV2.toString(), protocol.process(TVCommand.CHANNEL_DOWN));
    // Power off, expect None
    assertEquals(Channel.NONE.toString(), protocol.process(TVCommand.CHANNEL_UP));
  }
}