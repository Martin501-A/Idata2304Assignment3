package ntnu.iir.bidata.martinbf.logic.client.thread;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Marker interface for client runtime threads.
 */
public abstract class ClientRuntimeThread extends Thread {
  protected InetSocketAddress tvAddress;
  protected ResponseHandler handler;
  protected InetSocketAddress address;
  protected Command command;

  /**
   * Instantiates a new ClientRuntimeThread.
   */
  public ClientRuntimeThread(ResponseHandler handler, int port, IPAddress address, Command command) {
    if (handler == null) {
      throw new IllegalArgumentException("handler cannot be null");
    }
    if (port < 1024 || port > 50000) {
      throw new IllegalArgumentException("Port must be between 0 and 65535");
    }
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    this.handler = handler;
    this.command = command;
    this.tvAddress = new InetSocketAddress(address.getAddress(), port);
  }
}
