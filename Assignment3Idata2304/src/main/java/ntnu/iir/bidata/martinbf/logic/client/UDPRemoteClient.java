package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;
import ntnu.iir.bidata.martinbf.entity.Remote;

import java.io.IOException;
import java.net.*;

/**
 * Client for sending UDP commands to a remote UDP server.
 */
public class UDPRemoteClient implements TVRemoteClient {
  private Remote remote;
  private int port;
  private SocketAddress tvAddress;


  /**
   * Instantializes the UDP client with the given remote and port.
   */
  public UDPRemoteClient(Remote remote, int port) {
    if (remote == null) {
      throw new IllegalArgumentException("Remote cannot be null");
    }
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 0 and 65535");
    }
    this.remote = remote;
    this.port = port;
    this.tvAddress = new InetSocketAddress(IPAddress.ServerAddress.getAddress(), port);
  }


  /**
   * Sends a command to the UDP server using this clients protocol.
   */
  @Override
  public void protocol(Command command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }

    try (DatagramSocket socket = new DatagramSocket(
            new InetSocketAddress(IPAddress.BroadcastAddress.getAddress(), port));
    ) {
      byte[] data = command.toString().getBytes();
      DatagramPacket packet = new DatagramPacket(data, data.length, this.tvAddress);
      socket.send(packet);

      byte[] buffer = new byte[data.length];
      DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
      socket.receive(responsePacket);
      String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
      if (!response.isEmpty()) {
        remote.setCurrentChannel(Channel.valueOf(response));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
