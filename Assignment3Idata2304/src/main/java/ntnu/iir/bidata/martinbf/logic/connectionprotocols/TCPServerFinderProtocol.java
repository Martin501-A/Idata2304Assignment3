package ntnu.iir.bidata.martinbf.logic.connectionprotocols;

import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.InetSocketAddress;
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
  public InetSocketAddress findServer(IPAddress address, int port) throws ServerNotFoundException {
    try (Socket socket = new Socket(address.getAddress(), port)) {
      return new InetSocketAddress(socket.getInetAddress().getHostName(), socket.getPort());
    } catch (Exception e) {
      throw new ServerNotFoundException("No server found at " + address + ":" + port);
    }
  }

}
