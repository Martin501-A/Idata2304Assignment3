package ntnu.iir.bidata.martinbf.logic.client;

/**
 * Represents a message sent or received by the client or Server.
 * A message has a key which identifies the type of message,
 * and a value which contains the content of the message.
 */
public class Message {
  private String key;
  private String data;

  /**
   * Constructs a Message with the given key and value.
   *
   * @param key   identifies the type of message.
   * @param data  the data in the message.
   */
  public Message(String key, String data) {
    if (key == null || key.isEmpty()) {
      throw new IllegalArgumentException("Key cannot be null or empty");
    }
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null or empty");
    }
    this.key = key;
    this.data = data;
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
   * Returns the data of the message.
   *
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * Returns a string representation of the message.
   */
  @Override
  public String toString() {
    return key + ":" + data;
  }
}
