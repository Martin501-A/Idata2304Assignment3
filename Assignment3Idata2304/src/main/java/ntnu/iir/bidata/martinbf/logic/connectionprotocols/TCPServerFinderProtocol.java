package ntnu.iir.bidata.martinbf.logic.connectionprotocols;

import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.Socket;
import java.net.SocketAddress;

/**
  * Is responsible for finding a server using the TCP protocol.
 */
public class TCPServerFinderProtocol implements ConnectionProtocol {

  public TCPServerFinderProtocol() {}

  /**
   * Finds a server using the TCPServerFinderProtocol protocol.
   */
  @Override
  public SocketAddress findServer(IPAddress address, int port) throws ServerNotFoundException {
    try (Socket socket = new Socket(address.getAddress(), port)) {
      return socket.getRemoteSocketAddress();
    } catch (Exception e) {
      throw new ServerNotFoundException("No server found at " + address + ":" + port);
    }
  }

}
