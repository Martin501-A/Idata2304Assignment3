package ntnu.iir.bidata.martinbf.entity;

/**
 * Represents a message sent or received by the client or Server.
 * A message has a key which identifies the type of message,
 * and a value which contains the content of the message.
 */
public class Message {
  private String key;
  private String value;

  /**
   * Constructs a Message with the given key and value.
   *
   * @param key   identifies the type of message.
   * @param value  the value in the message.
   */
  public Message(String key, String value) {
    if (key == null || key.isEmpty()) {
      throw new IllegalArgumentException("Key cannot be null or empty");
    }
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null or empty");
    }
    this.key = key;
    this.value = value;
  }

  /**
   * Returns the key of the message.
   *
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * Returns the value of the message.
   *
   * @return the value held by the message
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns a string representation of the message.
   *
   * @return the key value pair separated by a colon.
   */
  @Override
  public String toString() {
    return key + ":" + value;
  }
}
