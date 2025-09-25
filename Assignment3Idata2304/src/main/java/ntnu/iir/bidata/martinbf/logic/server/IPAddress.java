package ntnu.iir.bidata.martinbf.logic.server;

public enum IPAddress {



  ServerAddress("127.0.0.1");

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

