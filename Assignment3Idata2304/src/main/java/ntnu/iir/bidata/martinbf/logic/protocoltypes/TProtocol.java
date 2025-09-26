package ntnu.iir.bidata.martinbf.logic.protocoltypes;

import ntnu.iir.bidata.martinbf.logic.server.IPAddress;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Represents the transport protocols TCP and UDP
 * Is used to find servers for the client.
 */
public interface TProtocol {

    /**
     * Finds a server using the protocol
     */
    public SocketAddress findServer(IPAddress address, int port) throws Exception;

    /**
     * creates an object of this.
     */
    public TProtocol newInstance();

}
