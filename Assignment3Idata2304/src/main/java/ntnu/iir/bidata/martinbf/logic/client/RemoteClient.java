package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.connectionprotocols.ConnectionProtocol;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client for the remote that can handle connections to multiple TVs
 */
public class RemoteClient {
    private List<SocketAddress> servers;
    private int serverPort;
    private ConnectionProtocol protocol;
    private volatile ResponseHandler handler;


    /**
     * Instantiates the RemoteClient and finds the servers the client can connect to.
     */
    public RemoteClient(int serverPort, ResponseHandler handler, ConnectionProtocol protocol) {
        if  (serverPort < 1024 || serverPort > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
        if (handler == null) {
            throw new IllegalArgumentException("Handler cannot be null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("Protocol cannot be null");
        }
        this.serverPort = serverPort;
        this.handler = handler;
        this.protocol =  protocol;
        this.servers = new ArrayList<>();
        findServers();
    }

    /**
     * Finds all available Servers on the port
     */
    public void findServers() {
        for (IPAddress address :IPAddress.values()) {
            try {
                SocketAddress socketAddress = this.protocol.findServer(address, this.serverPort);
                if (socketAddress != null) {
                  this.servers.add(socketAddress);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

  /**
   * Does the runtime protocol for the client.
   * Sends the command to all servers this client knows.
   */
  public void runtimeProtocol(Command command) {
    if (servers.isEmpty()) {
      throw new IllegalCallerException("Servers list is empty");
    }
    this.servers.forEach((SocketAddress socket) -> {

    });
  }
}
