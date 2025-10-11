package ntnu.iir.bidata.martinbf.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>Tests the Message class</p>
 * <p>The following is tested: </p>
 * <p>Positive tests: </p>
 * <ul>
 *   <li>That a message can be made.</li>
 *   <li>That a message key representation is correct</li>
 * </ul>
 * <p>Negative tests: </p>
 * <ul>
 *   <li>That key cannot be null or empty</li>
 *   <li>That value cannot be null or empty</li>
 * </ul>
 */
public class MessageTest {

  //Positive tests

  /**
   * Tests correct creation.
   */
  @Test
  public void correctCreation() {
    String key = "test";
    String value = "test";
    String combine = key + ":" + value;
    Message message = new Message(key, value);
    assertEquals(key, message.getKey());
    assertEquals(value, message.getValue());
    assertEquals(combine, message.toString());
  }

  //Negative tests

  /**
   * That key cannot be null
   */
  @Test
  public void noNullKey() {
    try {
      Message message = new Message(null, "test");
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

  /**
   * That no value cannot be null
   */
  @Test
  public void noNullValue() {
    try {
      Message message = new Message("test", null);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }

}