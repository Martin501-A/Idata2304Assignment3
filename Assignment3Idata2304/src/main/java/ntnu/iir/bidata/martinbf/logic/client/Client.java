package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;

import java.util.List;
import java.util.Map;

/**
 * Represents a client in the system.'
 *
 */
public class Client {
  private final Map<String, Connection> connections;
  private final DecoderService decoderService;
  private final EncoderService encoderService;
  private final MessageResolverService messageResolverService;
  private boolean isRunning;


  /**
   * Constructs a Client with the specified connection.
   *
   * @param connections            the map of available connections.
   * @param decoderService         the decoder service for decoding messages.
   * @param encoderService         the encoder service for encoding messages.
   * @param messageResolverService the message resolver service for handling messages.
   */
  public Client(Map<String, Connection> connections,
                DecoderService decoderService,
                EncoderService encoderService,
                MessageResolverService messageResolverService) {
    this.connections = connections;
    this.decoderService = decoderService;
    this.encoderService = encoderService;
    this.messageResolverService = messageResolverService;
  }

  /**
   * Starts the client by establishing the connection.
   */
  public void start() {
    while (isRunning) {
      for (Connection connection : connections.values()) {
        runClientLoop(connection);
      }
    }
  }

  /**
   * Runs the main client loop for the given connection.
   *
   * @param connection the connection to use.
   */
  private void runClientLoop(Connection connection) {
    byte[] receivedData = connection.receive();
    List<Message> messages = decoderService.decode(receivedData);
    messageResolverService.resolve(messages);
    List<Message> responses = messageResolverService.getResponses();
    byte[] encodedResponses = encoderService.encode(responses);
    connection.send(encodedResponses);
  }

  /**
   * Stops the client and stops its connections.
   */
  public void stop() {
    this.isRunning = false;
    for (Connection connection : connections.values()) {
      connection.disconnect();
    }
  }
}
