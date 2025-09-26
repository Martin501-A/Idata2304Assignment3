package ntnu.iir.bidata.martinbf.logic.client.thread;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;
import java.io.IOException;
import java.net.*;

/**
 * Client for sending UDP commands to a remote UDP server.
 */
public class UDPRemoteClientThread extends Thread {
  private ResponseHandler handler;
  private Command command;
  private SocketAddress tvAddress;


  /**
   * Instantiates the UDP client with the given remote and port.
   *.
   * @param port   the port to send on.
   */
  public UDPRemoteClientThread(ResponseHandler handler, int port, IPAddress address, Command command) {
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
    this.tvAddress = new InetSocketAddress(address.toString(), port);

  }


  /**
   * Sends a command to the UDP server using this clients protocol.
   */
  @Override
  public void run() {
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null");
    }
      try (DatagramSocket socket = new DatagramSocket();
      ) {
        socket.setSoTimeout(3000);
        String message = command.toString();
        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, this.tvAddress);
        socket.send(packet);
        byte[] buffer = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(responsePacket);
        String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
        if (!response.isEmpty()) {
          handler.handle(response);
        }
      } catch (SocketTimeoutException e) {
        System.err.println("No response from server, request timed out.");
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        throw new RuntimeException(e); //TODO Better implementation
      }
  }
}
