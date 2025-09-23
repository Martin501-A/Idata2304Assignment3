package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.entities.Channel;
import ntnu.iir.bidata.martinbf.entity.entities.Command;
import ntnu.iir.bidata.martinbf.entity.entities.IPAddress;
import ntnu.iir.bidata.martinbf.entity.entities.Remote;

import java.io.IOException;
import java.net.*;

/**
 * Client for sending UDP commands to a remote UDP server.
 */
public class UDPRemoteClient implements TVRemoteClient {
  private Remote remote;
  private int port;
  private SocketAddress socketAddress;


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
    this.socketAddress = new InetSocketAddress(IPAddress.SOCKET_ADDRESS.getAddress(), port);
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
            this.socketAddress);
    ) {
      byte[] data = command.toString().getBytes();
      DatagramPacket packet = new DatagramPacket(data, data.length, this.socketAddress);
      socket.send(packet);

      byte[] buffer = new byte[data.length];
      DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
      socket.receive(responsePacket);
      String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
      if (response != null) {
        remote.setCurrentChannel(Channel.valueOf(response));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
