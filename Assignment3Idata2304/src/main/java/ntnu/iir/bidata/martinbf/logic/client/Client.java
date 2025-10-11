package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.Message;
import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.services.MessageResolver.MessageHandlerService;
import ntnu.iir.bidata.martinbf.logic.services.decoder.DecoderService;
import ntnu.iir.bidata.martinbf.logic.services.encoder.EncoderService;

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
  private final MessageHandlerService messageHandlerService;
  private boolean isRunning;


  /**
   * Constructs a Client with the specified connection.
   *
   * @param connections            the map of available connections.
   * @param decoderService         service for decoding messages.
   * @param encoderService         service for encoding messages.
   * @param messageHandlerService service for handling messages.
   */

  public Client(Map<String, Connection> connections,
                DecoderService decoderService,
                EncoderService encoderService,
                MessageHandlerService messageHandlerService) {
    this.connections = connections;
    this.decoderService = decoderService;
    this.encoderService = encoderService;
    this.messageHandlerService = messageHandlerService;
  }

  /**
   * Starts the client and begins processing Connections.
   */
  public void start() {
    while (isRunning) {
      for (Connection connection : connections.values()) {
        runConnectionLoop(connection);
      }
    }
  }

  /**
   * Runs the main client loop for the given connection.
   *
   * @param connection the connection to use.
   */
  private void runConnectionLoop(Connection connection) {
    byte[] receivedData = connection.receive();
    List<Message> messages = decoderService.decode(receivedData);
    List<Message> responses = messageHandlerService.resolve(messages);
    byte[] encodedResponses = encoderService.encode(responses);
    connection.send(encodedResponses);
  }

  /**
   * Stops the client and stops its connections.
   */
  public void stop() {
    this.isRunning = false;
    for (Connection connection : connections.values()) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
