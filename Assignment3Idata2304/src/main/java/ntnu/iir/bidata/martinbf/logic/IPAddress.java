package ntnu.iir.bidata.martinbf.logic;

public enum IPAddress {



  SOCKET_ADDRESS("192.168.1.10");

  private final String address;

  IPAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the IP address.
   *
   * @return ipv4 address.
   */
  public String getAddress() {
    return address;
  }

}

