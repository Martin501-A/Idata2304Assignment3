package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.logic.Command;
import ntnu.iir.bidata.martinbf.logic.client.handler.RemoteHandler;
import ntnu.iir.bidata.martinbf.logic.client.handler.ResponseHandler;
import ntnu.iir.bidata.martinbf.logic.client.thread.ClientRuntimeThread;
import ntnu.iir.bidata.martinbf.logic.connectionprotocols.ConnectionProtocol;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client for the remote that can handle connections to multiple TVs
 */
public class RemoteClient {
  private List<InetSocketAddress> servers;
  private int serverPort;
  private ConnectionProtocol protocol;
  private volatile Remote remote;
  private final Class<? extends ClientRuntimeThread> runTimeClass;


  /**
   * Instantiates the RemoteClient and finds the servers the client can connect to.
   */
  public RemoteClient(int serverPort, Remote remote,
                      ConnectionProtocol protocol,
                      Class<? extends ClientRuntimeThread> runTimeClass) {
    if  (serverPort < 1024 || serverPort > 65535) {
      throw new IllegalArgumentException("Invalid port number");
    }
    if (remote == null) {
      throw new IllegalArgumentException("Remote cannot be null");
    }
    if (protocol == null) {
      throw new IllegalArgumentException("Protocol cannot be null");
    }
    this.serverPort = serverPort;
    this.remote = remote;
    this.protocol = protocol;
    this.servers = new ArrayList<>();
    this.runTimeClass = runTimeClass;
    findServers();
  }

  /**
   * Finds all available Servers on the port
   */
  public void findServers() {
    for (IPAddress address :IPAddress.values()) {
      try {
        InetSocketAddress socketAddress = this.protocol.findServer(address, this.serverPort);
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
    this.servers.forEach((InetSocketAddress address) -> {
      ResponseHandler handler = new RemoteHandler(this.remote);
      try {
        ClientRuntimeThread clientThread = this.runTimeClass.getDeclaredConstructor(
                ResponseHandler.class,
                int.class,
                IPAddress.class,
                Command.class
        ).newInstance(
                new RemoteHandler(this.remote),
                address.getPort(),
                IPAddress.fromString(address.getAddress().getHostAddress()),
                command
        );
        clientThread.start();
      } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
               IllegalAccessException e) {
        throw new RuntimeException(e);
      }

    });
  }
}
