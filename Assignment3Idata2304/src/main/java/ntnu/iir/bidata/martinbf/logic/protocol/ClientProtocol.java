package ntnu.iir.bidata.martinbf.logic.protocol;

/**
 * Represents a client protocol in the system.
 * The client protocol as well as doing what a protocol does, also has to have a first message.
 */
public interface ClientProtocol extends Protocol {

  /**
   * Returns the first message to be sent by the client.
   *
   * @return the first message
   */
  String getFirstMessage();
}
