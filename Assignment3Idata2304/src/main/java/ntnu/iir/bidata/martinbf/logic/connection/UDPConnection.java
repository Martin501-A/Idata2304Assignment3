package ntnu.iir.bidata.martinbf.logic.connection;

import ntnu.iir.bidata.martinbf.logic.protocol.Protocol;

/**
 * Represents a UDP network connection.
 * A UDP connection is defined by its host, port, and protocol.
 */
public class UDPConnection implements Connection {
  private String host;
  private int port;
  private final Protocol protocol;
  private


  public UDPConnection(String host, int port, Protocol protocol) {
    this.host = host;
    this.port = port;
    this.protocol = protocol;
  }

  @Override
  public void connect() {
  }

  @Override
  public void connect(String address, int port) {
  }

  @Override
  public void disconnect() {

  }
}
