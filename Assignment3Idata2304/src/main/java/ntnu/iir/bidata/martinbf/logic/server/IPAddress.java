package ntnu.iir.bidata.martinbf.logic.server;

/**
 * Represents the known ip addresses for the TV servers.
 */
public enum IPAddress {
  ServerAddress1("127.0.0.1"),
  ServerAddress2("127.0.0.2");


  private final String address;

  IPAddress(String address) {
    this.address = address;
  }

  public static IPAddress fromString(String hostAddress) {
    IPAddress address = null;
    for (IPAddress ipAddress : IPAddress.values()) {
      if (ipAddress.address.equals(hostAddress)) {
        address = ipAddress;
      }
    }
    if (address != null) {
      return address;
    }
    throw new IllegalArgumentException("No enum constant with text " + hostAddress + " found");
  }

  /**
   * Returns the IP address.
   *
   * @return ipv4 address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns the IP address as a string.
   *
   * @return ipv4 address.
   */
  @Override
  public String toString() {
    return address;
  }

}

