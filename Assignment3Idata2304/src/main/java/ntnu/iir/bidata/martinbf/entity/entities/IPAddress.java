package ntnu.iir.bidata.martinbf.entity.entities;

public enum IPAddress {



  SOCKET_ADDRESS("127.0.0.1"),;

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

