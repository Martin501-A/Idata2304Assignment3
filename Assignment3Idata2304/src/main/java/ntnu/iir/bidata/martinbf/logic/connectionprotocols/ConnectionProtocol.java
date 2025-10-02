package ntnu.iir.bidata.martinbf.logic.connectionprotocols;

import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Represents the connection protocol to find Servers.
 */
public interface ConnectionProtocol {

    /**
     * Finds a server using the protocol
     */
    public InetSocketAddress findServer(IPAddress address, int port) throws Exception;

}
