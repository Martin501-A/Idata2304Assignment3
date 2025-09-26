package ntnu.iir.bidata.martinbf.logic.connectionprotocols;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.Connectionhandler;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.client.thread.UDPRemoteClientThread;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Is responsible for finding servers using UDPServerFinderProtocol protocol.
 */
public class UDPServerFinderProtocol implements ConnectionProtocol {


  public UDPServerFinderProtocol(){}

  /**
   * Finds a server using the protocol
   *
   * @param address the IP address to find the server on.
   * @param port the port to find the server on.
   * @return the socket address of the server.
   */
  @Override
  public SocketAddress findServer(IPAddress address, int port) throws ServerNotFoundException {
    try {
      ResponseHandler handler = new Connectionhandler();
      UDPRemoteClientThread client = new UDPRemoteClientThread(handler, port, address, Command.CONNECT);
      client.start();
      client.join(4000);//TODO See if this works as intended.
      return new InetSocketAddress(address.toString(), port);
    } catch (Exception e) {
      throw new ServerNotFoundException("No server found at " + address + ":" + port);
    }
  }
}
