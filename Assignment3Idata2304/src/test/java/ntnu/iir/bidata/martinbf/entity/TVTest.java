package ntnu.iir.bidata.martinbf.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Test the TV Class</p>
 * <p>The Following is tested:</p>
 * <p>Positive Tests: </p>
 * <ul>
 *   <li>That Tv When off Can only return channels or power status</li>
 *   <li>That Tv When on Can change channels and return current channel</li>
 * </ul>
 * <p>Negative Tests: </p>
 * <ul>
 *   <li>That a TV cannot be constructed with null or empty channels</li>
 * </ul>
 */
public class TVTest {
  // Positive tests

  /**
   * That Tv When off Can only return channels or power status
   */
  @Test
  public void thatTvWhenOffCanOnlyReturnChannelsOrPowerStatus() {
    List<Channel> channels = List.of(Channel.NRK1, Channel.NRK2, Channel.TV2);
    TV tv = new TV(channels);
    assertFalse(tv.getPowerStatus());
    assertEquals(Channel.NONE, tv.getCurrentChannel());
    tv.nextChannel();
    assertEquals(Channel.NONE, tv.getCurrentChannel());
    tv.previousChannel();
    assertEquals(Channel.NONE, tv.getCurrentChannel());
  }

  /**
   * That Tv When on Can change channels and return current channel
   */
  @Test
  public void thatTvWhenOnCanChangeChannelsAndReturnCurrentChannel() {
    List<Channel> channels = List.of(Channel.NRK1, Channel.NRK2, Channel.TV2);
    TV tv = new TV(channels);
    tv.power();
    assertTrue(tv.getPowerStatus());
    assertEquals(Channel.NRK1, tv.getCurrentChannel());
    tv.nextChannel();
    assertEquals(Channel.NRK2, tv.getCurrentChannel());
    tv.nextChannel();
    assertEquals(Channel.TV2, tv.getCurrentChannel());
    tv.nextChannel();
  }

  /**
   * That a TV cannot be constructed with null or empty channels.
   */
  @Test
  public void thatTVCannotBeConstructedWithNullOrEmptyChannels() {
    assertThrows(IllegalArgumentException.class, () -> new TV(null));
    assertThrows(IllegalArgumentException.class, () -> new TV(new ArrayList<>()));
  }
}