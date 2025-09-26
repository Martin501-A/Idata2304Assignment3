package ntnu.iir.bidata.martinbf.logic.client;

import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.logic.protocoltypes.TProtocol;
import ntnu.iir.bidata.martinbf.logic.protocoltypes.UDP;
import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

/**
 * Represents a client for the remote that can handle connections to multiple TVs
 */
public class RemoteClient {
    private List<Socket> Servers;
    private Remote remote;
    private int serverPort;
    private TProtocol protocol;


    /**
     * Instantiates the RemoteClient and finds the servers the client can connect to.
     */
    public RemoteClient(int serverPort,Remote remote, TProtocol protocol) {
        if  (serverPort < 1024 || serverPort > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
        if  (remote == null) {
            throw new IllegalArgumentException("Remote is null");
        }
        this.serverPort = serverPort;
        this.remote = remote;
        this.protocol =  protocol;
        findServers();
    }

    /**
     * Finds all available Servers on the port
     */
    public void findServers() {
        for (IPAddress address :IPAddress.values()) {
            try {
                SocketAddress socketAddress = this.protocol.findServer(address, this.serverPort);
            } catch (Exception e) {
                continue;
            }
        }
    }
}
