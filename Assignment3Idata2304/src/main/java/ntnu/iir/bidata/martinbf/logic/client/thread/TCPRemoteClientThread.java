package ntnu.iir.bidata.martinbf.logic.client.thread;

import ntnu.iir.bidata.martinbf.entity.Channel;
import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;
import ntnu.iir.bidata.martinbf.entity.Remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Represents the client that connects to the TV server.
 */
public class TCPRemoteClientThread extends Thread {
  private ResponseHandler handler;
  private InetSocketAddress address;
  private Command command;

  /**
   * Instantiates a new TV remote client with a remote.
   */
  public TCPRemoteClientThread(ResponseHandler handler, int port, IPAddress address, Command command) {
    if (handler == null) {
      throw new IllegalArgumentException("handler cannot be null");
    }
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    this.handler = handler;
    this.command = command;
    this.address = new InetSocketAddress(address.toString(), port);
  }

  /**
   * Sends a command to the TV server that handles the response.
   */
  @Override
  public void run() {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    try (Socket socket = new Socket(
            this.address.getHostName(),
            this.address.getPort()
    );
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
      out.println(command);
      String response = in.readLine();
      if (response != null) {
        handler.handle(response);
      }
    } catch (IOException e) {
      handler.handle(null);
    }
  }
}
