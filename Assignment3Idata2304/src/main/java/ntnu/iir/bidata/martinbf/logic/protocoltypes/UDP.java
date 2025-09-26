package ntnu.iir.bidata.martinbf.logic.protocoltypes;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.Connectionhandler;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.client.thread.UDPRemoteClientThread;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Is responsible for finding servers using UDP protocol.
 */
public class UDP implements TProtocol {


  private UDP(){}

  /**
   * Finds a server using the protocol
   *
   * @param address the IP address to find the server on.
   * @param port the port to find the server on.
   * @return the socket address of the server.
   */
  @Override
  public SocketAddress findServer(IPAddress address, int port) throws Exception {
    ResponseHandler handler = new Connectionhandler();
    UDPRemoteClientThread client = new UDPRemoteClientThread(handler, port, address, Command.CONNECT);
    client.start();
    client.join(4000);//TODO See if this works as intended.
    return new InetSocketAddress(address.toString(), port);
  }

  /**
   * creates an object of this.
   *
   * @return new instance of this.
   */
  @Override
  public TProtocol newInstance() {
    return new UDP();
  }
}
