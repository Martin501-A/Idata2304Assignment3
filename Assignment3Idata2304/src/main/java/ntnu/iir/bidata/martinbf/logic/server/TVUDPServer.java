package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.TVProtocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Represents a UDP Server for the TV.
 */
public class TVUDPServer implements TVServer {
  private final TV tv;
  private DatagramSocket socket;
  private volatile boolean running = true;

  /**
   * Creates a new TVUDPServer.
   */
  public TVUDPServer(int port, TV tv) throws IOException {
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    if (tv == null) {
      throw new IllegalArgumentException("TV cannot be null");
    }
    this.tv = tv;
    this.socket = new DatagramSocket(port,
            InetAddress.getByName(IPAddress.SOCKET_ADDRESS.getAddress()));
  }


  /**
   * Starts the UDP server to listen for incoming commands.
   */
  @Override
  public void start() throws IOException {
    while (this.running) {
      String response = recievePacket();
      sendResponsePacket(response);
    }
  }

  /**
   * Receives a UDP packet and processes the command.
   */
  private String recievePacket() {
    byte[] buffer = new byte[256];
    String response = "";
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    try {
      socket.receive(packet);
      String data = new String(packet.getData(), StandardCharsets.UTF_8).trim();
      response = new TVProtocol(this.tv)
              .process(Command.valueOf(data));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  /**
   * Sends a response UDP packet back to the client.
   */
  private void sendResponsePacket(String response) {
    byte[] responseData = response.getBytes(StandardCharsets.UTF_8);
    DatagramPacket responsePacket = new DatagramPacket(
            responseData, responseData.length);
    try {
      socket.send(responsePacket);
    } catch (IOException e) {
      e.printStackTrace();
  }


  /**
   * Closes the UDP server socket.
   */
  @Override
  public void close() throws IOException {
    this.running = false;
    if (!socket.isClosed()) {
      socket.close();
    }
  }
}
