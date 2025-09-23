package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.entities.Channel;
import ntnu.iir.bidata.martinbf.entity.entities.Command;
import ntnu.iir.bidata.martinbf.entity.entities.IPAddress;
import ntnu.iir.bidata.martinbf.entity.entities.Remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Represents the client that connects to the TV server.
 */
public class TCPRemoteClient implements TVRemoteClient {
  private Remote remote;
  private int port;

  /**
   * Instantiates a new TV remote client with a remote.
   *
   * @param remote
   */
  public TCPRemoteClient(Remote remote, int port) {
    if (remote == null) {
      throw new IllegalArgumentException("Remote cannot be null");
    }
    if (port < 1024 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1024 and 65535");
    }
    this.remote = remote;
    this.port = port;
  }

  /**
   * Sends a command to the TV server that handles the response.
   */
  public void protocol(Command command) {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
    try (Socket socket = new Socket(IPAddress.SOCKET_ADDRESS.getAddress(), this.port);
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
      out.println(command);
      String response = in.readLine();
      if (response != null) {
        remote.setCurrentChannel(Channel.valueOf(response));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
