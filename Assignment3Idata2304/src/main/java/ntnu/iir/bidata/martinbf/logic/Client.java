package ntnu.iir.bidata.martinbf.logic;

import ntnu.iir.bidata.martinbf.logic.connection.Connection;
import ntnu.iir.bidata.martinbf.logic.services.MessageHandler;
import ntnu.iir.bidata.martinbf.logic.services.decoder.DecoderService;
import ntnu.iir.bidata.martinbf.logic.services.encoder.EncoderService;

import java.util.Map;

/**
 * Represents a client in the system.'
 *
 */
public class Client {
  private final Map<String, Connection> connections;
  private final DecoderService decoderService;
  private final EncoderService encoderService;
  private final MessageHandler messageHandler;
  private boolean isRunning;


  /**
   * Constructs a Client with the specified connection.
   *
   * @param connections            the map of available connections.
   * @param decoderService         service for decoding messages.
   * @param encoderService         service for encoding messages.
   */

  public Client(Map<String, Connection> connections,
                DecoderService decoderService,
                EncoderService encoderService,
                MessageHandler messageHandler) {
    this.connections = connections;
    this.decoderService = decoderService;
    this.encoderService = encoderService;
    this.messageHandler = messageHandler;
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
    byte[] responses = messageHandler.handleMessage(receivedData);
    connection.send(responses);
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
