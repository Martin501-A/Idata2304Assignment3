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
public class TCPRemoteClientThread extends ClientRuntimeThread {

  /**
   * Instantiates a new TV remote client with a remote.
   */
  public TCPRemoteClientThread(ResponseHandler handler, int port, IPAddress address, Command command) {
    super(handler, port, address, command);
  }

  /**
   * Sends a command to the TV server that handles the response.
   */
  @Override
  public void run() {
    if (super.command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    try (Socket socket = new Socket(
            super.address.getHostName(),
            super.address.getPort()
    );
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
      out.println(super.command);
      String response = in.readLine();
      if (response != null) {
        super.handler.handle(response);
      }
    } catch (Exception e) {
      //TODO For now we just ignore exceptions
    }
  }
}
