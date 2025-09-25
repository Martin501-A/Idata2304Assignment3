package ntnu.iir.bidata.martinbf.logic.server;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.entity.TV;
import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.TVProtocol;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a UDP Server for the TV.
 */
public class TVUDPServer implements TVServer {

  private record DataAndSocket (String data, SocketAddress socketAddress) {
    public DataAndSocket {
      Objects.requireNonNull(data);
      Objects.requireNonNull(socketAddress);
    }
  }
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
            InetAddress.getByName(IPAddress.ServerAddress.getAddress()));
  }


  /**
   * Starts the UDP server to listen for incoming commands.
   */
  @Override
  public void start() throws IOException {
    while (this.running) {
      DataAndSocket response = recievePacket();
      sendResponsePacket(response);
    }
  }

  /**
   * Receives a UDP packet and processes the command.
   */
  private DataAndSocket recievePacket() {
    byte[] buffer = new byte[256];
    String response = "";
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    try {
      socket.receive(packet);
      byte[] receivedData = Arrays.copyOf(packet.getData(), packet.getLength());
      String data = new String(receivedData, StandardCharsets.UTF_8).trim();
      response = new TVProtocol(this.tv)
              .process(Command.valueOf(data));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      response = Channel.NONE.toString();
    }
    return new DataAndSocket(response,
            packet.getSocketAddress());
  }

  /**
   * Sends a response UDP packet back to the client.
   */
  private void sendResponsePacket(DataAndSocket response) {
    try {
    SocketAddress remoteAddress = new InetSocketAddress(
            ((InetSocketAddress) response.socketAddress).getAddress(),
            ((InetSocketAddress) response.socketAddress).getPort());
    byte[] responseData = response.data.getBytes(StandardCharsets.UTF_8);
      DatagramPacket responsePacket = new DatagramPacket(
              responseData,
              responseData.length,
              remoteAddress);
      this.socket.send(responsePacket);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
